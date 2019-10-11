package com.example.demo.mapper;

import com.example.demo.domain.ReportInoutPlanVO;
import com.example.demo.domain.ReportInoutPlanVOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReportInoutPlanDAO {
    long countByExample(ReportInoutPlanVOExample example);

    int deleteByExample(ReportInoutPlanVOExample example);

    int deleteByPrimaryKey(String custInoutPlanLedgerId);

    int insert(ReportInoutPlanVO record);

    int insertSelective(ReportInoutPlanVO record);

    List<ReportInoutPlanVO> selectByExample(ReportInoutPlanVOExample example);

    ReportInoutPlanVO selectByPrimaryKey(String custInoutPlanLedgerId);

    int updateByExampleSelective(@Param("record") ReportInoutPlanVO record, @Param("example") ReportInoutPlanVOExample example);

    int updateByExample(@Param("record") ReportInoutPlanVO record, @Param("example") ReportInoutPlanVOExample example);

    int updateByPrimaryKeySelective(ReportInoutPlanVO record);

    int updateByPrimaryKey(ReportInoutPlanVO record);
}