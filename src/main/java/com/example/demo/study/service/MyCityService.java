package com.example.study.service;

import com.example.demo.domain.CityVO;
import com.example.demo.mapper.CityDAO;
import com.example.demo.service.CityOtherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
@Slf4j
public class MyCityService {

    @Autowired
    private CityDAO cityDAO;

    /**
     *  更新城市名称
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void queryCityName() {

        // 更新10001
        CityVO cityVO = cityDAO.selectByPrimaryKey("10001");
    }
}
