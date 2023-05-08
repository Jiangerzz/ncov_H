package com.example.ncov_h.service;

import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.GeliRequest;
import com.example.ncov_h.entity.Geli;

public interface GeliService {
    
    Result queryList(GeliRequest geliRequest);
    
    Result addGeli(Geli geli);
    
    Result updateGeli(Geli geli);
    
    Result deleteGeli(Integer id);
    
}
