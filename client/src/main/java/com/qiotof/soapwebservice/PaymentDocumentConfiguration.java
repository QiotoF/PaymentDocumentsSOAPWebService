package com.qiotof.soapwebservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class PaymentDocumentConfiguration {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.qiotof.soapwebservice.wsdl");
        return marshaller;
    }

    @Bean
    public PaymentDocumentClient paymentDocumentClient(Jaxb2Marshaller marshaller) {
        PaymentDocumentClient client = new PaymentDocumentClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
