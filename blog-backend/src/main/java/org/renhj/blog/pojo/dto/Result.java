package org.renhj.blog.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.renhj.blog.pojo.enums.ResultCode;

import java.io.Serializable;

@Data
@ToString
public class Result implements Serializable {
    private Integer code = 200;
    private String msg = "ok";
    private Object data;

    public Result() {}
    public Result(ResultCode code, Object data) {
        this(code.getCode(), code.getMsg(), data);
    }
    public Result(ResultCode code) {
        this(code.getCode(), code.getMsg(), null);
    }
    public Result(Integer code) {
        this.code = code;
    }
    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Result(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result ok(){
        return new Result(200, "success", null);
    }
    public static Result ok(Object data){
        return new Result(200, "success", data);
    }
    public static Result ok(int code, String msg){
        return new Result(code, msg, null);
    }
    public static Result ok(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result error(){
        return new Result(500, "error", null);
    }
    public static Result error(int code, String msg){
        return new Result(code, msg, null);
    }

}
