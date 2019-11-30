package com.example.demo.mapper;

import com.example.demo.domain.AreaCodeVO;
import com.example.demo.domain.AreaCodeVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaCodeDAO {
    long countByExample(AreaCodeVOExample example);

    int deleteByExample(AreaCodeVOExample example);

    int deleteByPrimaryKey(String areaCodeId);

    int insert(AreaCodeVO record);

    int insertSelective(AreaCodeVO record);

    List<AreaCodeVO> selectByExample(AreaCodeVOExample example);

    AreaCodeVO selectByPrimaryKey(String areaCodeId);

    int updateByExampleSelective(@Param("record") AreaCodeVO record, @Param("example") AreaCodeVOExample example);

    int updateByExample(@Param("record") AreaCodeVO record, @Param("example") AreaCodeVOExample example);

    int updateByPrimaryKeySelective(AreaCodeVO record);

    int updateByPrimaryKey(AreaCodeVO record);

    /**
     * 查询直辖市code列表
     */
    List<String> queryDirectlyCityCodes();
}