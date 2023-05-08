package com.example.ncov_h.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_tc")
public class TeacherInfo {
    
    private Integer id;
    private String tcId;
    private String name;
    private Integer age;
    private String sex;
    private String zc;
    private String phone;
    private String status;
    
}
