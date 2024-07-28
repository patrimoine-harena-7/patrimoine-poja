package com.haren.api.config;

import com.haren.api.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.time.LocalDate;

public class JacksonConfig {

    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer());
        mapper.registerModule(module);
        return mapper;
    }

    public static void main(String[] args) throws Exception {
        ObjectMapper mapper = createObjectMapper();
        LocalDate date = LocalDate.now();
        String json = mapper.writeValueAsString(date);
        System.out.println(json);
    }
}


