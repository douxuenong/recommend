package cn.shawn.recommend.service.impl;

import cn.shawn.recommend.bean.SockettoPython;
import cn.shawn.recommend.dao.MovieMapper;
import cn.shawn.recommend.dao.UserMapper;
import cn.shawn.recommend.entity.Movie;
import cn.shawn.recommend.entity.User;
import cn.shawn.recommend.entity.UserLike;
import cn.shawn.recommend.service.MovieService;
import com.alibaba.fastjson.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
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

    public MovieServiceImpl(MovieMapper movieMapper, UserMapper userMapper) {
        this.movieMapper = movieMapper;
        this.userMapper = userMapper;
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
    public void addLikeMovie(Long[] movieList) {
        String userName =(String) getContext().getAuthentication().getPrincipal();
        User user = userMapper.getUserByName(userName);
        for (Long movieId : movieList ) {
            movieMapper.addLikeMovie(new UserLike(user.getId(),movieId));
        }
    }

    @Override
    public String searchMovie(String keyword) {
        //加入通配符
        String movieName = "%" + keyword + "%";
        return JSONArray.toJSONString(movieMapper.searchMovie(movieName));
    }


}
