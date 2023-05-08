package com.example.ncov_h.controller.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class DakaRequest extends BaseRequest {
    
    private String username;
    
    private String tjsj;
    
    private String roleId;
}
