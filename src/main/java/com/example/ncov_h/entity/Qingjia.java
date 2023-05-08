package com.example.ncov_h.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Qingjia {

    @TableId(type = IdType.AUTO)
    private String id;

    private String username;

    private String name;

    private String kssj;

    private String jssj;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date czsj;
    
    private String qjsy;
    
    private String spzt;  //待审批|1;审批通过|2;驳回|3
}
