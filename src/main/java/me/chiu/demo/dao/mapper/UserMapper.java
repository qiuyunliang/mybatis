package me.chiu.demo.dao.mapper;

import me.chiu.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE id = #{id}")
    User selectUser(int id);

    @Insert("INSERT INTO USER (username, password, salary, age)"
            + "VALUES (#{userName}, #{password}, #{salary}, #{age})")
    @Options(useGeneratedKeys = true) // 主键自增，User对象的id属性映射的列ID为主键
    Long insert(User user);
}