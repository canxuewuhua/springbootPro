package com.example.demo.mapper;

import com.example.demo.domain.GeoRequestLogVO;
import com.example.demo.domain.GeoRequestLogVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GeoRequestLogDAO {
    long countByExample(GeoRequestLogVOExample example);

    int deleteByExample(GeoRequestLogVOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GeoRequestLogVO record);

    int insertSelective(GeoRequestLogVO record);

    List<GeoRequestLogVO> selectByExampleWithBLOBs(GeoRequestLogVOExample example);

    List<GeoRequestLogVO> selectByExample(GeoRequestLogVOExample example);

    GeoRequestLogVO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GeoRequestLogVO record, @Param("example") GeoRequestLogVOExample example);

    int updateByExampleWithBLOBs(@Param("record") GeoRequestLogVO record, @Param("example") GeoRequestLogVOExample example);

    int updateByExample(@Param("record") GeoRequestLogVO record, @Param("example") GeoRequestLogVOExample example);

    int updateByPrimaryKeySelective(GeoRequestLogVO record);

    int updateByPrimaryKeyWithBLOBs(GeoRequestLogVO record);

    int updateByPrimaryKey(GeoRequestLogVO record);
}