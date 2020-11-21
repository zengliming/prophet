package cn.zlmisme.prophet.commons.vo;

import lombok.Data;

/**
 * @author liming zeng
 * @create 2020/11/21 8:14 下午
 */
@Data
public class BaseResponse<T> {

    private Integer code;

    private String msg;

    private T data;

    public static BaseResponse<Void> success() {
        BaseResponse<Void> response = new BaseResponse<>();
        response.code = 1;
        response.msg = "success";
        return response;
    }

    public static <T> BaseResponse<T> success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.code = 1;
        response.msg = "success";
        response.setData(data);
        return response;
    }

    public static BaseResponse<Void> fail(String msg) {
        BaseResponse<Void> response = new BaseResponse<>();
        response.code = 0;
        response.msg = msg;
        return response;
    }

    public static BaseResponse<Void> flowLimit() {
        BaseResponse<Void> response = new BaseResponse<>();
        response.code = 0;
        response.msg = "flow limit";
        return response;
    }

}
