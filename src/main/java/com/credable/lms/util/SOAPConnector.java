package com.credable.lms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;



public class SOAPConnector extends WebServiceGatewaySupport {

    private final Logger log = LoggerFactory.getLogger(SOAPConnector.class.getName());

    public <T> T callWebService(Object request, String requestType, String customUri) {
        log.info("Sending {} request for: {} ",requestType, request);
        WebServiceTemplate webServiceTemplate = getWebServiceTemplate();
        webServiceTemplate.setDefaultUri(customUri);
        return  (T)webServiceTemplate.marshalSendAndReceive(customUri,request,new TokenHeaderRequestCallback());
    }
}
