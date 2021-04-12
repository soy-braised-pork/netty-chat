package com.zlx.chat.handler;

import com.sun.security.ntlm.Client;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class WebSocketHandler extends SimpleChannelInboundHandler {
    //websocket 服务的 uri
    private static final String WEBSOCKET_PATH="/websocket";

    //一个 ChannelGroup 代表一个直播频道
    private static Map<Integer, ChannelGroup> channelGroupMap=new ConcurrentHashMap<>();

    //本次请求的 code
    private static final String HTTP_EWQUEST_STRING="request";


    private WebSocketServerHandshaker handshaker;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {
        if (msg instanceof FullHttpMessage){
            handleHttpRequest(ctx,(FullHttpRequest) msg);
        }else if (msg instanceof WebSocketFrame){
            handleWebSocketFrame(ctx,(WebSocketFrame) msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.flush();
    }

    private void handleHttpRequest(ChannelHandlerContext ctx,FullHttpRequest req){
        //Handle a bad request
        if (!req.decoderResult().isSuccess()){
            sendHttpResponse(ctx,req, new DefaultFullHttpResponse(HTTP_1_1, HttpResponseStatus.BAD_REQUEST)){
                return;
            }

            //Allow only GET methods
            if (req.method()!=HttpMethod.GET){
                sendHttpResponse(ctx,req, new DefaultFullHttpResponse(HTTP_1_1,HttpResponseStatus.FORBIDDEN));
                return;
            }


        }
    }
}
