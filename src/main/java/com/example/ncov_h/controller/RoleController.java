package com.example.ncov_h.controller;


import com.example.ncov_h.common.Result;
import com.example.ncov_h.controller.request.RoleRequest;
import com.example.ncov_h.entity.Role;
import com.example.ncov_h.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/role")
public class RoleController {
    
    @Autowired
    RoleService roleService;
    
    @GetMapping("/page")
    public Result getRole(RoleRequest request){
        return roleService.getRole(request);
    }
    
    @PostMapping("/save")
    public Result saveRole(@RequestBody Role role){
        Integer flag = roleService.saveRole(role);
        if(flag > 0){
            return Result.success();
        }else {
            return Result.error("添加失败");
        }
    }

    @PostMapping("/update")
    public Result updateRole(@RequestBody Role role){
        Integer flag = roleService.updateRole(role);
        if(flag > 0){
            return Result.success();
        }else {
            return Result.error("修改失败");
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public Result deleteRole(@PathVariable("id") String id){
        Integer flag = roleService.deleteRole(id);
        if(flag > 0){
            return Result.success();
        }else {
            return Result.error("删除失败");
        }
    }

    @PostMapping("/roleMenu/{roleId}")
    public Result roleMenu(@PathVariable Integer roleId, @RequestBody List<Integer> menuIds) {
        roleService.setRoleMenu(roleId, menuIds);
        return Result.success();
    }

    @GetMapping("/roleMenu/{roleId}")
    public Result getRoleMenu(@PathVariable String roleId) {
        return Result.success( roleService.getRoleMenu(roleId));
    }
    
}
