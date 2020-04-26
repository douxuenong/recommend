package cn.shawn.recommend.service;

import cn.shawn.recommend.bean.WebResponse;
import cn.shawn.recommend.entity.User;
import com.alibaba.fastjson.JSONArray;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.security.Principal;

/**
 * @author shawn
 */
public interface UserService {
    /**
     * 添加新用户，默认USER权限
     * @param user
     */
    WebResponse add(User user);

    /**
     * 根据用户名查询用户
     * @param username
     * @return 查得用户实体
     */
    User getByUsername(String username);


}
