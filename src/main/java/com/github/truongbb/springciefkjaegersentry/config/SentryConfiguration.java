package com.github.truongbb.springciefkjaegersentry.config;

import io.sentry.Sentry;
import io.sentry.spring.SentryExceptionResolver;
import io.sentry.spring.SentryServletContextInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.annotation.PostConstruct;

/**
 * Sentry configuration
 *
 * @author truongbb
 * @see "https://docs.sentry.io/clients/java/integrations/#spring"
 */
@Slf4j
@Configuration
public class SentryConfiguration {

    @PostConstruct
    public void init() {
        try {
            // you can get Sentry DSN by Project >> Setting >> Client Keys (DSN)
            String sentryDSN = "<your sentry DSN>";
//            try {
//                sentryDSN = FileUtil.readTextFile(Constant.SentryConstant.sentryDsnFilePath);
//            } catch (IOException e) {
//                log.error("SentryConfiguration get DSN error");
//            }

            log.info("Init Sentry: {}", sentryDSN);
            Sentry.init(sentryDSN);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    @Bean
    public HandlerExceptionResolver sentryExceptionResolver() {
        return new SentryExceptionResolver();
    }

    @Bean
    public ServletContextInitializer sentryServletContextInitializer() {
        return new SentryServletContextInitializer();
    }

}
