package com.vlad.imdbdata.web.config;

import com.vlad.imdbdata.web.util.MediaTypeEnumConverter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
@ComponentScan("com.vlad.imdbdata.web.controller")
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService f = super.mvcConversionService();
        f.addConverter(new MediaTypeEnumConverter());
        return f;
    }

}
