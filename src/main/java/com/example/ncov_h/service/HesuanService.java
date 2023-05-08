package com.example.ncov_h.service;

import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.HesuanRequest;
import com.example.ncov_h.entity.Hesuan;

public interface HesuanService {
    
    Result queryAll(HesuanRequest hesuanRequest);
    
    Result addHesuan(Hesuan hesuan);
    
    Result updateHesuan(Hesuan hesuan);
    
    Result deleteHesuan(String id);
    
}
