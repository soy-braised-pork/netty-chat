package com.zlx.chat.dto;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private int error_code; //成功时 0 ，如果大于 0 则表示error_msg
    private String error_msg; //返回错误信息
    private Map<String,Object> data;

    public Response(){
        data=new HashMap<String,Object>();
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
