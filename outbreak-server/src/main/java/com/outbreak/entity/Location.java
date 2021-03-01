package com.outbreak.entity;

import java.util.List;
import java.util.Objects;

public class Location {
    private int id;
    private String provinceName;
    private String provinceShortName;
    private int currentConfirmedCount;
    private int confirmedCount;
    private int suspectedCount;
    private int curedCount;
    private int deadCount;
    private String comment;
    private List<CitiesBean> cities;
    private long modifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceShortName() {
        return provinceShortName;
    }

    public void setProvinceShortName(String provinceShortName) {
        this.provinceShortName = provinceShortName;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CitiesBean> getCities() {
        return cities;
    }

    public void setCities(List<CitiesBean> cities) {
        this.cities = cities;
    }

    public long getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(long modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return id == location.id &&
                currentConfirmedCount == location.currentConfirmedCount &&
                confirmedCount == location.confirmedCount &&
                suspectedCount == location.suspectedCount &&
                curedCount == location.curedCount &&
                deadCount == location.deadCount &&
                modifyTime == location.modifyTime &&
                Objects.equals(provinceName, location.provinceName) &&
                Objects.equals(provinceShortName, location.provinceShortName) &&
                Objects.equals(comment, location.comment) &&
                Objects.equals(cities, location.cities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, provinceName, provinceShortName, currentConfirmedCount, confirmedCount, suspectedCount, curedCount, deadCount, comment, cities, modifyTime);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", provinceName='" + provinceName + '\'' +
                ", provinceShortName='" + provinceShortName + '\'' +
                ", currentConfirmedCount=" + currentConfirmedCount +
                ", confirmedCount=" + confirmedCount +
                ", suspectedCount=" + suspectedCount +
                ", curedCount=" + curedCount +
                ", deadCount=" + deadCount +
                ", comment='" + comment + '\'' +
                ", cities=" + cities +
                ", modifyTime=" + modifyTime +
                '}';
    }

    public static class CitiesBean {
        private int id;
        private String cityName;
        private int currentConfirmedCount;
        private int confirmedCount;
        private int suspectedCount;
        private int curedCount;
        private int deadCount;
        private String provinceName;
        private long modifyTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
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

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public long getModifyTime() {
            return modifyTime;
        }

        public void setModifyTime(long modifyTime) {
            this.modifyTime = modifyTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CitiesBean that = (CitiesBean) o;
            return id == that.id &&
                    currentConfirmedCount == that.currentConfirmedCount &&
                    confirmedCount == that.confirmedCount &&
                    suspectedCount == that.suspectedCount &&
                    curedCount == that.curedCount &&
                    deadCount == that.deadCount &&
                    modifyTime == that.modifyTime &&
                    Objects.equals(cityName, that.cityName) &&
                    Objects.equals(provinceName, that.provinceName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, cityName, currentConfirmedCount, confirmedCount, suspectedCount, curedCount, deadCount, provinceName, modifyTime);
        }

        @Override
        public String toString() {
            return "CitiesBean{" +
                    "id=" + id +
                    ", cityName='" + cityName + '\'' +
                    ", currentConfirmedCount=" + currentConfirmedCount +
                    ", confirmedCount=" + confirmedCount +
                    ", suspectedCount=" + suspectedCount +
                    ", curedCount=" + curedCount +
                    ", deadCount=" + deadCount +
                    ", provinceName='" + provinceName + '\'' +
                    ", modifyTime=" + modifyTime +
                    '}';
        }
    }
}
