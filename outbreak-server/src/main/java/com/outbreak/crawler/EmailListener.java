package com.outbreak.crawler;

import com.alibaba.fastjson.JSON;
import com.outbreak.dto.MerchantResult;
import com.outbreak.entity.Merchant;
import com.outbreak.mapper.MerchantMapper;
import com.outbreak.netty.ChatHandler;
import com.outbreak.service.impl.MerchantServiceImpl;
import com.outbreak.utils.MailUtils;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class EmailListener {

    @Autowired
    private MerchantMapper merchantMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "OUTBREAK.VERIFY.QUEUE", durable = "true"),
            exchange = @Exchange(value = "OUTBREAK.EXCHANGE", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"email.verify"}
    ))
    public void EmailListen(Map<String, String> msg){
        if (CollectionUtils.isEmpty(msg)){
            return;
        }
        String emial = msg.get("email");
        String code = msg.get("code");
        if (StringUtils.isNoneBlank(emial) && StringUtils.isNoneBlank(code)) {
            MailUtils.sendMail(emial,"您的验证码为" + code + "(15分钟内有效)","疫情便民信息");
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "OUTBREAK.PEOPLE.QUEUE", durable = "true"),
            exchange = @Exchange(value = "OUTBREAK.EXCHANGE", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"merchant.people"}
    ))
    public void PeopleListen(Map<String, Integer> msg){
        System.out.println(msg.toString());
        ChannelGroup channels = ChatHandler.getChannels();
        Integer id = msg.get("id");
        Integer people = msg.get("people");
        Merchant merchant = merchantMapper.selectMerchantById(2);
        MerchantResult merchantResult = MerchantServiceImpl.getMerchantResult(merchant.getId());
        int present = merchantResult.getPresent();
        if(people < 0){
            present = present-people;
        }else {
            present = present+people;
        }
        merchantResult.setPresent(present);
        MerchantServiceImpl.setMap(merchant.getId(),merchantResult);
        String json = JSON.toJSONString(merchantResult);
        System.out.println(json + "shsih");
        channels.writeAndFlush(new TextWebSocketFrame(
                json
        ));
    }
}
