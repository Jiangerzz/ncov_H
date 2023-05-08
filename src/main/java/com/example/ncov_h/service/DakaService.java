package com.example.ncov_h.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.DakaRequest;
import com.example.ncov_h.entity.Daka;


public interface DakaService extends IService<Daka> {
    
    Result getDakaList(DakaRequest dakaRequest);
    
    Result addDaka(Daka daka);
    
    Result updateDaka(Daka daka);
    
}
