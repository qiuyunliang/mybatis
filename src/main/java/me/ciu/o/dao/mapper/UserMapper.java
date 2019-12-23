package me.ciu.o.dao.mapper;

import me.ciu.o.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.LoggingCache;
import org.springframework.stereotype.Service;

@Mapper
@Service
@CacheNamespace(eviction = LoggingCache.class)
public interface UserMapper {
    @Select("SELECT * FROM USER WHERE id = #{id}")
    User selectUser(int id);

    @Insert("INSERT INTO USER (username, password, salary, age)"
            + "VALUES (#{userName}, #{password}, #{salary}, #{age})")
    @Options(useGeneratedKeys = true) // 主键自增，User对象的id属性映射的列ID为主键
    Long insert(User user);
}