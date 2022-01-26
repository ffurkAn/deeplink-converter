package com.atanriverdi.deeplinkconterter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "deeplink")
public class ApplicationConfiguration {

    private String urlParamWhiteList;
    private String deeplinkParamWhiteList;
}
