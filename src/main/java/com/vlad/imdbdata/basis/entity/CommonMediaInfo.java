package com.vlad.imdbdata.basis.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "MEDIA_INFO_ENTITY")
public class CommonMediaInfo implements Cloneable{
    public CommonMediaInfo() {
    }

    @Id
    @Column(name = "ID", length = 16)
    protected String imdbId;

    @Column(length = 512)
    protected String plotShort;
    @Column(length = 4096)
    protected String plotFull;

    protected Date released;
    @Column(length = 64)
    protected String title;
    @Column(length = 16)
    protected String year;
    @Column(length = 8)
    protected String rated;
    @Column(length = 16)
    protected String runtime;
    protected String director;
    protected String awards;
    protected String posterUrl;
    @Column(length = 16)
    protected String type;
    protected String response;
    protected String genre;
    protected String writer;
    protected String actors;
    protected String language;
    @Column(length = 64)
    protected String country;
    protected Float imdbRating;
    protected Integer metaScore;
    protected Integer imdbVotes;

    //tomatoes info
    protected Date dvd;
    protected String tomatoImage;
    protected String tomatoConsensus;
    protected String boxOffice;
    @Column(length = 64)
    protected String production;
    protected String tomatoUrl;
    @Column(length = 64)
    protected String website;
    protected Float tomatoRating;
    protected Float tomatoUserRating;
    protected Integer tomatoMeter;
    protected Integer tomatoReviews;
    protected Integer tomatoFresh;
    protected Integer tomatoRotten;
    protected Integer tomatoUserMeter;
    protected Integer tomatoUserReviews;

    @Override
    public String toString() {
        return "[" + this.getClass().getSimpleName() + "{" +
                "imdbId='" + imdbId + "\'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof CommonMediaInfo))
            return false;

        CommonMediaInfo that = (CommonMediaInfo) o;

        return imdbId.equals(that.imdbId);
    }

    @Override
    public int hashCode() {
        return imdbId.hashCode();
    }

    @Override
    public Object clone() {
        return null;
    }

    public static EntityBuilder entityBuilder() {
        return new EntityBuilder();
    }
    public static class EntityBuilder {
        protected CommonMediaInfo obj;

        EntityBuilder() {
            obj = new CommonMediaInfo();
        }

        public CommonMediaInfo build() {
            return obj;
        }

        public EntityBuilder withImdbId(String imdbId) {
            obj.setImdbId(imdbId);
            return this;
        }
        public EntityBuilder withPlotShort(String plotShort) {
            obj.setPlotShort(plotShort);
            return this;
        }
        public EntityBuilder withPlotFull(String plotFull) {
            obj.setPlotFull(plotFull);
            return this;
        }
        public EntityBuilder withReleased(Date released) {
            obj.setReleased(released);
            return this;
        }
        public EntityBuilder withTitle(String title) {
            obj.setTitle(title);
            return this;
        }
        public EntityBuilder withYear(String year) {
            obj.setYear(year);
            return this;
        }
        public EntityBuilder withRated(String rated) {
            obj.setRated(rated);
            return this;
        }
        public EntityBuilder withRuntime(String runtime) {
            obj.setRuntime(runtime);
            return this;
        }
        public EntityBuilder withDirector(String director) {
            obj.setDirector(director);
            return this;
        }
        public EntityBuilder withAwards(String awards) {
            obj.setAwards(awards);
            return this;
        }
        public EntityBuilder withPosterUrl(String posterUrl) {
            obj.setPosterUrl(posterUrl);
            return this;
        }
        public EntityBuilder withType(String type) {
            obj.setType(type);
            return this;
        }
        public EntityBuilder withResponse(String responce) {
            obj.setResponse(responce);
            return this;
        }
        public EntityBuilder withGenre(String genre) {
            obj.setGenre(genre);
            return this;
        }
        public EntityBuilder withWriter(String writer) {
            obj.setWriter(writer);
            return this;
        }
        public EntityBuilder withActors(String actors) {
            obj.setActors(actors);
            return this;
        }
        public EntityBuilder withLanguage(String language) {
            obj.setLanguage(language);
            return this;
        }
        public EntityBuilder withCountry(String country) {
            obj.setCountry(country);
            return this;
        }
        public EntityBuilder withImdbRating(Float imdbRating) {
            obj.setImdbRating(imdbRating);
            return this;
        }
        public EntityBuilder withMetaScore(Integer metaScore) {
            obj.setMetaScore(metaScore);
            return this;
        }
        public EntityBuilder withImdbVotes(Integer imdbVotes) {
            obj.setImdbVotes(imdbVotes);
            return this;
        }
        public EntityBuilder withDvd(Date dvd) {
            obj.setDvd(dvd);
            return this;
        }
        public EntityBuilder withTomatoImage(String tomatoImage) {
            obj.setTomatoImage(tomatoImage);
            return this;
        }
        public EntityBuilder withTomatoConsensus(String tomatoConsensus) {
            obj.setTomatoConsensus(tomatoConsensus);
            return this;
        }
        public EntityBuilder withBoxOffice(String boxOffice) {
            obj.setBoxOffice(boxOffice);
            return this;
        }
        public EntityBuilder withProduction(String production) {
            obj.setProduction(production);
            return this;
        }
        public EntityBuilder withTomatoUrl(String tomatoUrl) {
            obj.setTomatoUrl(tomatoUrl);
            return this;
        }
        public EntityBuilder withWebsite(String website) {
            obj.setWebsite(website);
            return this;
        }
        public EntityBuilder withTomatoRating(Float tomatoRating) {
            obj.setTomatoRating(tomatoRating);
            return this;
        }
        public EntityBuilder withTomatoUserRating(Float tomatoUserRating) {
            obj.setTomatoUserRating(tomatoUserRating);
            return this;
        }
        public EntityBuilder withTomatoMeter(Integer tomatoMeter) {
            obj.setTomatoMeter(tomatoMeter);
            return this;
        }
        public EntityBuilder withTomatoReviews(Integer tomatoReviews) {
            obj.setTomatoReviews(tomatoReviews);
            return this;
        }
        public EntityBuilder withTomatoFresh(Integer tomatoFresh) {
            obj.setTomatoFresh(tomatoFresh);
            return this;
        }
        public EntityBuilder withTomatoRotten(Integer tomatoRotten) {
            obj.setTomatoRotten(tomatoRotten);
            return this;
        }
        public EntityBuilder withTomatoUserMeter(Integer tomatoUserMeter) {
            obj.setTomatoUserMeter(tomatoUserMeter);
            return this;
        }
        public EntityBuilder withTomatoUserReviews(Integer tomatoUserReviews) {
            obj.setTomatoUserReviews(tomatoUserReviews);
            return this;
        }
    }

    //copy constructor

    public CommonMediaInfo(CommonMediaInfo other) {
        this.imdbId = other.imdbId;
        this.plotShort = other.plotShort;
        this.plotFull = other.plotFull;
        this.released = other.released;
        this.title = other.title;
        this.year = other.year;
        this.rated = other.rated;
        this.runtime = other.runtime;
        this.director = other.director;
        this.awards = other.awards;
        this.posterUrl = other.posterUrl;
        this.type = other.type;
        this.response = other.response;
        this.genre = other.genre;
        this.writer = other.writer;
        this.actors = other.actors;
        this.language = other.language;
        this.country = other.country;
        this.imdbRating = other.imdbRating;
        this.metaScore = other.metaScore;
        this.imdbVotes = other.imdbVotes;
        this.dvd = other.dvd;
        this.tomatoImage = other.tomatoImage;
        this.tomatoConsensus = other.tomatoConsensus;
        this.boxOffice = other.boxOffice;
        this.production = other.production;
        this.tomatoUrl = other.tomatoUrl;
        this.website = other.website;
        this.tomatoRating = other.tomatoRating;
        this.tomatoUserRating = other.tomatoUserRating;
        this.tomatoMeter = other.tomatoMeter;
        this.tomatoReviews = other.tomatoReviews;
        this.tomatoFresh = other.tomatoFresh;
        this.tomatoRotten = other.tomatoRotten;
        this.tomatoUserMeter = other.tomatoUserMeter;
        this.tomatoUserReviews = other.tomatoUserReviews;
    }

    //the whole bunch of accessors here!

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setPlotShort(String plotShort) {
        this.plotShort = plotShort;
    }

    public void setPlotFull(String plotFull) {
        this.plotFull = plotFull;
    }

    public void setReleased(Date released) {
        this.released = released;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setImdbRating(Float imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setMetaScore(Integer metaScore) {
        this.metaScore = metaScore;
    }

    public void setImdbVotes(Integer imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setDvd(Date dvd) {
        this.dvd = dvd;
    }

    public void setTomatoImage(String tomatoImage) {
        this.tomatoImage = tomatoImage;
    }

    public void setTomatoConsensus(String tomatoConsensus) {
        this.tomatoConsensus = tomatoConsensus;
    }

    public void setBoxOffice(String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public void setTomatoUrl(String tomatoUrl) {
        this.tomatoUrl = tomatoUrl;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setTomatoRating(Float tomatoRating) {
        this.tomatoRating = tomatoRating;
    }

    public void setTomatoUserRating(Float tomatoUserRating) {
        this.tomatoUserRating = tomatoUserRating;
    }

    public void setTomatoMeter(Integer tomatoMeter) {
        this.tomatoMeter = tomatoMeter;
    }

    public void setTomatoReviews(Integer tomatoReviews) {
        this.tomatoReviews = tomatoReviews;
    }

    public void setTomatoFresh(Integer tomatoFresh) {
        this.tomatoFresh = tomatoFresh;
    }

    public void setTomatoRotten(Integer tomatoRotten) {
        this.tomatoRotten = tomatoRotten;
    }

    public void setTomatoUserMeter(Integer tomatoUserMeter) {
        this.tomatoUserMeter = tomatoUserMeter;
    }

    public void setTomatoUserReviews(Integer tomatoUserReviews) {
        this.tomatoUserReviews = tomatoUserReviews;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getPlotShort() {
        return plotShort;
    }

    public String getPlotFull() {
        return plotFull;
    }

    public Date getReleased() {
        return released;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getDirector() {
        return director;
    }

    public String getAwards() {
        return awards;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getType() {
        return type;
    }

    public String getResponse() {
        return response;
    }

    public String getGenre() {
        return genre;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public Float getImdbRating() {
        return imdbRating;
    }

    public Integer getMetaScore() {
        return metaScore;
    }

    public Integer getImdbVotes() {
        return imdbVotes;
    }

    public Date getDvd() {
        return dvd;
    }

    public String getTomatoImage() {
        return tomatoImage;
    }

    public String getTomatoConsensus() {
        return tomatoConsensus;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public String getTomatoUrl() {
        return tomatoUrl;
    }

    public String getWebsite() {
        return website;
    }

    public Float getTomatoRating() {
        return tomatoRating;
    }

    public Float getTomatoUserRating() {
        return tomatoUserRating;
    }

    public Integer getTomatoMeter() {
        return tomatoMeter;
    }

    public Integer getTomatoReviews() {
        return tomatoReviews;
    }

    public Integer getTomatoFresh() {
        return tomatoFresh;
    }

    public Integer getTomatoRotten() {
        return tomatoRotten;
    }

    public Integer getTomatoUserMeter() {
        return tomatoUserMeter;
    }

    public Integer getTomatoUserReviews() {
        return tomatoUserReviews;
    }


}