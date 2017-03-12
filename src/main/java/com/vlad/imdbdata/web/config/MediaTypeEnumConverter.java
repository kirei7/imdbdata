package com.vlad.imdbdata.basis.config;

import com.vlad.imdbdata.basis.service.MediaType;
import org.springframework.core.convert.converter.Converter;

public class MediaTypeEnumConverter implements Converter<String, MediaType> {

    public MediaType convert(String source) {
        try {
            return MediaType.valueOf(source);
        } catch(Exception e) {
            return null; // or SortEnum.asc
        }
    }
}
