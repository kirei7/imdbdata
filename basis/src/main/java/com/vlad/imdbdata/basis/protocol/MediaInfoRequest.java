package com.vlad.imdbdata.protocol;

public class MediaInfoRequest {

    private ContentType type;
    private Integer year;
    private String title;

    public enum  ContentType {
        MOVIE, SERIES
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "MediaInfoRequest{" +
                "type=" + type +
                ", year=" + year +
                ", title='" + title + '\'' +
                '}';
    }
}
