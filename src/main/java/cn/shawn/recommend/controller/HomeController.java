package cn.shawn.recommend.controller;

import cn.shawn.recommend.bean.WebResponse;
import cn.shawn.recommend.entity.Movie;
import cn.shawn.recommend.service.impl.MovieServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Shawn
 */
@Api(tags = "主页")
@RestController(value = "/home")
public class HomeController {
    private final MovieServiceImpl movieService;

    public HomeController(MovieServiceImpl movieService) {
        this.movieService = movieService;
    }

    /**
     * @return 热门电影列表
     */
    @ApiOperation(value = "主页获取热门电影列表")
    @PostMapping(value = "/")
    public List<Movie> homePage(){
        return movieService.getHotMovie();
    }

    @ApiOperation(value = "电影推荐")
    @PostMapping(value = "/recommend")
    public List<Movie>  getrecommendMovie(){
        return movieService.getRecommendMovie();
    }

    @ApiOperation(value = "勾选喜欢的电影")
    @PostMapping(value = "/like")
    //TODO 确认是一次添加单个还是多个
    public WebResponse addUserLike(@RequestParam Long[] userLikeList){
        movieService.addLikeMovie(userLikeList);
        return WebResponse.success();
    }

}
