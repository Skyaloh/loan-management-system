package com.credable.lms.config;


import com.credable.lms.util.SOAPConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
public class SoapClientConfig {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan    ("com.credable.lms.service.soap.api.generated");
       // marshaller.setContextPath("com.credable.lms.service.soap.api.generated");
        return marshaller;
    }

    @Bean
    public SOAPConnector soapConnector(Jaxb2Marshaller jaxb2Marshaller) {
        SOAPConnector soapConnector = new SOAPConnector();
        soapConnector.setMarshaller(jaxb2Marshaller);
        soapConnector.setUnmarshaller(jaxb2Marshaller);
        return soapConnector;
    }
}
