package com.credable.lms.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
public class RestTemplateHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestTemplateHelper.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public RestTemplateHelper(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public <T, R> T getForEntity(HttpHeaders headers, Class<T> clazz, String logMessage, String url)
        throws HttpClientErrorException {
        LOGGER.debug("RestTemplate GET request: {}", logMessage);

        HttpEntity<R> requestHeaders = new HttpEntity<>(headers);

        //  ResponseEntity<String> response = restTemplate.getForEntity(url, String.class,params);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestHeaders, String.class);
        LOGGER.info("Server Response {}", response);
        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);
        return readValue(response, javaType);
    }


    public <T, R> T postForEntity(HttpHeaders headers, Class<T> clazz, String logMessage, String url, R body)
        throws JsonProcessingException {
        LOGGER.info("Server Request: ({}) {} {}", logMessage, objectMapper.writeValueAsString(body), url);

        HttpEntity<R> request = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        LOGGER.info("Server response: ({}) {}", logMessage, objectMapper.writeValueAsString(response.getBody()));

        JavaType javaType = objectMapper.getTypeFactory().constructType(clazz);

        return readValue(response, javaType);
    }


    private <T> T readValue(ResponseEntity<String> response, JavaType javaType) {
        T result = null;
        if (response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.CREATED) {
            try {
                result = objectMapper.readValue(response.getBody(), javaType);
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        } else {
            LOGGER.info("No data found {}", response.getStatusCode());
        }
        return result;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
