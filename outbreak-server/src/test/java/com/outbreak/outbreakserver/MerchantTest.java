package com.outbreak.outbreakserver;

import com.outbreak.OutbreakServerApplication;
import com.outbreak.entity.Merchant;
import com.outbreak.mapper.MerchantMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OutbreakServerApplication.class)
public class MerchantTest {
    @Autowired
    MerchantMapper merchantMapper;

    @Test
    public void redisTest() {
        List<Merchant> valOpt = merchantMapper.selectMerchantList(126.608940,45.721195);
        System.out.println(valOpt.toString());
        System.out.println(111);
    }

}
