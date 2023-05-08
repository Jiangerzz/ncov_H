package com.example.ncov_h.controller;


import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.dto.StuInfoDTO;
import com.example.ncov_h.controller.dto.TcInfoDTO;
import com.example.ncov_h.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserInfoController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/queryStu/{stuId}")
    public Result queryStu(@PathVariable("stuId") String stuId){
        return userService.queryStuInfo(stuId);
    }
    
    @PostMapping("/updateStu")
    public Result updateStu(@RequestBody StuInfoDTO stuInfo){
        return userService.updateStuInfo(stuInfo);
    }
    
    @GetMapping("/queryTc/{tcId}")
    public Result queryTc(@PathVariable("tcId") String tcId){
        return userService.queryTcInfo(tcId);
    }
    
    @PostMapping("/updateTc")
    public Result updateTc(@RequestBody TcInfoDTO tcInfoDTO){
        return userService.updateTcInfo(tcInfoDTO);
    }
    
}
