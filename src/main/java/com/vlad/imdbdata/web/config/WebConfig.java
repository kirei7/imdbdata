package com.vlad.imdbdata.basis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService f = super.mvcConversionService();
        f.addConverter(new MediaTypeEnumConverter());
        return f;
    }

}
