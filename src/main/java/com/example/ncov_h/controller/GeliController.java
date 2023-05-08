package com.example.ncov_h.controller;


import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.GeliRequest;
import com.example.ncov_h.entity.Geli;
import com.example.ncov_h.service.GeliService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/geli")
public class GeliController {
    
    @Autowired
    private GeliService geliService;
    
    
    @GetMapping("/list")
    public Result queryList(GeliRequest geliRequest){
        return geliService.queryList(geliRequest);
    }
    
    @PostMapping("/add")
    public Result addGeli(@RequestBody Geli geli){
        return geliService.addGeli(geli);
    }
    
    @PostMapping("/update")
    public Result updateGeli(@RequestBody Geli geli){
        return geliService.updateGeli(geli);
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteGeli(@PathVariable Integer id){
        return geliService.deleteGeli(id);
    }
    
}
