package com.example.demo.service;

import com.example.demo.constant.ConstAreaCodeConfiguration;
import com.example.demo.domain.AreaCodeVO;
import com.example.demo.domain.AreaCodeVOExample;
import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.UserAreaDTO;
import com.example.demo.mapper.AreaCodeDAO;
import com.example.demo.util.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AddressService {

    @Autowired
    private AreaCodeDAO areaCodeDAO;

    /**
     * 省、市、县（区）三个必须得有两个才能倒推第三个，否则地址无效
     *
     * @param address
     * @param isGetZoneCodeByCity 如果区编码不存在，是否根据市级编码+1
     * @return
     */
    public UserAreaDTO parseAddress(String address, boolean isGetZoneCodeByCity) {
        if (StringUtils.isBlank(address)) {
            return null;
        }

        AddressDTO addressDTO = AddressUtils.parseAddress(address);
        if (addressDTO == null) {
            return null;
        }
        String provinceName = StringUtils.trim(addressDTO.getProvince());
        String cityName = StringUtils.trim(addressDTO.getCity());
        String countyName = StringUtils.trim(addressDTO.getCounty());
        // 省市区都为空：解析失败；
        if (null == addressDTO || StringUtils.isAllBlank(provinceName, cityName, countyName)) {
            log.error("从地址信息解析省市区失败，地址：{}，省：{}，市：{}，区：{}", address, provinceName, cityName, countyName);
            return null;
        }
        AreaCodeVO provinceVO = this.getByNameLike(provinceName, 1, null);
        // 如果省是直辖市，那么市级名称都是“市辖区”或者“县” 老的地址里面可能存在县，比如密云县（现在叫密云区），它的上级名称是“县”
        if (provinceVO != null && ConstAreaCodeConfiguration.getDirectlyCityCodeList().contains(provinceVO.getAreaCode())) {
            if (countyName != null && countyName.endsWith("县")) {
                cityName = "县";
            } else {
                cityName = "市辖区";
            }
        }

        AreaCodeVO cityVO = this.getByNameLike(cityName, 2, provinceVO);
        AreaCodeVO countyVO = this.getByNameLike(countyName, 3, cityVO);

        UserAreaDTO userAreaDTO = new UserAreaDTO();

        if (provinceVO != null && cityVO != null && countyVO != null) {
            // 三级都不为空不做处理
        } else if (provinceVO != null && cityVO != null) {
            // 省、市两级不为空，那么县区可能有多个，则根据市级编码和县区名称再查询一次
            countyVO = this.getByNameLike(countyName, 3, cityVO);
            if (countyVO == null) {
                if (isGetZoneCodeByCity) {
                    log.error("根据市级编码以及县区名称查询县区编码不存在，县区编码在市编码上加1，市区名称：{}，市区编码：{}，县区名称：{}", cityVO.getAreaName(), cityVO.getAreaCode(), countyName);
                    countyVO = new AreaCodeVO();
                    countyVO.setAreaName(countyName);
                    countyVO.setAreaCode(cityVO.getAreaCode() + 1);
                } else {
                    log.error("根据市级编码以及县区名称查询编码不存在，不再查询，市区名称：{}，市区编码：{}，县区名称：{}", cityVO.getAreaName(), cityVO.getAreaCode(), countyName);
                    return null;
                }
            }
//            String areaCode = areaCodeDAO.queryCityAreaCode(countyName, "3", cityVO.getAreaCode());
//            if (StringUtils.isBlank(areaCode)) {
//                log.error("根据市级编码以及县区名称查询县区编码不存在，县区编码在市编码上加1，市区名称：{}，市区编码：{}，县区名称：{}", cityVO.getAreaName(), cityVO.getAreaCode(), countyName);
//                countyVO = new AreaCodeVO();
//                countyVO.setAreaName(countyName);
//                countyVO.setAreaCode(cityVO.getAreaCode() + 1);
//            }
        } else if (provinceVO != null && countyVO != null) {
            // 省、区县两级不为空，缺少市级，根据区县倒推市级编码
            cityVO = this.getByCode(countyVO.getParentCode(), 2);
            if (cityVO == null) {
                log.error("根据县区编码查询市级编码失败，address：{}，countyVO：{}", address, countyVO);
                return null;
            }
        } else if (cityVO != null && countyVO != null) {
            // 市、县区两级编码不为空，缺少省级，根据市区倒推省编码
            provinceVO = this.getByCode(cityVO.getParentCode(), 1);
            if (provinceVO == null) {
                log.error("根据市级编码查询省级编码失败，address：{}，cityVO：{}", address, cityVO);
                return null;
            }
        } else {
            log.error("查询省、市、县区编码失败，address：{}，provinceVO：{}，cityVO：{}，countyVO：{}", address, provinceVO, cityVO, countyVO);
            return null;
        }

        userAreaDTO.setProvinceName(provinceVO.getAreaName());
        userAreaDTO.setProvinceCode(provinceVO.getAreaCode());
        userAreaDTO.setCityName(cityVO.getAreaName());
        userAreaDTO.setCityCode(cityVO.getAreaCode());
        userAreaDTO.setZoneName(countyVO.getAreaName());
        userAreaDTO.setZoneCode(countyVO.getAreaCode());

        return userAreaDTO;
    }

    /**
     * 根据名称模糊查询编码，如果查询多个则失败
     *
     * @param areaName
     * @return
     */
    private AreaCodeVO getByNameLike(String areaName, int level, AreaCodeVO partnerCodeVO) {
        if (StringUtils.isBlank(areaName)) {
            return null;
        }
        AreaCodeVOExample example = new AreaCodeVOExample();
        AreaCodeVOExample.Criteria criteria = example.createCriteria();
        criteria.andAreaNameLike(areaName + "%");
        criteria.andAreaLevelEqualTo(level);
        if (partnerCodeVO != null && StringUtils.isNotBlank(partnerCodeVO.getAreaCode())) {
            criteria.andParentCodeEqualTo(partnerCodeVO.getAreaCode());
        }
        List<AreaCodeVO> list = areaCodeDAO.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            log.error("根据区域名称查询区域编码不存在，区域名称：{}，层级：{}", areaName, level);
            // 针对县区级，有些地方已经将县升级为区或者将区升级为市等情况，为了能够匹配这类数据，将最后一个县/区/市去掉后匹配
            if (level == 3 && areaName.length() > 2 && (areaName.endsWith("县") || areaName.endsWith("区") || areaName.endsWith("市"))) {
                log.info("根据区域名称查询编码不存在，该区域级别为第3级且以 县/区/市结尾，准备去掉结尾的 县/区/市再查询一次，区域名称：{}", areaName);
                return getByNameLike(areaName.substring(0, areaName.length() - 1), 3, partnerCodeVO);
            }
            return null;
        } else if (list.size() > 1) {
            log.error("根据区域名称查询到多个区域编码，区域名称：{}，层级：{}", areaName, level);
            return null;
        }
        return list.get(0);
    }

    /**
     * 根据编码获取信息
     *
     * @param areaCode
     * @param level
     * @return
     */
    private AreaCodeVO getByCode(String areaCode, int level) {
        AreaCodeVOExample example = new AreaCodeVOExample();
        AreaCodeVOExample.Criteria criteria = example.createCriteria();
        criteria.andAreaCodeEqualTo(areaCode);
        criteria.andAreaLevelEqualTo(level);
        List<AreaCodeVO> list = areaCodeDAO.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            log.error("根据区域编码查询编码信息不存在，区域编码：{}，层级：{}", areaCode, level);
            return null;
        } else if (list.size() > 1) {
            log.error("根据区域名称查询到多个编码信息，区域编码：{}，层级：{}", areaCode, level);
            return null;
        }
        return list.get(0);
    }
}
