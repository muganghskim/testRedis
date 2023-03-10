//package com.netty.chat.testChat.chatServer;
//
//import io.netty.channel.Channel;
//import io.netty.channel.ChannelHandlerContext;
//import io.netty.channel.ChannelInboundHandlerAdapter;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import java.util.List;
//import java.util.Set;
//
//public class ChatServerHandler extends ChannelInboundHandlerAdapter {
//    public ChatServerHandler(List<Channel> channels, Jedis resource) {
//    }
//    JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost");
//
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        String message = (String) msg;
//        System.out.println("Received message from client: " + message);
//
//        // Broadcast the message to all connected clients except the sender
//        Jedis jedis = null;
//        try {
//            jedis = jedisPool.getResource();
//            Set<String> channelKeys = jedis.keys("*");
//            for (String channelKey : channelKeys) {
//                jedis.publish(channelKey, "[" + ctx.channel().remoteAddress() + "]: " + message + "\n");
//            }
//        } finally {
//            if (jedis != null) {
//                jedis.close();
//            }
//        }
//
//        // Save the message to Redis
//        try {
//            jedis = jedisPool.getResource();
//            String key = "chat_history";
//            String value = "[" + ctx.channel().remoteAddress() + "]: " + message + "\n";
//            jedis.append(key, value);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        // Broadcast the message to all connected clients except the sender
//        ctx.channel().parent().writeAndFlush("[" + ctx.channel().remoteAddress() + "]: " + message + "\n");
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//        cause.printStackTrace();
//        ctx.close();
//    }
//}

