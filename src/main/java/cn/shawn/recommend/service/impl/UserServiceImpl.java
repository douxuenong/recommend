package cn.shawn.recommend.service.impl;

import cn.shawn.recommend.bean.WebResponse;
import cn.shawn.recommend.dao.UserMapper;
import cn.shawn.recommend.entity.User;
import cn.shawn.recommend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户相关
 * @author shawn
 */
@Service
public class UserServiceImpl implements UserService {

    private  UserMapper userMapper;

    private  BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Autowired
    public void setbCryptPasswordEncoder(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public WebResponse add(User user) {
        String username = user.getUsername();
        WebResponse webResponse;
        if(exist(username)){
            webResponse =  WebResponse.fail();
        }else {
            //密码加密
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userMapper.add(user);
            webResponse = WebResponse.success();
        }
        return  webResponse;
    }

    @Override
    public User getByUsername(String username) {
        return userMapper.getUserByName(username);
    }

    private boolean exist(String username){
        User user = userMapper.getUserByName(username);
        return user != null;
    }
}
