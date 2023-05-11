package com.example.ncov_h.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.RoleRequest;
import com.example.ncov_h.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface RoleService extends IService<Role> {

    Result getRole(RoleRequest request);
    
    Integer saveRole(Role role);
    
    Integer deleteRole(String id);
    
    Integer updateRole(Role role);

    void setRoleMenu(Integer roleId, List<Integer> menuIds);

    List<Integer> getRoleMenu(String roleId);
    
}
