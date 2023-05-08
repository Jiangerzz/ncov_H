package com.example.ncov_h.controller;


import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.QingjiaRequest;
import com.example.ncov_h.entity.Qingjia;
import com.example.ncov_h.exception.ServiceException;
import com.example.ncov_h.service.QingjiaService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/wcqj")
public class QingjiaController {
    
    @Autowired
    private QingjiaService qingjiaService;
    
    @GetMapping
    public Result queryList(QingjiaRequest qingjiaRequest){
        return qingjiaService.queryQingjia(qingjiaRequest);
    }
    
    @PostMapping("/add")
    public Result addQingjia(@RequestBody Qingjia qingjia){
        return qingjiaService.addQingjia(qingjia);
    }
    
    @PostMapping("/update")
    public Result updateQingjia(@RequestBody Qingjia qingjia){
        return qingjiaService.updateQingjia(qingjia);
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteQingjia(@PathVariable("id") String id){
        return qingjiaService.deleteQingjia(id);
    }
    
    @PostMapping("/shenpi")
    public Result shenpiQingjia(@RequestBody Qingjia qingjia){
        return qingjiaService.shenpi(qingjia);
    }
}
