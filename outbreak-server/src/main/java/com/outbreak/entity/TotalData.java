package com.outbreak.entity;

import java.util.Objects;

public class TotalData {
    private int id;
    private long createTime;
    private long modifyTime;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private int seriousCount;
    private int suspectedIncr;
    private int currentConfirmedIncr;
    private int confirmedIncr;
    private int curedIncr;
    private int deadIncr;
    private int seriousIncr;
    private int yesterdayConfirmedCountIncr;
    private int yesterdaySuspectedCountIncr;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCurrentConfirmedCount() {
        return currentConfirmedCount;
    }

    public void setCurrentConfirmedCount(int currentConfirmedCount) {
        this.currentConfirmedCount = currentConfirmedCount;
    }

    public int getConfirmedCount() {
        return confirmedCount;
    }

    public void setConfirmedCount(int confirmedCount) {
        this.confirmedCount = confirmedCount;
    }

    public int getSuspectedCount() {
        return suspectedCount;
    }

    public void setSuspectedCount(int suspectedCount) {
        this.suspectedCount = suspectedCount;
    }

    public int getCuredCount() {
        return curedCount;
    }

    public void setCuredCount(int curedCount) {
        this.curedCount = curedCount;
    }

    public int getDeadCount() {
        return deadCount;
    }

    public void setDeadCount(int deadCount) {
        this.deadCount = deadCount;
    }

    public int getSeriousCount() {
        return seriousCount;
    }

    public void setSeriousCount(int seriousCount) {
        this.seriousCount = seriousCount;
    }

    public int getSuspectedIncr() {
        return suspectedIncr;
    }

    public void setSuspectedIncr(int suspectedIncr) {
        this.suspectedIncr = suspectedIncr;
    }

    public int getCurrentConfirmedIncr() {
        return currentConfirmedIncr;
    }

    public void setCurrentConfirmedIncr(int currentConfirmedIncr) {
        this.currentConfirmedIncr = currentConfirmedIncr;
    }

    public int getConfirmedIncr() {
        return confirmedIncr;
    }

    public void setConfirmedIncr(int confirmedIncr) {
        this.confirmedIncr = confirmedIncr;
    }

    public int getCuredIncr() {
        return curedIncr;
    }

    public void setCuredIncr(int curedIncr) {
        this.curedIncr = curedIncr;
    }

    public int getDeadIncr() {
        return deadIncr;
    }

    public void setDeadIncr(int deadIncr) {
        this.deadIncr = deadIncr;
    }

    public int getSeriousIncr() {
        return seriousIncr;
    }

    public void setSeriousIncr(int seriousIncr) {
        this.seriousIncr = seriousIncr;
    }

    public int getYesterdayConfirmedCountIncr() {
        return yesterdayConfirmedCountIncr;
    }

    public void setYesterdayConfirmedCountIncr(int yesterdayConfirmedCountIncr) {
        this.yesterdayConfirmedCountIncr = yesterdayConfirmedCountIncr;
    }

    public int getYesterdaySuspectedCountIncr() {
        return yesterdaySuspectedCountIncr;
    }

    public void setYesterdaySuspectedCountIncr(int yesterdaySuspectedCountIncr) {
        this.yesterdaySuspectedCountIncr = yesterdaySuspectedCountIncr;
    }

    @Override
    public String toString() {
        return "TotalData{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", currentConfirmedCount=" + currentConfirmedCount +
                ", confirmedCount=" + confirmedCount +
                ", suspectedCount=" + suspectedCount +
                ", curedCount=" + curedCount +
                ", deadCount=" + deadCount +
                ", seriousCount=" + seriousCount +
                ", suspectedIncr=" + suspectedIncr +
                ", currentConfirmedIncr=" + currentConfirmedIncr +
                ", confirmedIncr=" + confirmedIncr +
                ", curedIncr=" + curedIncr +
                ", deadIncr=" + deadIncr +
                ", seriousIncr=" + seriousIncr +
                ", yesterdayConfirmedCountIncr=" + yesterdayConfirmedCountIncr +
                ", yesterdaySuspectedCountIncr=" + yesterdaySuspectedCountIncr +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TotalData totalData = (TotalData) o;
        return id == totalData.id &&
                createTime == totalData.createTime &&
                modifyTime == totalData.modifyTime &&
                currentConfirmedCount == totalData.currentConfirmedCount &&
                confirmedCount == totalData.confirmedCount &&
                suspectedCount == totalData.suspectedCount &&
                curedCount == totalData.curedCount &&
                deadCount == totalData.deadCount &&
                seriousCount == totalData.seriousCount &&
                suspectedIncr == totalData.suspectedIncr &&
                currentConfirmedIncr == totalData.currentConfirmedIncr &&
                confirmedIncr == totalData.confirmedIncr &&
                curedIncr == totalData.curedIncr &&
                deadIncr == totalData.deadIncr &&
                seriousIncr == totalData.seriousIncr &&
                yesterdayConfirmedCountIncr == totalData.yesterdayConfirmedCountIncr &&
                yesterdaySuspectedCountIncr == totalData.yesterdaySuspectedCountIncr;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createTime, modifyTime, currentConfirmedCount, confirmedCount, suspectedCount, curedCount, deadCount, seriousCount, suspectedIncr, currentConfirmedIncr, confirmedIncr, curedIncr, deadIncr, seriousIncr, yesterdayConfirmedCountIncr, yesterdaySuspectedCountIncr);
    }
}
