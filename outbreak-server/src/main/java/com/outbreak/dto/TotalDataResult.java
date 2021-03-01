package com.outbreak.dto;

import java.util.List;

public class TotalDataResult {
    private String date;
    private List<TagDataResult> tagList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<TagDataResult> getTagList() {

        return tagList;
    }

    public void setTagList(List<TagDataResult> tagList) {
        this.tagList = tagList;
    }
}
