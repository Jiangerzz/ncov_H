package com.example.ncov_h.service;

import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.PaRequest;
import com.example.ncov_h.entity.Pa;

public interface PaService {
    
    Result queryAllList(PaRequest paRequest);
    
    Result queryByCondition(PaRequest paRequest);
    
    Result deletePa(String id);
    
    Result insertPa(Pa pa);
}
