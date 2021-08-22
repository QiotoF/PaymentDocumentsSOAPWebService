package com.qiotof.soapwebservice;

import com.qiotof.soapwebservice.PaymentDocumentRepository;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class PaymentDocumentEndpoint {

    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private PaymentDocumentRepository repository;

    @Autowired
    public PaymentDocumentEndpoint(PaymentDocumentRepository repository) {
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPaymentDocumentByIdRequest")
    @ResponsePayload
    public GetPaymentDocumentByIdResponse getPaymentDocument(@RequestPayload GetPaymentDocumentByIdRequest request) {
        GetPaymentDocumentByIdResponse response = new GetPaymentDocumentByIdResponse();

        try {
            response.setPaymentDocument(repository.findPaymentDocumentById(request.getId()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPaymentDocumentRequest")
    @ResponsePayload
    public AddPaymentDocumentResponse addPaymentDocument(@RequestPayload AddPaymentDocumentRequest request) {
        AddPaymentDocumentResponse response = new AddPaymentDocumentResponse();
        repository.addPaymentDocument(request.getPurpose(), request.getAmount(), request.getSourceAccount(), request.getDestinationAccount());
        return response;
    };

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePaymentDocumentRequest")
    @ResponsePayload
    public DeletePaymentDocumentResponse deletePaymentDocument(@RequestPayload DeletePaymentDocumentRequest request) {
        DeletePaymentDocumentResponse response = new DeletePaymentDocumentResponse();
        repository.deletePaymentDocument(request.getId());
        return response;
    };

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPaymentDocumentsRequest")
    @ResponsePayload
    public GetAllPaymentDocumentsResponse deletePaymentDocument(@RequestPayload GetAllPaymentDocumentsRequest request) {
        GetAllPaymentDocumentsResponse response = new GetAllPaymentDocumentsResponse();
        response.getPaymentDocument().addAll(repository.getAllPaymentDocuments());
        return response;
    };
}