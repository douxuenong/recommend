package cn.shawn.recommend.dao;

import cn.shawn.recommend.entity.Movie;
import cn.shawn.recommend.entity.UserLike;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author shawn
 * 电影
 */
@Mapper
@Repository
public interface MovieMapper {

    /**
     * TODO 热门电影是否单独建表
     * 获取热门电影
     * @return 热门电影列表
     */
    @Select("select * from movie")
    List<Movie> getHotMovie();


    /**
     * 添加用户喜爱
     * @param userLike
     * @return 修改行数
     */
    @Insert("insert into user_like(user_id,movie_id) values (#{userID},#{movieID})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int addLikeMovie(UserLike userLike);

    /**
     * 简单的模糊搜索，返回包含输入字段的电影
     * @param movieName 输入的电影名
     * @return 符合条件的所有电影
     */
    @Select("select * from movie where movieName like #{movieName}")
    List<Movie> searchMovie(String movieName);
}
