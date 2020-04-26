package cn.shawn.recommend.entity;

import lombok.Data;


/**
 * @author shawn
 */
@Data
public class UserRating {

    private Long id;

    private Long userId;

    private Long movieId;

    private double userMovieScore;

}
