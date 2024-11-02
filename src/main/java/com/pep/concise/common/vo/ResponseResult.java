package com.pep.concise.common.vo;

import com.pep.concise.common.enums.ResultErrorStatus;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

/**
 * 响应结果
 *
 * @author gang.liu
 */
@Data
@Accessors(chain = true)
public class ResponseResult<T> {

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应信息
     */
    private String msg;

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>()
                .setCode(HttpStatus.OK.value())
                .setMsg("Operation Success")
                .setData(data);
    }

    public static <T> ResponseResult<T> failure(String message, ResultErrorStatus status) {
        return new ResponseResult<T>()
                .setCode(status.getCode())
                .setMsg(message);
    }

    public static <T> ResponseResult<T> failure(ResultErrorStatus status) {
        return new ResponseResult<T>()
                .setCode(status.getCode())
                .setMsg(status.getMessage());
    }

}
