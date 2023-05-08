package com.example.ncov_h.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.ncov_h.controller.dto.StuInfoDTO;
import com.example.ncov_h.controller.dto.TcInfoDTO;
import com.example.ncov_h.controller.request.UserRequest;
import com.example.ncov_h.entity.StudentInfo;
import com.example.ncov_h.entity.TeacherInfo;
import com.example.ncov_h.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    
    @Select("select * " +
            "from t_stu " +
            "where stu_id = #{stuId};")
    public StudentInfo queryStuInfo(@Param("stuId") String stuId);
    
    
    @Update("update t_stu " +
            "set name = #{name}, sex = #{sex}, age = #{age}, phone = #{phone}, class_name = #{className}, " +
            "xueyuan_name = #{xueyuanName} " +
            "where stu_id = #{stuId}")
    public Integer UpdateStuInfo(StuInfoDTO stuInfo);
    
    @Select("select name from t_stu where stu_id = #{stuId}")
    public String getStuNameById(@Param("stuId") String stuId);

    @Select("select name from t_tc where tc_id = #{tcId}")
    public String getTcNameById(@Param("tcId") String tcId);

    
    @Select("select * from t_tc where tc_id = #{tcId}")
    public TeacherInfo queryTcInfo(@Param("tcId") String tcId);
    
    @Update("update t_tc " +
            "set name = #{name}, sex = #{sex}, age = #{age}, zc = #{zc}, phone = #{phone} " +
            "where tc_id = #{tcId}")
    public Integer UpdateTcInfo(TcInfoDTO tcInfoDTO);
    
    @Select("<script>" +
            "select id,username,password,role_id from t_user where 1 = 1 " +
            "<if test = 'username != null and username != \"\"'> and username = #{username} </if>" +
            "<if test = 'roleId != null and roleId != \"\"'> and role_id = #{roleId} </if>" +
            "</script>")
    public List<User> getUserListByCond(UserRequest userRequest);
    
    
    @Insert("insert into t_user(username,password,role_id) values (#{username},#{password},#{roleId})")
    public int insert(User user);

    
    @Update("update t_user set password = #{password}, role_id = #{roleId} where username = #{username}")
    public int update(User user);
    
    @Select("select role_id from t_user where username = #{username}")
    public String getRoleId(String username);
    
    @Select("select name from t_stu where stu_id = #{username}")
    public String getStuName(String username);
    
    @Select("select name from t_tc where tc_id = #{username}")
    public String getTcName(String username);
}
