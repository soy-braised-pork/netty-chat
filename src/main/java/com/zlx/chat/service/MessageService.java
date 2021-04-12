package com.zlx.chat.service;


import com.zlx.chat.dto.Response;
import com.zlx.chat.entity.Client;

public class MessageService {
    public static Response sendMessage(Client client, String message) {
        Response res = new Response();
        res.getData().put("id",client.getId());
        res.getData().put("message",message);
        res.getData().put("ts",System.currentTimeMillis());  //返回毫秒数
        return res;
    }
}
