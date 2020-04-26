package cn.shawn.recommend.entity;

import lombok.Data;


/**
 * @author shawn
 */
@Data
public class UserLike {

    private long id;

    private long userId;

    private long movieId;

    public UserLike(long userId, long movieId) {
        this.userId = userId;
        this.movieId = movieId;
    }
}
