package com.vlad.imdbdata.basis.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Map;

@Entity
public class MediaInfoEntity {

    @Id
    private String imdbId;
    private Map<String, String> mediaInfo;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Map<String, String> getMediaInfo() {
        return mediaInfo;
    }

    public void setMediaInfo(Map<String, String> mediaInfo) {
        this.mediaInfo = mediaInfo;
    }

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "{" +
                "imdbId='" + imdbId + '\'' +
                "} Title: '" + mediaInfo.get("Title") + "\']";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MediaInfoEntity))
            return false;

        MediaInfoEntity that = (MediaInfoEntity) o;

        return imdbId.equals(that.imdbId);
    }

    @Override
    public int hashCode() {
        return imdbId.hashCode();
    }

    public MediaInfoEntity withImdbId(String imdbId) {
        setImdbId(imdbId);
        return this;
    }
    public MediaInfoEntity withMediaInfo(Map<String, String> mediaInfo) {
        setMediaInfo(mediaInfo);
        return this;
    }
}
