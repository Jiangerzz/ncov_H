package com.example.ncov_h.service;

import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.QingjiaRequest;
import com.example.ncov_h.entity.Qingjia;

public interface QingjiaService {
    
    Result queryQingjia(QingjiaRequest qingjiaRequest);
    
    Result addQingjia(Qingjia qingjia);
    
    Result updateQingjia(Qingjia qingjia);
    
    Result deleteQingjia(String id);
    
    Result shenpi(Qingjia qingjia);
    
}
