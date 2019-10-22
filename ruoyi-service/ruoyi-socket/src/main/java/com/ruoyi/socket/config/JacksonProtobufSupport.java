package com.ruoyi.socket.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hubspot.jackson.datatype.protobuf.ProtobufModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonProtobufSupport {

  @Bean
  @SuppressWarnings("unchecked")
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return jacksonObjectMapperBuilder -> {
      jacksonObjectMapperBuilder.featuresToDisable(
          JsonGenerator.Feature.IGNORE_UNKNOWN,
          MapperFeature.DEFAULT_VIEW_INCLUSION,
          DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
          SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
      );
      jacksonObjectMapperBuilder.propertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE);//如果字段都是驼峰命名规则，需要这一句
      jacksonObjectMapperBuilder.modulesToInstall(ProtobufModule.class);
    };
  }

}