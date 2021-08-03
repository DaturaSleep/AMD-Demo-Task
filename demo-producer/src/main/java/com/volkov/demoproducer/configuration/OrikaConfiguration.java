package com.volkov.demoproducer.configuration;

import com.volkov.demoproducer.entity.DemoUser;
import com.volkov.demoproducer.entity.dto.DemoUserDTO;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrikaConfiguration {
    @Bean
    public MapperFacade mapperFacade() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new CustomConverter<DemoUserDTO, DemoUser>() {
            @Override
            public DemoUser convert(DemoUserDTO source, Type<? extends DemoUser> destinationType, MappingContext mappingContext) {
                return new DemoUser(source.getUsername(), source.getFirstname());
            }
        });
        mapperFactory.getConverterFactory().registerConverter(new CustomConverter<DemoUser, DemoUserDTO>() {
            @Override
            public DemoUserDTO convert(DemoUser source, Type<? extends DemoUserDTO> destinationType, MappingContext mappingContext) {
                return new DemoUserDTO(source.getUsername(), source.getFirstname());
            }
        });
        return mapperFactory.getMapperFacade();
    }
}
