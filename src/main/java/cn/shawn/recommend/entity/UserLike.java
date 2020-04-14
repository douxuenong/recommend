package cn.shawn.recommend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author shawn
 */
@ApiModel(description = "用户喜爱")
@Data
public class UserLike {
    @ApiModelProperty("喜爱编号")
    private long id;

    @ApiModelProperty("用户编号")
    private long userId;

    @ApiModelProperty("电影编号")
    private long movieId;

    public UserLike(long userId, long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }
}
