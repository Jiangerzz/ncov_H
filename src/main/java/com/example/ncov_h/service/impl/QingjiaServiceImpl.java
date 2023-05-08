package com.example.ncov_h.service.impl;

import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.QingjiaRequest;
import com.example.ncov_h.entity.Geli;
import com.example.ncov_h.entity.Qingjia;
import com.example.ncov_h.exception.ServiceException;
import com.example.ncov_h.mapper.QingjiaMapper;
import com.example.ncov_h.service.QingjiaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class QingjiaServiceImpl implements QingjiaService {
    
    @Autowired
    private QingjiaMapper qingjiaMapper;

    @Override
    public Result queryQingjia(QingjiaRequest qingjiaRequest) {
        PageHelper.startPage(qingjiaRequest.getPageNum(),qingjiaRequest.getPageSize());
        List<Qingjia> qingjiaList = qingjiaMapper.queryQingjia(qingjiaRequest);
        PageInfo<Qingjia> qingjiaPageInfo = new PageInfo<>(qingjiaList);
        return Result.success(qingjiaPageInfo);
    }

    @Override
    public Result addQingjia(Qingjia qingjia) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = simpleDateFormat.format(new Date());
        qingjia.setSpzt("1");
        try {
            date = simpleDateFormat.parse(sDate);
            qingjia.setCzsj(date);
            Integer integer = qingjiaMapper.addQingjia(qingjia);
            if (integer > 0) {
                return Result.success();
            } else {
                return Result.error("请假失败!");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException("请假异常,请联系管理员!");
        }
    }

    @Override
    public Result updateQingjia(Qingjia qingjia) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = simpleDateFormat.format(new Date());
        try{
            date = simpleDateFormat.parse(sDate);
            qingjia.setCzsj(date);
            Integer count = qingjiaMapper.updateQingjia(qingjia);
            if(count > 0){
                return Result.success();
            }else {
                return Result.error("修改失败!");
            }
        }catch (Exception e){
            throw new ServiceException("修改异常，请联系管理员!");
        }
    }

    @Override
    public Result deleteQingjia(String id) {
        try {
            Integer count = qingjiaMapper.deleteQingjia(id);
            if (count > 0) {
                return Result.success();
            } else {
                return Result.error("删除失败!");
            }
        } catch (Exception e){
            throw new ServiceException("删除异常,请联系管理员!");
        }
    }

    @Override
    public Result shenpi(Qingjia qingjia) {
        try{
            String spzt = qingjiaMapper.getSpzt(qingjia.getId());
            if("1".equals(spzt)){
                Integer count = qingjiaMapper.shenpi(qingjia);
                if(count > 0 ){
                    return Result.success();
                }else {
                    return Result.error("审批失败");
                }
            }else{
                return Result.error("不可重复审批");
            }
        }catch (Exception e){
            throw new ServiceException("审批异常!");
        }
    }
}
