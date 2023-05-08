package com.example.ncov_h.controller;



import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.HesuanRequest;
import com.example.ncov_h.entity.Hesuan;
import com.example.ncov_h.service.HesuanService;
import com.example.ncov_h.util.DateUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/hesuan")
public class HesuanController {
    
    @Autowired
    private HesuanService hesuanService;
    
    @Value("${ImagesFilePath}")
    private String imagesFilePath;
    
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(MultipartFile file)throws Exception{
        HashMap<String, Object> resultMap = new HashMap<>();
        if(!file.isEmpty()){
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDatePath() + suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(imagesFilePath+newFileName));
            resultMap.put("code",0);
            resultMap.put("msg","上传成功");
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("title",newFileName);
            dataMap.put("src","images/"+newFileName);
            resultMap.put("data",dataMap);
        }
        return resultMap;
    }
    
    @GetMapping("/list")
    public Result queryHesuan(HesuanRequest hesuanRequest){
        return hesuanService.queryAll(hesuanRequest);
    }
    
    @PostMapping("/add")
    public Result addHesuan(@RequestBody Hesuan hesuan){
        return hesuanService.addHesuan(hesuan);
    }
    
    @PostMapping("/update")
    public Result updateHesuan(@RequestBody Hesuan hesuan){
        return hesuanService.updateHesuan(hesuan);
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteHesuan(@PathVariable String id){
        return hesuanService.deleteHesuan(id);
    }
    
    
    
    
    
}
