package com.lqstar.vo;

import java.util.List;

/**
 * @Author: tianqiu.lan
 * @Date: 2019/4/9/009 20:45
 */

public class Result {
    private List<?> results;
    private boolean success = false;
    private String msg = "";
    private Object obj = null;
    private String token = "";

    public Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public Result(boolean success, String msg, String token) {
        this.success = success;
        this.msg = msg;
        this.token = token;
    }

    public List<?> getResults() {

        return results;
    }

    public void setResults(List<?> results) {
        this.results = results;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
