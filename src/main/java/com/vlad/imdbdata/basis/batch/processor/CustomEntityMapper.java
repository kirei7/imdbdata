package com.vlad.imdbdata.basis.batch.processor;

import com.vlad.imdbdata.basis.entity.CommonMediaInfo;
import com.vlad.imdbdata.basis.entity.SeriesEpisodeInfo;
import com.vlad.imdbdata.basis.service.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by vlad on 13.03.17.
 */
public class CustomEntityMapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomEntityMapper.class);

    public CommonMediaInfo mapToCommonEntity(Map<String, String> map) {
            CommonMediaInfo.EntityBuilder builder
                    = CommonMediaInfo.entityBuilder();
            mapToCommonEntity(map, builder);
            return builder.build();
    }
    public SeriesEpisodeInfo mapToSeriesEntity(Map<String, String> map) {
            SeriesEpisodeInfo.SeriesEntityBuilder builder
                    = SeriesEpisodeInfo.entityBuilder();
            mapToEpisodeEntity(map, builder);
            return builder.build();
    }

    public CommonMediaInfo.EntityBuilder mapToCommonEntity(Map<String, String> map, CommonMediaInfo.EntityBuilder builder) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd MMM yyyy",
                Locale.US
        );
        Date released = null, dvd = null;
        try {
            released = formatter.parse(map.get("Released"));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        try {
            dvd = formatter.parse(map.get("DVD"));
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        String str = map.get("tomatoUserMeter").replace(",", "");
        if (isValidInt(str))
            builder.withTomatoUserMeter(new Integer(str));
        str = map.get("tomatoUserReviews").replace(",", "");
        if (isValidInt(str))
            builder.withTomatoUserReviews(new Integer(str));
        str = map.get("tomatoReviews").replace(",", "");
        if (isValidInt(str))
            builder.withTomatoReviews(new Integer(str));
        str = map.get("tomatoFresh").replace(",", "");
        if (isValidInt(str))
            builder.withTomatoFresh(new Integer(str));
        str = map.get("tomatoRotten").replace(",", "");
        if (isValidInt(str))
            builder.withTomatoRotten(new Integer(str));
        str = map.get("tomatoMeter").replace(",", "");
        if (isValidInt(str))
            builder.withTomatoMeter(new Integer(str));
        str = map.get("tomatoMeter").replace(",", "");
        if (isValidInt(str))
            builder.withTomatoMeter(new Integer(str));
        str = map.get("imdbVotes").replace(",", "");
        if (isValidInt(str))
            builder.withImdbVotes(new Integer(str));
        str = map.get("Metascore").replace(",", "");
        if (isValidInt(str))
            builder.withMetaScore(new Integer(str));
        str = map.get("imdbRating");
        if (isValidFloat(str))
            builder.withImdbRating(new Float(str));
        str = map.get("tomatoUserRating");
        if (isValidFloat(str))
            builder.withTomatoUserRating(new Float(str));
        str = map.get("tomatoRating");
        if (isValidFloat(str))
            builder.withTomatoRating(new Float(str));

        return builder
                .withTitle(map.get("Title"))
                .withYear(map.get("Year"))
                .withRated(map.get("Rated"))
                .withReleased(released)
                .withRuntime(map.get("Runtime"))
                .withGenre(map.get("Genre"))
                .withDirector(map.get("Director"))
                .withWriter(map.get("Writer"))
                .withActors(map.get("Actors"))
                .withPlotShort(map.get("Plot"))
                .withPlotFull(map.get("plotFull"))
                .withLanguage(map.get("Language"))
                .withCountry(map.get("Country"))
                .withAwards(map.get("Awards"))
                .withPosterUrl(map.get("Poster"))
                .withImdbId(map.get("imdbID"))
                .withType(map.get("Type"))
                .withTomatoImage(map.get("tomatoImage"))
                .withTomatoConsensus(map.get("tomatoConsensus"))
                .withTomatoUrl(map.get("tomatoURL"))
                .withDvd(dvd)
                .withBoxOffice(map.get("BoxOffice"))
                .withProduction(map.get("Production"))
                .withWebsite(map.get("Website"))
                .withResponse(map.get("Response"));
    }

    public SeriesEpisodeInfo.SeriesEntityBuilder mapToEpisodeEntity(Map<String, String> map, SeriesEpisodeInfo.SeriesEntityBuilder builder) {
        if (map.get("Type").toLowerCase().equals("episode")) {
            String str = map.get("Season");
            if (isValidInt(str))
                builder.withSeasonNum(new Integer(str));
            str = map.get("Episode");
            if (isValidInt(str))
                builder.withEpisodeNum(new Integer(str));
            str = map.get("seriesID");
            builder.withSeriesId(str);
        }
        builder = (SeriesEpisodeInfo.SeriesEntityBuilder) mapToCommonEntity(map, builder);
        return builder;
    }

    private CommonMediaInfo.EntityBuilder createBuilder(MediaType type) {
        switch (type) {
            case SERIES:
                return SeriesEpisodeInfo.entityBuilder();
            case MOVIE:
            default:
                return CommonMediaInfo.entityBuilder();
        }

    }

    //check if a given string is valid to convert to a numeric type
    private static boolean isValidInt(String str) {
        try {
            new Integer(str);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }

    private static boolean isValidFloat(String str) {
        try {
            new Float(str);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
