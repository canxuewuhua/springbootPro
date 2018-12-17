package com.example.demo.mapper;

import com.example.demo.domain.EmployerVO;
import com.example.demo.domain.EmployerVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployerDAO {
    long countByExample(EmployerVOExample example);

    int deleteByExample(EmployerVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployerVO record);

    int insertSelective(EmployerVO record);

    List<EmployerVO> selectByExample(EmployerVOExample example);

    EmployerVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployerVO record, @Param("example") EmployerVOExample example);

    int updateByExample(@Param("record") EmployerVO record, @Param("example") EmployerVOExample example);

    int updateByPrimaryKeySelective(EmployerVO record);

    int updateByPrimaryKey(EmployerVO record);
}