package com.example.ncov_h.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ncov_h.entity.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    
    @Select("<script>" +
            "select id,name,flag from t_role " +
            "where deleted = 0 " +
            "<if test = 'name != null and name != \"\"'> and name like concat(#{name},'%')" +
            "</if> " +
            "order by id" +
            "</script>")
    List<Role> getRoleList(String name);
    
    @Insert("insert into t_role(name,flag,deleted) values(#{name},#{flag},0)")
    Integer save(Role role);
    
    @Update("update t_role set deleted = 1 where id = #{id}")
    Integer delete(String id);
    
    @Update("update t_role set name = #{name}, flag = #{flag} where id = #{id}")
    Integer update(Role role);
    
}
