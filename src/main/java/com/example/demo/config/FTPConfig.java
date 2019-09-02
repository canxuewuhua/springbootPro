package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 *   启动类一加载，就记载该类，通过keyCenter的配置文件和在keyCenter中存储的key值，获取到hostName，userName，passWord，port
 *   测试类在test/bestPay/ConnectFTPTest
 */
@Configuration
@Slf4j
public class FTPConfig {

    //@Value("${keyCenter.config.path}")
    private String keyCenterPath;

    //@Value("${account.bestpay.ftp.connect}")
    private String bestpayFTPConnectInfo;

    public String hostName;

    public String userName;

    public String passWord;

    public Integer port;

    @PostConstruct
    private void init() {
        log.info("keycenter配置文件路径：{}，从keycenter取翼支付FTP连接信息的key：{}", keyCenterPath, bestpayFTPConnectInfo);
        //KeyCenterConstants.getKeyCenterProperties(keyCenterPath);
        //String response = KeyCenterUtil.getKeyCenterValue(bestpayFTPConnectInfo);
        //FTPConfigDTO ftpConfigDTO = JSON.parseObject(response, FTPConfigDTO.class);
        //this.hostName = ftpConfigDTO.getHostName();
        //this.userName = ftpConfigDTO.getUserName();
        //this.passWord = ftpConfigDTO.getPassWord();
        //this.port = ftpConfigDTO.getPort();
    }
}
