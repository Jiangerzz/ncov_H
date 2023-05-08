package com.example.ncov_h.mapper;


import com.example.ncov_h.controller.request.QingjiaRequest;
import com.example.ncov_h.entity.Qingjia;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QingjiaMapper {
    
    @Select("<script>" +
            "select * from t_wcqj where 1 = 1" +
            "<if test='username != null and username != \"\"'> and username = #{username}</if>" +
            "<if test='spzt != null and spzt != \"\"'> and spzt = #{spzt} </if>" +
            "</script>")
    List<Qingjia> queryQingjia(QingjiaRequest qingjiaRequest);
    
    @Insert("insert into t_wcqj(username,name,qjsy,spzt,kssj,jssj,czsj) values(#{username},#{name},#{qjsy},#{spzt},#{kssj},#{jssj},#{czsj})")
    Integer addQingjia(Qingjia qingjia);
    
    @Update("update t_wcqj set kssj = #{kssj},jssj = #{jssj},czsj = #{czsj},qjsy = #{qjsy}")
    Integer updateQingjia(Qingjia qingjia);
    
    @Delete("delete from t_wcqj where id = #{id}")
    Integer deleteQingjia(String id);
    
    
    @Update("update t_wcqj set spzt = #{spzt} where id = #{id}")
    Integer shenpi(Qingjia qingjia);
    
    @Select("select spzt from t_wcqj where id = #{id}")
    String getSpzt(String id);
}
