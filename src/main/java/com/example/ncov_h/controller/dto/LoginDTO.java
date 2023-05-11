package com.example.ncov_h.controller.dto;

import com.example.ncov_h.entity.Menu;
import lombok.Data;

import java.util.List;

@Data
public class LoginDTO {

    private String username;
//    private String password;
    private String roleId;
    private String name;
    private String token;
    private List<Menu> menus;
}