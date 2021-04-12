package com.zlx.chat.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) {
        //判断evt是否是IdleStateHandler(用于触发用户事件，包含读空闲、写空闲、读写空闲)
        if (evt instanceof IdleStateHandler) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;

            if (idleStateEvent.state() == IdleState.READER_IDLE) {
                System.out.println("进入读空间。。。");
            } else if (idleStateEvent.state() == IdleState.WRITER_IDLE) {
                System.out.println("进入写空间。。。");
            } else if (idleStateEvent.state() == IdleState.ALL_IDLE) {
                System.out.println("进入读写空闲。。。");

                Channel channel = ctx.channel();
                //关闭无用channel，避免浪费资源
                channel.close();
            }
        }
    }
}
