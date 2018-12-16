package com.example.demo.dto;

import lombok.Data;

@Data
public class RabbitMQConfigDTO {
    private String host;
    private Integer port;
    private String username;
    private String password;
}
