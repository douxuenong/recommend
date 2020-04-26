package cn.shawn.recommend.entity;

import lombok.Data;


/**
 *
 * @author shawn
 */
@Data
public class User {

    private Long id;

    private String username;

    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
