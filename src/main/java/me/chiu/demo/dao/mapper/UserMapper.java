package me.chiu.demo.dao.mapper;

import me.chiu.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select("SELECT * FROM USER WHERE id = #{id}")
    User selectUser(int id);

    @Insert("INSERT INTO USER (id, username, password, salary, age)"
            + "VALUES (#{id}, #{userName}, #{password}, #{salary}, #{age})")
    Long insert(User user);
}