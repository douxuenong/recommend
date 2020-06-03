package cn.shawn.recommend.entity;

import lombok.Data;


/**
 * @author shawn
 */
@Data
public class UserLike {

    private Long id;

    private Long userId;

    private Long movieId;

    public UserLike(long userId, long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }
}
