package com.example.ncov_h.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.dto.LoginDTO;
import com.example.ncov_h.controller.dto.StuInfoDTO;
import com.example.ncov_h.controller.dto.TcInfoDTO;
import com.example.ncov_h.controller.request.UserRequest;
import com.example.ncov_h.entity.User;

public interface UserService extends IService<User> {
    
    LoginDTO login(User user);
    
    Result queryStuInfo(String stuId);
    
    Result updateStuInfo(StuInfoDTO stuInfo);
    
    Result queryTcInfo(String tcId);
    
    Result updateTcInfo(TcInfoDTO tcInfoDTO);
    
    Result queryUserList(UserRequest userRequest);
    
    Result insertUser(User user);
    
    Result updateUser(User user);
    
    Result getUser(String username);
}
