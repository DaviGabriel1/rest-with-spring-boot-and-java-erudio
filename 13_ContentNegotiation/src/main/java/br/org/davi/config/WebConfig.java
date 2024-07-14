package br.org.davi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        /*configurer.favorParameter(true). //VIA QUERY PARAM
                parameterName("mediaType").
                ignoreAcceptHeader(true).
                useRegisteredExtensionsOnly(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("json",MediaType.APPLICATION_JSON).
                mediaType("xml",MediaType.APPLICATION_XML);*/

        //VIA HEADER PARAM
        configurer.favorParameter(false).
                ignoreAcceptHeader(false).
                useRegisteredExtensionsOnly(false).
                defaultContentType(MediaType.APPLICATION_JSON).
                mediaType("json",MediaType.APPLICATION_JSON).
                mediaType("xml",MediaType.APPLICATION_XML).
                mediaType("x-yaml",MEDIA_TYPE_APPLICATION_YML);


    }
}
