package com.example.ncov_h.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_stu")
public class StudentInfo {
    
    private Integer id;
    private String stuId;
    private String name;
    private String sex;
    private Integer age;
    private String phone;
    private String xueyuanName;
    private String className;
    private String status;
}
