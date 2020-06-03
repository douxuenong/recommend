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

    private String phone;

    private String email;

    public User(String username, String password, String phone, String email) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
}
