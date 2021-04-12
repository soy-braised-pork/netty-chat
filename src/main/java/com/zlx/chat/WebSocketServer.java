package com.zlx.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WebSocketServer {

    public void run(int port) throws InterruptedException{
        /*
        bossgroup、workergroup默认线程数为CPU核心*2
        bossgroup用于接受客户端发来的请求，接收到请求后将后续操作交由workergroup来处理
         */
        //两个线程池
        EventLoopGroup worker=new NioEventLoopGroup();
        EventLoopGroup boss=new NioEventLoopGroup();

        try {
            /*
            ServerBootstrap用来为Netty启动组装配置一些必要组件，如上面两个线程池
            channel方法用于指定服务器监听套接字通道NioSocketChannel,
             */
            //服务端启动辅助类 bootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelPipelineInit());
              /*
            bootstrap的bind方法将服务绑定在8080端口，bind方法内部会执行端口绑定等一系列操作，使前面的配置各司其职
            加锁----->同步
             */
            Channel ch = bootstrap.bind(port).sync().channel();

            //等待服务端关闭
            ch.closeFuture().sync();
        }finally {
            //退出，释放线程池资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new WebSocketServer().run(55555);
    }
}
