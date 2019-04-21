package com.example.demo.dto;

import lombok.Data;

@Data
public class KeyCenterRedisConfigDTO {
    private String host;
    private Integer port;
    private String password;
    private Integer database;
}
