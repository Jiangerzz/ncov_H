package com.example.ncov_h.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ncov_h.controller.request.DakaRequest;
import com.example.ncov_h.entity.Daka;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface DakaMapper extends BaseMapper<Daka> {
    
    
    @Select("<script>" +
            "select * from t_jkdk" +
            "<where>" +
            "<if test = 'username != null and username != \"\"'> and username = #{username} </if>" +
            "<if test = 'tjsj != null and tjsj != \"\"'> and tjsj = #{tjsj} </if>" +
            "</where>" +
            "</script>")
    public List<Daka> getDakaList(DakaRequest dakaRequest);


    @Select("<script>" +
            "select * from t_jkdk where username = #{username}"+
            "<if test = 'tjsj != null and tjsj != \"\"'> and tjsj = #{tjsj} </if>" +
            "</script>")
    public List<Daka> getDakaListByUsername(DakaRequest dakaRequest);
    
    @Insert("insert into t_jkdk(username,name,tw,jkm,status,tjsj) values (#{username},#{name},#{tw},#{jkm},#{status},#{tjsj})")
    public Integer addDaka(Daka daka);
    
    @Update("update t_jkdk set tw = #{tw},jkm = #{jkm},status = #{status} " +
            "where username = #{username} and tjsj = #{tjsj}")
    public Integer updateDaka(Daka daka);
    
    @Select("select count(*) from t_jkdk where username = #{username} and tjsj = #{tjsj}")
    public Integer queryByNameAndDate(Daka daka);
}
