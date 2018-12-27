package com.daji.supermarket.vo;

import lombok.Data;

/**
 * @author 大稽
 * @date2018/12/1114:52
 */
@Data
public class ResponseVo {
    private int code;
    private Object data;
    private String msg;

    public ResponseVo(){
    }
    public ResponseVo(int code, String msg) {
        this.code = code;
        this.data = null;
        this.msg = msg;
    }
    public ResponseVo(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
