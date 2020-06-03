package cn.shawn.recommend.service.impl;

import cn.shawn.recommend.bean.SockettoPython;
import cn.shawn.recommend.dao.MovieMapper;
import cn.shawn.recommend.dao.RatingMapper;
import cn.shawn.recommend.dao.UserMapper;
import cn.shawn.recommend.entity.Movie;
import cn.shawn.recommend.entity.User;
import cn.shawn.recommend.entity.UserLike;
import cn.shawn.recommend.entity.UserRating;
import cn.shawn.recommend.service.MovieService;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;


/**
 * 电影相关
 * @author shawn
 */
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieMapper movieMapper;

    private final UserMapper userMapper;

    private final RatingMapper ratingMapper;

    public MovieServiceImpl(MovieMapper movieMapper, UserMapper userMapper, RatingMapper ratingMapper) {
        this.movieMapper = movieMapper;
        this.userMapper = userMapper;
        this.ratingMapper = ratingMapper;
    }

    @Override
    public String  getHotMovie() {

        return JSONArray.toJSONString(movieMapper.getHotMovie());
    }

    @Override
    public JSONArray getRecommendMovie() {
        //获取当前用户
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String id = userMapper.getUserByName(username).getId().toString();
        //发送给python服务端的用户名必需转化为byte[]
        byte[] b_hostID = id.getBytes();
        //调用与python服务端的通信方法
        SockettoPython sockettoPython = new SockettoPython();
        JSONArray res = sockettoPython.sendMessage(b_hostID);
        return res;
    }

    @Override
    public void addLikeMovie(List<Long> userLikeList) {
        String userName =(String) getContext().getAuthentication().getPrincipal();
        User user = userMapper.getUserByName(userName);
        long id = user.getId();
        for (Long movieId : userLikeList ) {
            movieMapper.addLikeMovie(new UserLike(id,movieId));
        }
    }

    @Override
    public int addComment(long movieId, double userMovieScore, String userComment) {
        String userName =(String) getContext().getAuthentication().getPrincipal();
        User user = userMapper.getUserByName(userName);
        long userId = user.getId();
        return movieMapper.addUserRating(movieId, userMovieScore, userId, userComment);
    }

    @Override
    public PageInfo<UserRating> getComment(long movieID,int pageNumber, int pageSize) {
        PageHelper.startPage(pageNumber,pageSize);
        Page<UserRating> comments = ratingMapper.getComment(movieID);
        PageInfo<UserRating> commentsPageInfo = new PageInfo<>(comments);
        return commentsPageInfo;
    }

    @Override
    public PageInfo<Movie> searchMovie(String keyword,int pageNumber, int pageSize) {
        //加入通配符
        String movieName = "%" + keyword + "%";
        PageHelper.startPage(pageNumber,pageSize);
        Page<Movie> movies = movieMapper.searchMovie(movieName);
        PageInfo<Movie> moviePageInfo = new PageInfo<>(movies);
        return moviePageInfo;
    }


}
