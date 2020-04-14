package cn.shawn.recommend.dao;

import cn.shawn.recommend.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author shawn
 * 用户
 */
@Mapper
@Repository
public interface UserMapper {


    /**
     * 根据用户名得到用户
     * @param username
     * @return
     */
    @Select("select * from user where userName = #{username}")
    User getUserByName(String username);


    /**
     * 存储新用户
     * @param  user
     * @return int
     */
    @Insert("insert into user(username,password) values (#{username},#{password})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int add(User user);



}
