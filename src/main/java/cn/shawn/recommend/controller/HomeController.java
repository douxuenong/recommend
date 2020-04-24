package cn.shawn.recommend.controller;

import cn.shawn.recommend.bean.SockettoPython;
import cn.shawn.recommend.bean.WebResponse;
import cn.shawn.recommend.service.impl.MovieServiceImpl;
import cn.shawn.recommend.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * @author Shawn
 */
@Api(tags = "主页")
@RestController
public class HomeController {
    private final MovieServiceImpl movieService;
    private final UserServiceImpl userService;

    public HomeController(MovieServiceImpl movieService, UserServiceImpl userService) {
        this.movieService = movieService;
        this.userService = userService;
    }

    /**
     * @return 热门电影列表
     */
    @ApiOperation(value = "主页获取热门电影列表")
    @PostMapping(value = "/home")
    public String homePage(){
        return movieService.getHotMovie();
    }

    @ApiOperation(value = "电影推荐")
    @PostMapping(value = "/home/recommend")
    public JSONArray getrecommendMovie(@AuthenticationPrincipal Principal principal){
        String username = principal.getName();
        String id = userService.getByUsername(username).getId().toString();
        //发送给python服务端的用户名必需转化为byte[]
        byte[] b_hostID = id.getBytes();
        //调用与python服务端的通信方法
        SockettoPython sockettoPython = new SockettoPython();
        JSONArray res = sockettoPython.sendMessage(b_hostID);
        return res;
    }

    @ApiOperation(value = "勾选喜欢的电影")
    @PostMapping(value = "/home/like")
    //TODO 确认是一次添加单个还是多个
    public WebResponse addUserLike(@RequestParam Long[] userLikeList){
        movieService.addLikeMovie(userLikeList);
        return WebResponse.success();
    }

}
