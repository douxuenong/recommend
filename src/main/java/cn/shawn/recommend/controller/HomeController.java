package cn.shawn.recommend.controller;

import cn.shawn.recommend.bean.WebResponse;
import cn.shawn.recommend.entity.Movie;
import cn.shawn.recommend.entity.UserRating;
import cn.shawn.recommend.service.impl.MovieServiceImpl;
import cn.shawn.recommend.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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
    @GetMapping(value = "/home")
    public WebResponse homePage(){
         return WebResponse.success(movieService.getHotMovie());
    }

    /**
     * 获取推荐电影
     * @return 推荐电影列表
     */
    @GetMapping(value = "/home/recommend")
    public WebResponse getrecommendMovie(){
        String recommendMovie = JSONArray.toJSONString(movieService.getRecommendMovie());
        return WebResponse.success(recommendMovie);
    }

    /**
     * 提交用户喜爱的电影
     */
    @PostMapping(value = "/home/like")
    public WebResponse addUserLike(@RequestBody JSONArray jsonArray){
        List<Long> userLikeList = jsonArray.toJavaList(Long.class);
        movieService.addLikeMovie(userLikeList);
        return WebResponse.success();
    }

    /**
     * 电影简单搜索
     */
    @GetMapping(value = "/home/search")
    public WebResponse searchMovie(@RequestParam(value = "page",defaultValue = "1") Integer pageNumber,
                                   @RequestParam(value = "keyword") String keyword){
        PageInfo<Movie> moviePageInfo = movieService.searchMovie(keyword,pageNumber,15);
        return WebResponse.success(moviePageInfo);
    }


    /**
     * 获取电影评论
     * @param movieId
     * @param pageNumber
     */
    @GetMapping(value = "/home/movie/comment")
    public WebResponse getComment(@RequestParam(value = "movieId") Long movieId,
                                  @RequestParam(value = "page",defaultValue = "1") Integer pageNumber){
        PageInfo<UserRating> commentPageInfo = movieService.getComment(movieId,pageNumber,15);
        return WebResponse.success(commentPageInfo);
    }

    /**
     * 添加用户评论
     * @param params
     * @return
     */
    @PostMapping(value = "/home/movie/comment")
    public WebResponse addComment(@RequestBody Map<String,Object> params){
        Long movieId = (Long)params.get("movieId");
        double userMovieScore = (double)params.get("userMovieScore");
        String userComment = (String)params.get("userComment");
        return WebResponse.success(movieService.addComment(movieId, userMovieScore, userComment));
    }
}
