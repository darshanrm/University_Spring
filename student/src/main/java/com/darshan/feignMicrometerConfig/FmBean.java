package com.darshan.feignMicrometerConfig;

import feign.micrometer.MicrometerCapability;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;

public class FmBean {
    @Bean
    public MicrometerCapability capability(final MeterRegistry registry) {
        return new MicrometerCapability(registry);
    }
}
