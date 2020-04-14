package cn.shawn.recommend.controller;

import cn.shawn.recommend.bean.WebResponse;
import cn.shawn.recommend.entity.User;
import cn.shawn.recommend.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author shawn
 */
@Api(tags = "用户管理")
@RestController
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * @param username 用户名
     * @param password 密码
     * @return WebResponse code为0成功，为1失败（用户名已存在）
     */
    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public WebResponse register(@RequestParam("username") String username, @RequestParam("password") String password){
        return userService.add(new User(username,password));
    }

    @GetMapping("/user")
    @ApiOperation(value = "获取当前用户名")
    public WebResponse user(@AuthenticationPrincipal Principal principal){
        return WebResponse.success(principal.getName());
    }
}
