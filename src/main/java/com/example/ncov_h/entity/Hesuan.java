package com.example.ncov_h.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_hsjc")
public class Hesuan {
    
    @TableId(type = IdType.AUTO)
    private String id;
    
    private String username;
    
    private String cjsj;
    
    private String jcsj;
    
    private String jcjg;
    
    private String bz;
}
