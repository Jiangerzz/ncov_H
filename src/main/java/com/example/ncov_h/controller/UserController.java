package com.example.ncov_h.controller;


import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.UserRequest;
import com.example.ncov_h.entity.User;
import com.example.ncov_h.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/list")
    public Result getUserList(UserRequest userRequest){
        return userService.queryUserList(userRequest);
    }
    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable("id") String id){
        boolean b = userService.removeById(id);
        if(b == true){
            return Result.success();
        }else {
            return Result.error("删除失败，请重试");
        }

    }
    @PostMapping
    public Result save(@RequestBody User user){
        return userService.insertUser(user);
    }
    
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        return userService.updateUser(user); 
    }
    
    @GetMapping("/username/{username}")
    public Result getUser(@PathVariable("username") String username){
        return userService.getUser(username);
    }
}
