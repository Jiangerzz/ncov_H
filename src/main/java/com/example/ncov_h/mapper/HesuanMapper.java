package com.example.ncov_h.mapper;


import com.example.ncov_h.controller.request.HesuanRequest;
import com.example.ncov_h.entity.Hesuan;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface HesuanMapper {
    
    @Select("select * from t_hsjc")
    List<Hesuan> queryAll(HesuanRequest hesuanRequest);
    
    @Insert("insert into t_hsjc(username,cjsj,jcsj,jcjg,bz) values(#{username},#{cjsj},#{jcsj},#{jcjg},#{bz})")
    Integer addHesuan(Hesuan hesuan);
    
    @Update("update t_hsjc set cjsj = #{cjsj},jcsj = #{jcsj},jcjg = #{jcjg} where id = #{id}")
    Integer updateHesuan(Hesuan hesuan);
    
    @Delete("delete from t_hsjc where id = #{id}")
    Integer deleteHesuan(String id);

    @Select("<script>" +
            "select * from t_hsjc where 1=1" +
            "<if test='username != null and username != \"\"'> and username = #{username} </if>" +
            "</script>")
    List<Hesuan> queryList(HesuanRequest hesuanRequest);
}
