package cn.shawn.recommend.service;

import cn.shawn.recommend.entity.Movie;
import com.alibaba.fastjson.JSONArray;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;
import java.util.List;

/**
 * 电影相关
 * @author shawn
 */
public interface MovieService {
    /**
     * 获取热门电影
     * @return 热门电影
     */
    String getHotMovie();

    /**
     * 存储用户喜爱电影
     * @param movieList 用户喜爱电影id列表
     */
    void addLikeMovie(Long[] movieList);

    /**
     * 简单的模糊搜索，返回包含输入字段的电影
     * @param movieName 输入的电影名
     * @return 符合条件的所有电影JSON
     */
    String searchMovie(String movieName);

    /**
     * 通过调用推荐模块获取推荐电影
     * @return 推荐电影JSON
     */
    JSONArray getRecommendMovie();
}
