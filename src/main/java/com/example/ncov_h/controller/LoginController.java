package com.example.ncov_h.controller;


import cn.hutool.core.util.StrUtil;
import com.example.ncov_h.common.Constants;
import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.dto.LoginDTO;
import com.example.ncov_h.entity.User;
import com.example.ncov_h.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400,"参数错误");
        }
        LoginDTO dto = userService.login(user);
        return Result.success(dto);
    }
    
}
