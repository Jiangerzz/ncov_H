package com.example.ncov_h.mapper;


import com.example.ncov_h.controller.request.GeliRequest;
import com.example.ncov_h.entity.Geli;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GeliMapper {
    
    @Select("<script>" +
            "select * from t_glxx where 1=1" +
            "<if test='username != null and username != \"\"'> and username = #{username} </if>" +
            "</script>")
    public List<Geli> queryGeliList(GeliRequest geliRequest);

    @Select("select * from t_glxx ")
    public List<Geli> queryGeliListAll(GeliRequest geliRequest);
    
    @Insert("insert into t_glxx(username,kssj,jssj,remark,czsj) values(#{username},#{kssj},#{jssj},#{remark},#{czsj}) ")
    public Integer addGeli(Geli geli);
    
    @Update("update t_glxx set kssj = #{kssj},jssj = #{jssj},remark = #{remark},czsj = #{czsj} where id = #{id}")
    public Integer updateGeli(Geli geli);
    
    @Delete("delete from t_glxx where id = #{id}")
    public Integer deleteGeli(Integer id);
}
