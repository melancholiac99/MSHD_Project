package com.example.mshd_project.core.dao;

import com.example.mshd_project.core.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

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
    @Select("select count(*) from tb_user" )
    public int getTotalUsers(Map map);
    @SelectProvider(type = UserSqlProvider.class, method = "findUserListSql")
    List<User> findUserList(Map map);

    class UserSqlProvider {
        public String findUserListSql(Map<String, Object> params) {
            StringBuilder sql = new StringBuilder("SELECT user_id AS userId, user_name AS userName, password, status, regist_time AS registTime FROM tb_user ");
            sql.append("ORDER BY user_id DESC ");
            if (params.get("start") != null && params.get("limit") != null) {
                sql.append("LIMIT #{start}, #{limit}");
            }
            return sql.toString();
        }
    }
}
