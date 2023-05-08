package com.example.ncov_h.controller.request;


import lombok.Data;

/**
 * 基础请求参数
 * 用于分页
 */

@Data
public class BaseRequest {
    
    private Integer pageNum = 1;
    private Integer pageSize = 10;
    
}
