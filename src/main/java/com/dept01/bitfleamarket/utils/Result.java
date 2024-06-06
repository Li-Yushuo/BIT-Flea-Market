package com.dept01.bitfleamarket.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    /***
     * 响应码：
     * 0 -> 成功
     * 1 -> 普通错误
     * 2 -> 未登录错误
     * 3 -> 无权限错误
     * */
    private int code;
    // 响应信息
    private String msg;
    // 响应数据
    private Object data;

    public static Result success() {
        return new Result( 0, "成功", null);
    }

    public static Result success(Object data) {
        return new Result( 0, "成功", data);
    }

    public static Result error(String msg) {
        return new Result( 1, msg, null);
    }
    public static Result error(int code, String msg) {return new Result(code, msg, null);}

    public static Result error(int code, String msg, Object data) {return new Result(code, msg, data);}

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
