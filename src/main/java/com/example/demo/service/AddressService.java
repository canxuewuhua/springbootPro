package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.UserAreaDTO;
import com.example.demo.util.AddressUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressService {

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
        UserAreaDTO userAreaDTO = new UserAreaDTO();

        userAreaDTO.setProvinceName(provinceName);
        userAreaDTO.setCityName(cityName);

        return userAreaDTO;
    }
}
