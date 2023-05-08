package com.example.ncov_h.controller.dto;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;
//    private String password;
    private String roleId;
    private String name;
    private String token;
}