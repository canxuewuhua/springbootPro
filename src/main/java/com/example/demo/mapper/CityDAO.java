package com.example.demo.mapper;

import com.example.demo.domain.CityVO;
import com.example.demo.domain.CityVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CityDAO {
    long countByExample(CityVOExample example);

    int deleteByExample(CityVOExample example);

    int deleteByPrimaryKey(String cityId);

    int insert(CityVO record);

    int insertSelective(CityVO record);

    List<CityVO> selectByExample(CityVOExample example);

    CityVO selectByPrimaryKey(String cityId);

    int updateByExampleSelective(@Param("record") CityVO record, @Param("example") CityVOExample example);

    int updateByExample(@Param("record") CityVO record, @Param("example") CityVOExample example);

    int updateByPrimaryKeySelective(CityVO record);

    int updateByPrimaryKey(CityVO record);
}