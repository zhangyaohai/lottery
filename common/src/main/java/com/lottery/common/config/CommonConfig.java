package com.lottery.common.config;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.lottery.common.kit.AESKit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfig {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setVersion(1.0)
                .disableInnerClassSerialization()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+0800")
                .create();
    }

    @Bean
    public AESKit aes() {
        return AESKit.me();
    }

}
