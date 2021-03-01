package com.outbreak.entity;


public class TimeLine {

    private int id;
    private long pubDate;
    private String pubDateStr;
    private String title;
    private String summary;
    private String infoSource;
    private String sourceUrl;
    private String provinceName;
    private long createTime;
    private long modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getPubDate() {
        return pubDate;
    }

    public void setPubDate(long pubDate) {
        this.pubDate = pubDate;
    }

    public String getPubDateStr() {
        return pubDateStr;
    }

    public void setPubDateStr(String pubDateStr) {
        this.pubDateStr = pubDateStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getInfoSource() {
        return infoSource;
    }

    public void setInfoSource(String infoSource) {
        this.infoSource = infoSource;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }


    @Override
    public String toString() {
        return "TimeLine{" +
                "id=" + id +
                ", pubDate=" + pubDate +
                ", pubDateStr='" + pubDateStr + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", infoSource='" + infoSource + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", provinceName='" + provinceName + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }


    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

}
