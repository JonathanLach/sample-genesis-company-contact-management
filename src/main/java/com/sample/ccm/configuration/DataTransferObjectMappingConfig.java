package com.sample.ccm.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataTransferObjectMappingConfig {

    @Bean
    public ModelMapper modelMapper()  {
        return new ModelMapper();
    }
}
