package com.qiotof.soapwebservice;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import io.spring.guides.gs_producing_web_service.PaymentDocument;

@Component
public class PaymentDocumentRepository {

    private static final Map<Integer, PaymentDocument> paymentDocuments = new HashMap<>();

    private static int id = 0;

    private static int nextId() {
        id++;
        return id;
    }

    public PaymentDocument findPaymentDocumentById(int id) throws Exception {
        PaymentDocument doc = paymentDocuments.get(id);
        if (doc == null)
            throw new Exception("No such element!");
        return doc;
    }

    public PaymentDocument addPaymentDocument(String purpose, int amount, String sourceAccount, String destinationAccount) {
        PaymentDocument doc = new PaymentDocument();
        doc.setId(nextId());
        doc.setPurpose(purpose);
        doc.setAmount(amount);
        doc.setSourceAccount(sourceAccount);
        doc.setDestinationAccount(destinationAccount);
        paymentDocuments.put(doc.getId(), doc);
        return doc;
    }

    public void deletePaymentDocument(int id) {
        paymentDocuments.remove(id);
    }

    public List<PaymentDocument> getAllPaymentDocuments() {
        return new ArrayList<PaymentDocument>(paymentDocuments.values());
    }

}