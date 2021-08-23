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

        response.setPaymentDocument(repository.findPaymentDocumentById(request.getId()));

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPaymentDocumentRequest")
    @ResponsePayload
    public AddPaymentDocumentResponse addPaymentDocument(@RequestPayload AddPaymentDocumentRequest request) {
        AddPaymentDocumentResponse response = new AddPaymentDocumentResponse();
        response.setPaymentDocument(
                repository.addPaymentDocument(
                        request.getPurpose(),
                        request.getAmount(),
                        request.getSourceAccount(),
                        request.getDestinationAccount()));
        ServiceStatus status = new ServiceStatus();
        status.setStatusCode("SUCCESS");
        status.setMessage("Item successfully added");
        response.setServiceStatus(status);
        return response;
    }

    ;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePaymentDocumentRequest")
    @ResponsePayload
    public DeletePaymentDocumentResponse deletePaymentDocument(@RequestPayload DeletePaymentDocumentRequest request) {
        DeletePaymentDocumentResponse response = new DeletePaymentDocumentResponse();
        PaymentDocument doc = repository.deletePaymentDocument(request.getId());
        ServiceStatus status = new ServiceStatus();

        if (doc == null) {
            status.setStatusCode("FAIL");
            status.setMessage("There is no such id");
        } else {
            status.setStatusCode("SUCCESS");
            status.setMessage("Item successfully deleted");
        }
        response.setServiceStatus(status);
        return response;
    }

    ;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPaymentDocumentsRequest")
    @ResponsePayload
    public GetAllPaymentDocumentsResponse deletePaymentDocument(@RequestPayload GetAllPaymentDocumentsRequest request) {
        GetAllPaymentDocumentsResponse response = new GetAllPaymentDocumentsResponse();
        response.getPaymentDocument().addAll(repository.getAllPaymentDocuments());
        return response;
    }

    ;
}