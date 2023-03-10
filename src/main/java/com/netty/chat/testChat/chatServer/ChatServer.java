//package com.netty.chat.testChat.chatServer;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelPipeline;
//import io.netty.channel.EventLoopGroup;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.string.StringDecoder;
//import io.netty.handler.codec.string.StringEncoder;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ChatServer {
//    private final int port;
//    private final List<Channel> channels = new ArrayList<>();
//    private JedisPool jedisPool;
//
//    public ChatServer(int port) {
//        this.port = port;
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.setMaxTotal(128);
//        config.setMaxIdle(64);
//        config.setMinIdle(16);
//        config.setTestOnBorrow(true);
//        this.jedisPool = new JedisPool(config, "localhost");
//    }
//
//    public void run() throws Exception {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//
//        try {
//            ServerBootstrap bootstrap = new ServerBootstrap()
//                    .group(bossGroup, workerGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<SocketChannel>() {
//                        @Override
//                        public void initChannel(SocketChannel ch) throws Exception {
//                            ChannelPipeline pipeline = ch.pipeline();
//                            pipeline.addLast(new StringDecoder());
//                            pipeline.addLast(new StringEncoder());
//                            pipeline.addLast(new ChatServerHandler(channels, jedisPool.getResource()));
//                        }
//                    });
//
//            ChannelFuture future = bootstrap.bind(port).sync();
//            System.out.println("Server started on port " + port);
//
//            future.channel().closeFuture().sync();
//        } finally {
//            workerGroup.shutdownGracefully();
//            bossGroup.shutdownGracefully();
//            jedisPool.close();
//        }
//    }
//
//    public static void main(String[] args) throws Exception {
//        new ChatServer(8080).run();
//    }
//}
