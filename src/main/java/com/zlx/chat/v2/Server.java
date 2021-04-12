package com.zlx.chat.v2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *事件调度层
 * EventLoop:循环中处理事件调度
 * EventLoopGroup：线程池
 * TaskPool
 *
 * 网络通信层
 * ServerBootStrap
 * ServerSocketChannel
 * SocketChannel
 */

public class Server {

    private void start(int port) throws InterruptedException{
        EventLoopGroup boss=new NioEventLoopGroup(1);
        NioEventLoopGroup worker=new NioEventLoopGroup();
        try {
            ServerBootstrap b=new ServerBootstrap();
            b.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new PiplineManager());
            Channel ch=b.bind(port).sync().channel();
            ch.closeFuture().sync();
        }finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        new Server().start(9999);
    }
}
