package com.tech.challenge.soat.core.utils;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;

import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomMapper extends ModelMapper {
    private static final  CustomMapper INSTANCE = new CustomMapper();

    Set<Class<?>> allowedNullSources = new HashSet<>();

    private CustomMapper(){
        super();
        this.setConfigurations();
    }

    private void setConfigurations() {
        this.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setAmbiguityIgnored(true)
                .setSkipNullEnabled(true);
    }

    @Override
    public  <D> D map(Object source, Class<D> destinationType){
        if(source == null && allowedNullSources.contains(destinationType)){
            source = new Object();
        }
        return super.map(source, destinationType);
    }

    public void allowNullSources(Class<?> destinationType){
        allowedNullSources.add(destinationType);
    }

    public static CustomMapper getInstance(){
        return INSTANCE;
    }
}
