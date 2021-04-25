package com.outbreak.netty;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.outbreak.dto.MerchantResult;
import com.outbreak.entity.Merchant;
import com.outbreak.service.MerchantSerivce;
import com.outbreak.service.impl.MerchantServiceImpl;
import com.outbreak.utils.ParseUtils;
import com.outbreak.utils.SpringUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * 用于处理消息的handler
 * 由于它的传输数据的载体是frame，这个frame 在netty中，是用于为websocket专门处理文本对象的，frame是消息的载体，此类叫：TextWebSocketFrame
 */
@ChannelHandler.Sharable
@Component
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //用于记录和管理所有客户端的channel
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Autowired
    private MerchantSerivce merchantSerivce;

    public static ChannelGroup getChannels(){
        return channels;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //获取客户端所传输的消息
        String content = msg.text();
        System.out.println("接收的数据为" + content);
        Map<Integer, MerchantResult> merchantMap = merchantSerivce.selectMerchantList();
        List<MerchantResult> merchantResults = new LinkedList<>();
        for (Map.Entry<Integer, MerchantResult> merchant:merchantMap.entrySet()) {
            merchantResults.add(merchant.getValue());
        }
        Object[] merchantArray = merchantResults.toArray();
        String json = JSON.toJSONString(merchantArray);
        System.out.println(json);
        channels.writeAndFlush(new TextWebSocketFrame(
                json
        ));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String chanelId = ctx.channel().id().asShortText();
        System.out.println("客户端被移除：channel id 为：" + chanelId);

        channels.remove(ctx.channel());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        //发生了异常后关闭连接，同时从channelgroup移除
        ctx.channel().close();
        channels.remove(ctx.channel());
    }
}
