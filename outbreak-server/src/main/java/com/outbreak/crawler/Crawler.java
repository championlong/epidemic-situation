package com.outbreak.crawler;

import com.outbreak.entity.Merchant;
import com.outbreak.mapper.MerchantMapper;
import com.outbreak.service.TimeLineService;
import com.outbreak.service.TotalDataService;
import com.outbreak.service.LocationService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Component
public class Crawler implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    private LocationService locationService;
    @Autowired
    private TotalDataService totalDataService;
    @Autowired
    private TimeLineService timeLineService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public void process(Page page) {
        Html html = page.getHtml();
        String location = html.xpath("//script[@id='getAreaStat']").toString();
        String total = html.xpath("//script[@id='getStatisticsService']").toString();
        String timeLine = html.xpath("//script[@id='getTimelineService1']").toString();
        locationService.saveLocationInformation(location);
        totalDataService.saveTotalData(total);
        timeLineService.saveTimeLine(timeLine);
    }

    @Override
    public Site getSite() {
        return site;
    }

//    @Scheduled(cron = "0 0 1 * * ?")
    @Scheduled(cron = "0 */1 * * * ?")
    public void process(){
        Random random = new Random();
        int nRandomInt = random.nextInt(20);
        int nYouWant = 10 - nRandomInt;
        Map<String, Integer> msg = new HashMap<>();
        msg.put("id",nRandomInt);
        msg.put("people",nYouWant);
        amqpTemplate.convertAndSend("OUTBREAK.EXCHANGE", "merchant.people", msg);
        String url = "https://ncov.dxy.cn/ncovh5/view/pneumonia";
        Spider.create(this)
                .addUrl(url)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
