package com.example.ncov_h.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.ncov_h.common.Constants;
import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.DakaRequest;
import com.example.ncov_h.entity.Daka;
import com.example.ncov_h.exception.ServiceException;
import com.example.ncov_h.mapper.DakaMapper;
import com.example.ncov_h.service.DakaService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class DakaServiceImpl extends ServiceImpl<DakaMapper,Daka> implements DakaService{
    
    @Autowired
    private DakaMapper dakaMapper;

    @Override
    public Result getDakaList(DakaRequest dakaRequest) {
        String roleId = dakaRequest.getRoleId();
        PageHelper.startPage(dakaRequest.getPageNum(),dakaRequest.getPageSize());
        PageInfo<Daka> dakaPageInfo = null;
        if("1".equals(roleId)){
            String username = dakaRequest.getUsername();
            if("admin".equals(username)){
                dakaRequest.setUsername(null);
            }
            List<Daka> dakaList = dakaMapper.getDakaList(dakaRequest);
            dakaPageInfo = new PageInfo<>(dakaList);
        }else {
            List<Daka> dakaListByUsername = dakaMapper.getDakaListByUsername(dakaRequest);
            dakaPageInfo = new PageInfo<>(dakaListByUsername);
        }
        return Result.success(dakaPageInfo);
    }

    @Override
    public Result addDaka(Daka daka) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = simpleDateFormat.format(new Date());
        try{
            date = simpleDateFormat.parse(sDate);
            daka.setTjsj(date);
            Integer flag = dakaMapper.queryByNameAndDate(daka);
            if(flag == 0){
                Integer count = dakaMapper.addDaka(daka);
                if(count > 0){
                    return Result.success();
                }else {
                    return Result.error("打卡失败!");
                }
            }else {
                return Result.error(Constants.CODE_400,"不可重复打卡!");
            }
           
        } catch (Exception e){
            throw new ServiceException("打卡异常:"+e.getMessage());
        }

    }

    @Override
    public Result updateDaka(Daka daka) {
        Date tjsj = daka.getTjsj();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = simpleDateFormat.format(new Date());
        try {
            Date date = simpleDateFormat.parse(sDate);
            if(date.equals(tjsj)){
                Integer count = dakaMapper.updateDaka(daka);
                if(count > 0){
                    return Result.success();
                }else {
                    return Result.error("修改失败!");
                }
            }else {
                return Result.error("只能修改当天记录!");
            }
        } catch (Exception e){
            throw new ServiceException("重新打卡失败:" + e.getMessage());
        }
    }
    
    
}
