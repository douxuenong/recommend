package cn.shawn.recommend.entity;

import lombok.Data;


/**
 * @author shawn
 */
@Data
public class Movie {
    private long id;

    private String movieName;

    private long movieRanking;

    private String movieImg;

    private double movieScore;

    private String type;

}
