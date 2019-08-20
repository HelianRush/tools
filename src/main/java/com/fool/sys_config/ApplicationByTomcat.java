package com.fool.sys_config;

import com.fool.ToolsApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ApplicationByTomcat extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        setRegisterErrorPageFilter(false);
        return builder.sources(ToolsApplication.class);
    }
}