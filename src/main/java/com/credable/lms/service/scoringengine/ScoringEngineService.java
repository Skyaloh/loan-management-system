package com.credable.lms.service.scoringengine;

import com.credable.lms.service.SystemConfigQueryService;
import com.credable.lms.service.dto.SystemConfigDTO;
import com.credable.lms.service.scoringengine.response.CustomerScoreResponse;
import com.credable.lms.util.RestTemplateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class ScoringEngineService {
    private final Logger loggerFactory = LoggerFactory.getLogger(ScoringEngineService.class);

    private static final String TOKEN_SCORE_QUERY_API = "/api/v1/scoring/initiateQueryScore/";
    private static final String CUSTOMER_SCORE_QUERY_API = "/api/v1/scoring/queryScore/";
    private static final String MIDDLEWARE_TOKEN_API = "/api/middleware/access-token";
    private static final String KEY_MIDDLEWARE_USERNAME = "MIDDLEWARE_USERNAME";
    private static final String KEY_MIDDLEWARE_PASSWORD = "MIDDLEWARE_PASSWORD";
    private static final String KEY_MIDDLEWARE_ENDPOINT = "MIDDLEWARE_ENDPOINT";
    private static final String KEY_MAIN_SCORE_ENGINE_ENDPOINT = "MAIN_SCORE_ENGINE_ENDPOINT";
    private final SystemConfigQueryService systemConfigQueryService;

    private final RestTemplateHelper restTemplateHelper;

    public ScoringEngineService(SystemConfigQueryService systemConfigQueryService, RestTemplateHelper restTemplateHelper) {
        this.systemConfigQueryService = systemConfigQueryService;
        this.restTemplateHelper = restTemplateHelper;
    }

    //Getting client access token from middleware
    public String getClientAccessTokenFromMiddleware() {
        loggerFactory.info("Getting Client access token from middleware ");
        String username = getValue(KEY_MIDDLEWARE_USERNAME);
        String password = getValue(KEY_MIDDLEWARE_PASSWORD);
        String middlewareEndpoint = getValue(KEY_MIDDLEWARE_ENDPOINT);
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        return restTemplateHelper.getForEntity(new HttpHeaders(),String.class,
                "Getting client access token from middleware",middlewareEndpoint+MIDDLEWARE_TOKEN_API);
    }

    //Getting customer score from main score engine
    public CustomerScoreResponse getCustomerScore(String customerNumber, String clientAccessToken) {
        String mainScoreEngineEndpoint = getValue(KEY_MAIN_SCORE_ENGINE_ENDPOINT);
        loggerFactory.info("Getting Score for customer: {} ", customerNumber);
        String customerQueryScoreEngineToken = getCustomerQueryScoreEngineToken(customerNumber,clientAccessToken);
        HttpHeaders headers = getHeaders(clientAccessToken);
        return restTemplateHelper.getForEntity(headers,CustomerScoreResponse.class, "Getting score from main score engine",
                mainScoreEngineEndpoint+CUSTOMER_SCORE_QUERY_API+customerQueryScoreEngineToken);

    }

    //Getting customer token from main score engine
    @Retryable(value = { Exception.class }, maxAttempts = 3)
    private String getCustomerQueryScoreEngineToken(String customerNumber,String clientAccessToken) {
        loggerFactory.info("Getting Customer token for: {} ", customerNumber);
        String mainScoreEngineEndpoint = getValue(KEY_MAIN_SCORE_ENGINE_ENDPOINT);
        HttpHeaders headers = getHeaders(clientAccessToken);
        return restTemplateHelper.getForEntity(headers,String.class
               , "Getting customer token from main score engine",
                mainScoreEngineEndpoint+TOKEN_SCORE_QUERY_API+customerNumber);
    }

    private HttpHeaders getHeaders(String clientAccessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.set("client-token", clientAccessToken);
        return headers;
    }

    private String getValue(String code){
        return systemConfigQueryService.findOneByCode(code).map(SystemConfigDTO::getValue).orElse(null);
    }

}
