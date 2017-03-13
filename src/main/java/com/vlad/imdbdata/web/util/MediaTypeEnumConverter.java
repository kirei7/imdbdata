package com.vlad.imdbdata.web.util;

import com.vlad.imdbdata.basis.service.MediaType;
import org.springframework.core.convert.converter.Converter;

public class MediaTypeEnumConverter implements Converter<String, MediaType> {

    public MediaType convert(String source) {
        try {
            return MediaType.valueOf(source.toUpperCase());
        } catch(Exception e) {
            return null;
        }
    }
}
