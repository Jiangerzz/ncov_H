package com.example.ncov_h.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("t_jkdk")
public class Daka {
    
    @TableId(type = IdType.AUTO)
    private String id;
    
    private String  username;
    
    private String name;
    
    private String tw;
    
    private String jkm; //绿色|1;橙色|2;红色|3
    
    private String status;
    
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tjsj;
    
    
}
