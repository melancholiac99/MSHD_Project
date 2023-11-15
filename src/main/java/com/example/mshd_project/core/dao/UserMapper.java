package com.example.mshd_project.core.dao;

import com.example.mshd_project.core.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 这个mapper文件使用了ibatis的注解开发来写sql，
 * 与我前几个功能用的编码规范不太适配。
 */
@Mapper
public interface UserMapper {

    @Select("select * from tb_user where user_name = #{userName}")
    public User getUserByName(String userName);


    @Insert("insert into tb_user (user_name,password,status,regist_time)  values (#{userName}, #{password}, #{status}, #{registTime})")
    public int addUser(User user);


    @Update("update  tb_user set password = #{newpassword}  where user_name = #{username}")
    public int updateUser(String username, String newpassword);

}
