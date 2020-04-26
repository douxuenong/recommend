package cn.shawn.recommend.dao;

import cn.shawn.recommend.entity.UserRating;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

/**
 * 用户评分
 * @author shawn
 */
@Mapper
@Repository
public interface RatingMapper {

    /**
     * 存储用户评分
     * @param userRating 用户评分实体
     * @return 修改行数
     */
    @Insert("insert into user_rating(movie_id,user_movieScore,user_id) values (#{movie_id},#{user_movieScore},#{user_id})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int add(UserRating userRating);
}
