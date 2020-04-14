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
     * TODO 集成推荐模块
     * 获取用户推荐电影
     * @return 推荐电影列表
     */
    @Select("select * from movie")
    List<Movie> getRecommendMovie();

    /**
     * 添加用户喜爱
     * @param userLike
     * @return 修改行数
     */
    @Insert("insert into user_like(user_id,movie_id) values (#{userID},#{movieID})")
    @Options(useGeneratedKeys=true,keyProperty="id")
    int addLikeMovie(UserLike userLike);
}
