package cn.shawn.recommend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 *
 * @author shawn
 */
@ApiModel(description = "用户")
@Data
public class User {
    @ApiModelProperty("用户编号")
    private Long id;

    @ApiModelProperty("用户姓名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
