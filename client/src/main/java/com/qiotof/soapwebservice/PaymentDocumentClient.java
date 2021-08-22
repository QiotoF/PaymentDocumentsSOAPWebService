package com.qiotof.soapwebservice;

import com.example.consumingwebservice.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class PaymentDocumentClient extends WebServiceGatewaySupport {

    private static final Logger log = LoggerFactory.getLogger(PaymentDocumentClient.class);
    private static final String addressRoot = "http://localhost:8080/ws/";

    public GetPaymentDocumentByIdResponse getPaymentDocumentById(int id) {
        GetPaymentDocumentByIdRequest request = new GetPaymentDocumentByIdRequest();
        request.setId(id);

        log.info("Requesting Payment Document for id " + id);

        GetPaymentDocumentByIdResponse response = (GetPaymentDocumentByIdResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(addressRoot + "getPaymentDocumentByIdRequest")
        );
        return response;
    }

    public AddPaymentDocumentResponse addPaymentDocument(String purpose, int amount, String sourceAccount, String destinationAccount) {
        AddPaymentDocumentRequest request = new AddPaymentDocumentRequest();
        request.setPurpose(purpose);
        request.setAmount(amount);
        request.setSourceAccount(sourceAccount);
        request.setDestinationAccount(destinationAccount);

        log.info("Adding Payment Document");
        log.info("Purpose: " + purpose);
        log.info("Amount: " + amount);
        log.info("Source Account: " + sourceAccount);
        log.info("Destination Account: " + destinationAccount);

        AddPaymentDocumentResponse response = (AddPaymentDocumentResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(addressRoot + "addPaymentDocumentRequest")
        );
        return response;
    }

    public DeletePaymentDocumentResponse deletePaymentDocument(int id) {
        DeletePaymentDocumentRequest request = new DeletePaymentDocumentRequest();
        request.setId(id);

        log.info("Deleting item with id " + id);

        DeletePaymentDocumentResponse response = (DeletePaymentDocumentResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(addressRoot + "deletePaymentDocumentRequest")
        );
        return response;
    }

    public GetAllPaymentDocumentsResponse getAllPaymentDocuments() {
        GetAllPaymentDocumentsRequest request = new GetAllPaymentDocumentsRequest();

        log.info("Requesting all Payment Documents");

        GetAllPaymentDocumentsResponse response = (GetAllPaymentDocumentsResponse) getWebServiceTemplate().marshalSendAndReceive(
                request, new SoapActionCallback(addressRoot + "getAllPaymentDocumentsRequest")
        );
        return response;
    }
}
