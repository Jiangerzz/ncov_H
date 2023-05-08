package com.example.ncov_h.service.impl;

import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.HesuanRequest;
import com.example.ncov_h.entity.Geli;
import com.example.ncov_h.entity.Hesuan;
import com.example.ncov_h.mapper.HesuanMapper;
import com.example.ncov_h.service.HesuanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HesuanServiceImpl implements HesuanService {
    
    @Autowired
    private HesuanMapper hesuanMapper;
    
    @Override
    public Result queryAll(HesuanRequest hesuanRequest) {
        PageHelper.startPage(hesuanRequest.getPageNum(),hesuanRequest.getPageSize());
        List<Hesuan> hesuans = hesuanMapper.queryList(hesuanRequest);
        PageInfo<Hesuan> PageInfo = new PageInfo<>(hesuans);
        return Result.success(PageInfo);
    }

    @Override
    public Result addHesuan(Hesuan hesuan) {
        Integer count = hesuanMapper.addHesuan(hesuan);
        if(count > 0){
            return Result.success();
        }else {
            return Result.error("添加失败!");
        }
    }

    @Override
    public Result updateHesuan(Hesuan hesuan) {
        Integer count = hesuanMapper.updateHesuan(hesuan);
        if(count > 0){
            return Result.success();
        }else {
            return Result.error("修改失败!");
        }
    }

    @Override
    public Result deleteHesuan(String id) {
        Integer count = hesuanMapper.deleteHesuan(id);
        if(count > 0){
            return Result.success();
        }else {
            return Result.error("删除失败!");
        }
    }
}
