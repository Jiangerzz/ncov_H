package com.example.ncov_h.service.impl;

import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.PaRequest;
import com.example.ncov_h.entity.Pa;
import com.example.ncov_h.exception.ServiceException;
import com.example.ncov_h.mapper.PaMapper;
import com.example.ncov_h.service.PaService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class PaServiceImpl implements PaService {

    @Autowired
    private PaMapper paMapper;
    
    @Override
    public Result queryAllList(PaRequest paRequest) {
        try {
            PageHelper.startPage(paRequest.getPageNum(), paRequest.getPageSize());
            List<Pa> pas = paMapper.queryAllList(paRequest);
            PageInfo<Pa> paPageInfo = new PageInfo<>(pas);
            return Result.success(paPageInfo);
        } catch (Exception e){
            throw new ServiceException("查询异常");
        }
    }

    @Override
    public Result queryByCondition(PaRequest paRequest) {
        try{
            PageHelper.startPage(paRequest.getPageNum(),paRequest.getPageSize());
            List<Pa> pas = paMapper.queryByCondition(paRequest);
            PageInfo<Pa> paPageInfo = new PageInfo<>(pas);
            return Result.success(paPageInfo);
        }catch (Exception e){
            throw new ServiceException("模糊查询异常");
        }
    }

    @Override
    public Result deletePa(String id) {
        try{
            Integer count = paMapper.deletePa(id);
            if(count > 0){
                return Result.success();
            } else {
                return Result.error("删除失败!");
            }
        }catch (Exception e){
            throw new ServiceException("删除异常!");
        }
    }

    @Override
    public Result insertPa(Pa pa) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String sDate = simpleDateFormat.format(new Date());
        try{
            date = simpleDateFormat.parse(sDate);
            pa.setFbsj(date);
            Integer count = paMapper.insertPa(pa);
            if(count > 0){
                return Result.success();
            }else {
                return Result.error("添加失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("添加异常!");
        }
    }
}
