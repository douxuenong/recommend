package cn.shawn.recommend.constant;

import lombok.AllArgsConstructor;

/**
 *
 */
@AllArgsConstructor
public enum HttpResponseStatusEnum implements cn.shawn.recommend.constant.CommonResponse {

    /**
     * 请求成功
     */
    SUCCESS("0", "success"),
    /**
     * 请求失败
     */
    FAIL("1","fail"),
    /**
     * 没有权限
     */
    FORBIDDEN_OPERATION("2", "forbidden");
    private String code;
    private String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Object getResult() {
        return null;
    }

}
