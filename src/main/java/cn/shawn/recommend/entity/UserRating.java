package cn.shawn.recommend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author shawn
 */
@ApiModel("用户评分")
@Data
public class UserRating {
    @ApiModelProperty("评分编号")
    private Long id;

    private Long userId;

    private Long movieId;

    @ApiModelProperty("用户评分")
    private double userMovieScore;

}
