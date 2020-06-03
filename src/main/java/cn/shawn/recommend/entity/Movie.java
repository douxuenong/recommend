package cn.shawn.recommend.entity;

import lombok.Data;


/**
 * @author shawn
 */
@Data
public class Movie {
    private Long id;

    private String movieName;

    private Long movieRanking;

    private String movieImg;

    private double movieScore;

    private String type;

}
