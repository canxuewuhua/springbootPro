package com.example.demo.mapper;

import com.example.demo.domain.CoreProxyAbutmentMerchantVO;
import com.example.demo.domain.CoreProxyAbutmentMerchantVOExample;
import com.example.demo.domain.CoreProxyAbutmentMerchantVOKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CoreProxyAbutmentMerchantDAO {
    long countByExample(CoreProxyAbutmentMerchantVOExample example);

    int deleteByExample(CoreProxyAbutmentMerchantVOExample example);

    int deleteByPrimaryKey(CoreProxyAbutmentMerchantVOKey key);

    int insert(CoreProxyAbutmentMerchantVO record);

    int insertSelective(CoreProxyAbutmentMerchantVO record);

    List<CoreProxyAbutmentMerchantVO> selectByExample(CoreProxyAbutmentMerchantVOExample example);

    CoreProxyAbutmentMerchantVO selectByPrimaryKey(CoreProxyAbutmentMerchantVOKey key);

    int updateByExampleSelective(@Param("record") CoreProxyAbutmentMerchantVO record, @Param("example") CoreProxyAbutmentMerchantVOExample example);

    int updateByExample(@Param("record") CoreProxyAbutmentMerchantVO record, @Param("example") CoreProxyAbutmentMerchantVOExample example);

    int updateByPrimaryKeySelective(CoreProxyAbutmentMerchantVO record);

    int updateByPrimaryKey(CoreProxyAbutmentMerchantVO record);
}