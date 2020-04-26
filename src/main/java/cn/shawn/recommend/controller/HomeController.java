package cn.shawn.recommend.controller;

import cn.shawn.recommend.bean.WebResponse;
import cn.shawn.recommend.service.impl.MovieServiceImpl;
import cn.shawn.recommend.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * @author Shawn
 */
@RestController
public class HomeController {
    private final MovieServiceImpl movieService;
    private final UserServiceImpl userService;

    public HomeController(MovieServiceImpl movieService, UserServiceImpl userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    /**
     * 获取热门电影
     * @return 热门电影列表
     */
    @PostMapping(value = "/home")
    public String homePage(){
        return movieService.getHotMovie();
    }

    /**
     * 获取推荐电影
     * @return 推荐电影列表
     */
    @PostMapping(value = "/home/recommend")
    public JSONArray getrecommendMovie(){
        return movieService.getRecommendMovie();
    }

    /**
     * 提交用户喜爱的电影
     * @param userLikeList 用户喜爱电影编号数组
     * @return
     */
    @PostMapping(value = "/home/like")
    //TODO 确认是一次添加单个还是多个
    public WebResponse addUserLike(@RequestBody Long[] userLikeList){
        movieService.addLikeMovie(userLikeList);
        return WebResponse.success();
    }

    /**
     * 电影简单搜索
     * @return
     */
    @PostMapping(value = "/home/search")
    public String searchMovie(@RequestBody Map<String,Object> param){
        String movieName = (String)param.get("movieName");
        return movieService.searchMovie(movieName);
    }

}
