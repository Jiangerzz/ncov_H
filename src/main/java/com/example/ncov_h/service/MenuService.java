package com.example.ncov_h.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ncov_h.entity.Menu;

import java.util.List;

public interface MenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
    
}
