package com.cy.store.util;

import java.io.Serializable;

/**
 * @author Shuhui Lin
 * @projectName store
 * @description:
 * @date 2022/3/9 16:08
 */

/* 前后的每次交互，都涉及到状态码、庄描述信息和数据
*  因此定义一个基类
*
* */
public class JsonResult<T> implements Serializable {
    // 状态码
    private Integer state;
    // 描述信息
    private String message;
    // 数据
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Throwable e) {
        this.message = e.getMessage();
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
