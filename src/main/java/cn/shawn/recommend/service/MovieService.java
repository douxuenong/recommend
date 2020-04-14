package cn.shawn.recommend.service;

import cn.shawn.recommend.entity.Movie;

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
    List<Movie> getHotMovie();

    /**
     * 获取用户推荐电影
     * @return 推荐电影
     */
    List<Movie> getRecommendMovie();

    /**
     * 存储用户喜爱电影
     * @param movieList 用户喜爱电影id列表
     */
    void addLikeMovie(Long[] movieList);
}
