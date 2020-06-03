package cn.shawn.recommend.dao;

import cn.shawn.recommend.entity.UserRating;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
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
    @Insert({"insert into user_rating(movie_id,user_movieScore,user_id,user_comment) values (#{movie_id},#{user_movieScore},#{user_id},#{user_comment})"})
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int add(UserRating userRating);

    /**
     * 查询某部电影的评论
     * @param movieID 电影ID
     * @return 该电影的评论列表
     */
    @Select("select * from user_rating where movie_id=#{movieID}")
    Page<UserRating> getComment(Long movieID);
}
