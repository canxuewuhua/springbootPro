package com.example.demo.mapper;

import com.example.demo.domain.EmployeeVO;
import com.example.demo.domain.EmployeeVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeeDAO {
    long countByExample(EmployeeVOExample example);

    int deleteByExample(EmployeeVOExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmployeeVO record);

    int insertSelective(EmployeeVO record);

    List<EmployeeVO> selectByExample(EmployeeVOExample example);

    EmployeeVO selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmployeeVO record, @Param("example") EmployeeVOExample example);

    int updateByExample(@Param("record") EmployeeVO record, @Param("example") EmployeeVOExample example);

    int updateByPrimaryKeySelective(EmployeeVO record);

    int updateByPrimaryKey(EmployeeVO record);
}