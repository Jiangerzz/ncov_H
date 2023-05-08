package com.example.ncov_h.service.impl;

import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.GeliRequest;
import com.example.ncov_h.entity.Geli;
import com.example.ncov_h.exception.ServiceException;
import com.example.ncov_h.mapper.GeliMapper;
import com.example.ncov_h.service.GeliService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@Service
public class GeliServiceImpl implements GeliService {
    
    @Autowired
    private GeliMapper geliMapper;

    @Override
    public Result queryList(GeliRequest geliRequest) {
        List<Geli> geliList = null;
        PageHelper.startPage(geliRequest.getPageNum(),geliRequest.getPageSize());
        geliList = geliMapper.queryGeliList(geliRequest);
        PageInfo<Geli> dakaPageInfo = new PageInfo<>(geliList);
        return Result.success(dakaPageInfo);
    }

    @Override
    public Result addGeli(Geli geli) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = simpleDateFormat.format(new Date());
        try{
            date = simpleDateFormat.parse(sDate);
            geli.setCzsj(date);
            Integer count = geliMapper.addGeli(geli);
            if(count > 0){
                return Result.success();
            }else {
                return Result.error("添加隔离信息失败!");
            }
        }catch (Exception e){
            throw new ServiceException("添加隔离信息异常:" + e.getMessage());
        }
    }

    @Override
    public Result updateGeli(Geli geli) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = simpleDateFormat.format(new Date());
        try{
            date = simpleDateFormat.parse(sDate);
            geli.setCzsj(date);
            Integer cout = geliMapper.updateGeli(geli);
            if(cout > 0){
                return Result.success();
            }else {
                return Result.error("修改隔离信息失败!");
            }
        }catch (Exception e){
            throw new ServiceException("修改隔离信息异常:" + e.getMessage());
        }
    }

    @Override
    public Result deleteGeli(Integer id) {
        try {
            Integer count = geliMapper.deleteGeli(id);
            if(count > 0){
                return Result.success();
            }else {
                return Result.error("删除失败,请刷新后重试!");
            }
        }catch (Exception e){
            throw new ServiceException("删除异常:"+e.getMessage());
        }
    }
}
