package com.outbreak.crawler;

import com.outbreak.service.TimeLineService;
import com.outbreak.service.TotalDataService;
import com.outbreak.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

@Component
public class Crawler implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    @Autowired
    LocationService locationService;
    @Autowired
    TotalDataService totalDataService;
    @Autowired
    TimeLineService timeLineService;

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
        String url = "https://ncov.dxy.cn/ncovh5/view/pneumonia";
        Spider.create(this)
                .addUrl(url)
                //开启5个线程抓取
                .thread(5)
                //启动爬虫
                .run();
    }
}
