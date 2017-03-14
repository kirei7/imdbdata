package com.vlad.imdbdata.basis.batch.processor.util;

import com.vlad.imdbdata.basis.entity.CommonMediaInfo;
import com.vlad.imdbdata.basis.entity.EpisodeInfo;
import com.vlad.imdbdata.basis.entity.SeriesInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;

public class DataMapper {
    private static final Logger LOGGER
            = LoggerFactory.getLogger("Processor DataMapper");

    public static CommonMediaInfo mapToCommonEntity(Map<String, String> map) {
        CommonMediaInfo entity = new CommonMediaInfo();
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd MMM yyyy",
                Locale.US
        );
        Long released = null, dvd = null;
        try {
            released = formatter.parse(map.get("Released")).getTime();
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        try {
            dvd = formatter.parse(map.get("DVD")).getTime();
        } catch (ParseException ex) {
            LOGGER.error(ex.getMessage());
        }
        String str = map.get("tomatoUserMeter").replace(",", "");
        if (isValidInt(str))
            entity.setTomatoUserMeter(new Integer(str));
        str = map.get("tomatoUserReviews").replace(",", "");
        if (isValidInt(str))
            entity.setTomatoUserReviews(new Integer(str));
        str = map.get("tomatoReviews").replace(",", "");
        if (isValidInt(str))
            entity.setTomatoReviews(new Integer(str));
        str = map.get("tomatoFresh").replace(",", "");
        if (isValidInt(str))
            entity.setTomatoFresh(new Integer(str));
        str = map.get("tomatoRotten").replace(",", "");
        if (isValidInt(str))
            entity.setTomatoRotten(new Integer(str));
        str = map.get("tomatoMeter").replace(",", "");
        if (isValidInt(str))
            entity.setTomatoMeter(new Integer(str));
        str = map.get("tomatoMeter").replace(",", "");
        if (isValidInt(str))
            entity.setTomatoMeter(new Integer(str));
        str = map.get("imdbVotes").replace(",", "");
        if (isValidInt(str))
            entity.setImdbVotes(new Integer(str));
        str = map.get("Metascore").replace(",", "");
        if (isValidInt(str))
            entity.setMetaScore(new Integer(str));
        str = map.get("imdbRating");
        if (isValidFloat(str))
            entity.setImdbRating(new Float(str));
        str = map.get("tomatoUserRating");
        if (isValidFloat(str))
            entity.setTomatoUserRating(new Float(str));
        str = map.get("tomatoRating");
        if (isValidFloat(str))
            entity.setTomatoRating(new Float(str));

        return entity
                .withReleased(released)
                .withDvd(dvd)
                .withTitle(map.get("Title"))
                .withYear(map.get("Year"))
                .withRated(map.get("Rated"))
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
                .withBoxOffice(map.get("BoxOffice"))
                .withProduction(map.get("Production"))
                .withWebsite(map.get("Website"))
                .withResponse(map.get("Response"));
    }

    public static SeriesInfo mapToSeriesEntity(Map<String, String> map) {
        CommonMediaInfo temp = mapToCommonEntity(map);
        SeriesInfo entity = new SeriesInfo(temp);
        String str = map.get("totalSeasons");
        if (isValidInt(str))
            entity.setTotalSeasons(Integer.parseInt(str));
        return entity;
    }

    public static EpisodeInfo mapToEpisodeEntity(Map<String, String> map) {
        CommonMediaInfo temp = mapToCommonEntity(map);
        EpisodeInfo entity = new EpisodeInfo(temp);
        String str = map.get("Season");
        if (isValidInt(str))
            entity.setSeasonNum(Integer.parseInt(str));
        str = map.get("Episode");
        if (isValidInt(str))
            entity.setEpisodeNum(Integer.parseInt(str));
        return entity.withSeriesId(map.get("seriesID"));
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
