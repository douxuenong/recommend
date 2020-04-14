package cn.shawn.recommend.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author shawn
 */
@ApiModel("电影")
@Data
public class Movie {
    @ApiModelProperty("电影编号")
    private long id;

    @ApiModelProperty("电影名称")
    private String movieName;

    @ApiModelProperty("电影排名")
    private long movieRanking;

    @ApiModelProperty("电影封面")
    private String movieImg;

    @ApiModelProperty("电影评分")
    private double movieScore;

    @ApiModelProperty("电影类型")
    private String type;

}
