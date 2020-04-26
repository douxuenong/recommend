package cn.shawn.recommend.controller;

import cn.shawn.recommend.bean.WebResponse;
import cn.shawn.recommend.entity.User;
import cn.shawn.recommend.service.impl.UserServiceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * @author shawn
 */
@RestController
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * 注册
     * @return WebResponse code为0成功，为1失败（用户名已存在）
     */
    @PostMapping("/register")
    public WebResponse register(@RequestBody Map<String,Object> params){
        String username = (String)params.get("username");
        String password = (String)params.get("password");
        return userService.add(new User(username,password));
    }

    @GetMapping("/user")
    public WebResponse user(@AuthenticationPrincipal Principal principal){
        return WebResponse.success(principal.getName());
    }
}
