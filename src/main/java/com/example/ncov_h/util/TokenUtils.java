package com.example.ncov_h.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.ncov_h.controller.dto.LoginDTO;
import com.example.ncov_h.entity.User;
import com.example.ncov_h.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@Slf4j
public class TokenUtils{
    
    private static UserService staticUserSerive;
    
    @Resource
    private UserService userService;
    
    @PostConstruct
    public void setUserService(){
        staticUserSerive = userService;
    }
    
    /**
     * 生成token
     */
    public static String genToken(String username, String sign){
        return JWT.create().withAudience(username) //将admin id保存到token里面，作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //两小时后token过期
                .sign(Algorithm.HMAC256(sign)); //以password作为token的密钥。
    }


    /**
     * 获取当前登录的用户信息
     *
     * @return admin对象
     * /admin?token=xxx
     */
    public static User getCurrentAdmin() {
        String token;
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            token = request.getHeader("token");  //第一次获取
            if(StrUtil.isBlank(token)){
                token = request.getParameter("token");  //第二次获取
            }
            if(StrUtil.isBlank(token)){
                log.error("获取当前登录信息失败,token={}",token);
                return null;
            }
            String userName = JWT.decode(token).getAudience().get(0);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username",userName);
            return staticUserSerive.getOne(queryWrapper);
        }catch (Exception e){
            log.error("获取当前登录信息失败, token={}",e);
            return null;
        }
    }

}