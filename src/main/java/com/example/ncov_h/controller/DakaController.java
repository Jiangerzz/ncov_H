package com.example.ncov_h.controller;


import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.DakaRequest;
import com.example.ncov_h.entity.Daka;
import com.example.ncov_h.service.DakaService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/daka")
public class DakaController {
    
    @Resource
    private DakaService dakaService;
    
    @GetMapping
    public Result getList(DakaRequest dakaRequest){
        return dakaService.getDakaList(dakaRequest);
    }
    
    @PostMapping("/add")
    public Result addDaka(@RequestBody Daka daka){
        return dakaService.addDaka(daka);
    }
    
    @PostMapping("/update")
    public Result updateDaka(@RequestBody Daka daka){
        return dakaService.updateDaka(daka);
    }
    
}
