package com.example.ncov_h.mapper;


import com.example.ncov_h.controller.request.PaRequest;
import com.example.ncov_h.entity.Pa;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PaMapper {
    
    @Select("select id,title,content,fbsj from t_ggxx")
    public List<Pa> queryAllList(PaRequest paRequest);
    
    @Select("select * from t_ggxx where title like CONCAT('%',#{title},'%')")
    List<Pa> queryByCondition(PaRequest paRequest);    
    
    @Update("update t_ggxx set title = #{title}, content = #{content}")
    Integer updtaePa(Pa pa);
    
    @Insert("insert into t_ggxx(title,content,fbsj) values(#{title},#{content},#{fbsj})")
    Integer insertPa(Pa pa);
    
    @Delete("delete from t_ggxx where id = #{id}")
    Integer deletePa(String id);
}
