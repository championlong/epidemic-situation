package com.outbreak.crawler;

import com.outbreak.utils.MailUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;

@Component
public class EmailListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "OUTBREAK.VERIFY.QUEUE", durable = "true"),
            exchange = @Exchange(value = "OUTBREAK.EXCHANGE", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC),
            key = {"email.verify"}
    ))
    public void listen(Map<String, String> msg){
        if (CollectionUtils.isEmpty(msg)){
            return;
        }
        String emial = msg.get("email");
        String code = msg.get("code");
        if (StringUtils.isNoneBlank(emial) && StringUtils.isNoneBlank(code)) {
            MailUtils.sendMail(emial,"您的验证码为" + code + "(15分钟内有效)","疫情便民信息");
        }
    }
}
