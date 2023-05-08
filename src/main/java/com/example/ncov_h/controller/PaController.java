package com.example.ncov_h.controller;


import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.PaRequest;
import com.example.ncov_h.entity.Pa;
import com.example.ncov_h.service.PaService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/pa")
public class PaController {
    
    @Autowired
    private PaService paService;
    
    @GetMapping
    public Result queryList(PaRequest paRequest){
        return paService.queryAllList(paRequest);
    }
    
    
    @GetMapping("/query")
    public Result queryByCondition(PaRequest paRequest){
        return paService.queryByCondition(paRequest);
    }
    
    @PostMapping("/add")
    public Result addPa(@RequestBody Pa pa){
        return paService.insertPa(pa);
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deletePa(@PathVariable String id){
        return paService.deletePa(id);
    }
    
}
