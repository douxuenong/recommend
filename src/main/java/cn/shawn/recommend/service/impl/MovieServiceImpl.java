package cn.shawn.recommend.service.impl;

import cn.shawn.recommend.dao.MovieMapper;
import cn.shawn.recommend.dao.UserMapper;
import cn.shawn.recommend.entity.Movie;
import cn.shawn.recommend.entity.User;
import cn.shawn.recommend.entity.UserLike;
import cn.shawn.recommend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 电影相关
 * @author shawn
 */
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;

    private final UserMapper userMapper;

    public MovieServiceImpl(MovieMapper movieMapper, UserMapper userMapper) {
        this.movieMapper = movieMapper;
        this.userMapper = userMapper;
    }

    @Override
    public List<Movie> getHotMovie() {
        return movieMapper.getHotMovie();
    }

    @Override
    public List<Movie> getRecommendMovie() {
        return movieMapper.getRecommendMovie();
    }

    @Override
    public void addLikeMovie(Long[] movieList) {
        String userName =(String)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userMapper.getUserByName(userName);
        for (Long movieId : movieList ) {
            movieMapper.addLikeMovie(new UserLike(user.getId(),movieId));
        }
    }
}