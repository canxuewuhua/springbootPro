package com.example.demo.mapper;

import com.example.demo.domain.UserVO;
import com.example.demo.domain.UserVOExample;
import java.util.List;
import java.util.Map;

import com.example.demo.test.listmap.dto.TongDunRequestDTO;
import org.apache.ibatis.annotations.Param;

public interface UserDAO {
    long countByExample(UserVOExample example);

    int deleteByExample(UserVOExample example);

    int deleteByPrimaryKey(Integer stId);

    int insert(UserVO record);

    int insertSelective(UserVO record);

    List<UserVO> selectByExample(UserVOExample example);

    UserVO selectByPrimaryKey(Integer stId);

    int updateByExampleSelective(@Param("record") UserVO record, @Param("example") UserVOExample example);

    int updateByExample(@Param("record") UserVO record, @Param("example") UserVOExample example);

    int updateByPrimaryKeySelective(UserVO record);

    int updateByPrimaryKey(UserVO record);
    /************************自定义开始*************************/
    List<Map<String,Object>> selectMapByExample(UserVOExample example);

    List<Map<String, Object>> selectTongDunRecord(@Param("record") TongDunRequestDTO tongDunRequestDTO);
    /************************自定义结束*************************/}