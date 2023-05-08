package com.example.ncov_h.controller.request;

import lombok.Data;

@Data
public class GeliRequest extends BaseRequest{

    private String username;

    private String roleId;
    
    private String isAdmin;
    
}
