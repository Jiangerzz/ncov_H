package com.example.ncov_h.service.impl; 

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ncov_h.common.Constants;
import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.dto.LoginDTO;
import com.example.ncov_h.controller.dto.StuInfoDTO;
import com.example.ncov_h.controller.dto.TcInfoDTO;
import com.example.ncov_h.controller.request.UserRequest;
import com.example.ncov_h.entity.StudentInfo;
import com.example.ncov_h.entity.TeacherInfo;
import com.example.ncov_h.entity.User;
import com.example.ncov_h.exception.ServiceException;
import com.example.ncov_h.mapper.UserMapper;
import com.example.ncov_h.service.UserService;
import com.example.ncov_h.util.TokenUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {
    
    @Autowired
    private UserMapper userMapper;

    /**
     * 登录方法
     * @param user
     * @return
     */
    @Override
    public LoginDTO login(User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        queryWrapper.eq("password",user.getPassword());
        User one;
        try{
            one = getOne(queryWrapper);
        } catch (Exception e){
           e.printStackTrace();
           throw  new ServiceException(Constants.CODE_500,"系统错误");
        }
        if(one != null){
            LoginDTO userDto = new LoginDTO();
            BeanUtil.copyProperties(one, userDto,true);
            String token = TokenUtils.genToken(one.getUsername(), one.getPassword());
            userDto.setToken(token);
            if("1".equals(one.getRoleId())){
                userDto.setName("管理员");
            }else if("2".equals(one.getRoleId())){
                userDto.setName(userMapper.getStuNameById(one.getUsername()));
            }else if ("3".equals(one.getRoleId())){
                userDto.setName(userMapper.getTcNameById(one.getUsername()));
            }
            return userDto;
        }else{
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }

    @Override
    public Result getUser(String username) {
        String roleId = userMapper.getRoleId(username);
        String name = "";
        if("2".equals(roleId)){
            name = userMapper.getStuName(username);
        }else if("3".equals(roleId)){
            name = userMapper.getTcName(username);
        }
        return Result.success(name);
    }

    /**
     * 查询学生信息
     */

    @Override
    public Result queryStuInfo(String stuId) {
        StudentInfo studentInfo = userMapper.queryStuInfo(stuId);
        StuInfoDTO stuInfoDTO = new StuInfoDTO();
        if(studentInfo != null){
            BeanUtil.copyProperties(studentInfo,stuInfoDTO,true);
        }
        return Result.success(stuInfoDTO);
    }

    @Override
    public Result updateStuInfo(StuInfoDTO stuInfo) {
        try {
            Integer flag = userMapper.UpdateStuInfo(stuInfo);
            if (flag > 0) {
                return Result.success();
            } else {
                return Result.error("信息修改失败!");
            }
        }catch (Exception e){
            throw new ServiceException(Constants.CODE_600,"系统错误");
        }
    }

    @Override
    public Result queryTcInfo(String tcId) {
        TeacherInfo teacherInfo = userMapper.queryTcInfo(tcId);
        TcInfoDTO tcInfoDTO = new TcInfoDTO();
        if(teacherInfo != null){
            BeanUtil.copyProperties(teacherInfo,tcInfoDTO,true);
        }
        return Result.success(tcInfoDTO);
    }

    @Override
    public Result updateTcInfo(TcInfoDTO tcInfo) {
        try {
            Integer flag = userMapper.UpdateTcInfo(tcInfo);
            if (flag > 0) {
                return Result.success();
            } else {
                return Result.error("信息修改失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(Constants.CODE_600,"系统错误");
        }
    }


    /**
     * 查询用户账户
     */

    @Override
    public Result queryUserList(UserRequest userRequest) {
        PageHelper.startPage(userRequest.getPageNum(),userRequest.getPageSize());
        List<User> userList = userMapper.getUserListByCond(userRequest);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        return Result.success(userPageInfo);
    }


    @Override
    public Result insertUser(User user) {
        int count = userMapper.insert(user);
        if(count > 0) {
            return Result.success();
        }else {
            return  Result.error("添加失败！");
        }
    }

    @Override
    public Result updateUser(User user) {
        int count = userMapper.update(user);
        if(count > 0){
            return Result.success();
        }else {
            return Result.error("更新失败!");
        }
    }
}
