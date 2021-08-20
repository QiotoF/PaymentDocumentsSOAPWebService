package com.qiotof.soapwebservice;

import com.qiotof.soapwebservice.PaymentDocumentRepository;

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
        response.setPaymentDocument(repository.findPaymentDocumentById(request.getId));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPaymentDocumentRequest")
    @ResponsePayload
    public AddPaymentDocumentResponse addPaymentDocument(@RequestPayload AddPaymentDocumentRequest request) {
        repository.addPaymentDocument(request.purpose, request.amount, request.sourceAccount, request.destinationAccount);
    };

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePaymentDocumentRequest")
    @ResponsePayload
    public DeletePaymentDocumentResponse deletePaymentDocument(@RequestPayload DeletePaymentDocumentRequest request) {
        repository.deletePaymentDocument(request.id);
    };

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllPaymentDocumentsRequest")
    @ResponsePayload
    public GetAllPaymentDocumentsResponse deletePaymentDocument(@RequestPayload GetAllPaymentDocumentsRequest request) {
        return repository.getAllPaymentDocuments();
    };
}