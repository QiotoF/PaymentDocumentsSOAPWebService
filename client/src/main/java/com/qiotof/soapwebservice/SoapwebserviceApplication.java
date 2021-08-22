package com.qiotof.soapwebservice;

import com.example.consumingwebservice.wsdl.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoapwebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoapwebserviceApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(PaymentDocumentClient client) {
        return args -> {

            System.out.println("--- Add PaymentDocument ---");
            String purpose = "For birthday present";
            int amount = 500;
            String sourceAccount = "1234567890";
            String destinationAccount = "0987654321";
            System.out.println("Purpose: " + purpose);
            System.out.println("Amount: " + amount);
            System.out.println("Source Account: " + sourceAccount);
            System.out.println("Destination Account: " + destinationAccount);
            AddPaymentDocumentResponse addPaymentDocumentResponse = client.addPaymentDocument(
                    purpose, amount, sourceAccount, destinationAccount
            );
            ServiceStatus serviceStatus = addPaymentDocumentResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus.getStatusCode());
            System.out.println("Message: " + serviceStatus.getMessage());

            System.out.println("--- Add PaymentDocument ---");
            purpose = "Bribe";
            amount = 999999;
            sourceAccount = "1111155555";
            destinationAccount = "9922773366";
            System.out.println("Purpose: " + purpose);
            System.out.println("Amount: " + amount);
            System.out.println("Source Account: " + sourceAccount);
            System.out.println("Destination Account: " + destinationAccount);
            addPaymentDocumentResponse = client.addPaymentDocument(
                    purpose, amount, sourceAccount, destinationAccount
            );
            serviceStatus = addPaymentDocumentResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus.getStatusCode());
            System.out.println("Message: " + serviceStatus.getMessage());

            System.out.println("--- Add PaymentDocument ---");
            purpose = "To buy a lunch";
            amount = 300;
            sourceAccount = "1010101010";
            destinationAccount = "6363636363";
            System.out.println("Purpose: " + purpose);
            System.out.println("Amount: " + amount);
            System.out.println("Source Account: " + sourceAccount);
            System.out.println("Destination Account: " + destinationAccount);
            addPaymentDocumentResponse = client.addPaymentDocument(
                    purpose, amount, sourceAccount, destinationAccount
            );
            serviceStatus = addPaymentDocumentResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus.getStatusCode());
            System.out.println("Message: " + serviceStatus.getMessage());

            System.out.println("--- Get PaymentDocument by Id ---");
            GetPaymentDocumentByIdResponse getPaymentDocumentByIdResponse = client.getPaymentDocumentById(1);
            PaymentDocument paymentDocument = getPaymentDocumentByIdResponse.getPaymentDocument();
            System.out.println(
                    "id: " + paymentDocument.getId() +
                            "\nPurpose: " + paymentDocument.getPurpose() +
                            "\nAmount: " + paymentDocument.getAmount() +
                            "\nSource Account: " + paymentDocument.getSourceAccount() +
                            "\nDestination Account: " + paymentDocument.getDestinationAccount());

            System.out.println("--- Delete PaymentDocument ---");
            DeletePaymentDocumentResponse deletePaymentDocumentResponse = client.deletePaymentDocument(2);
            serviceStatus = deletePaymentDocumentResponse.getServiceStatus();
            System.out.println("StatusCode: " + serviceStatus.getStatusCode());
            System.out.println("Message: " + serviceStatus.getMessage());

            System.out.println("--- Get all PaymentDocuments ---");
            GetAllPaymentDocumentsResponse getAllPaymentDocumentsResponse = client.getAllPaymentDocuments();
            getAllPaymentDocumentsResponse.getPaymentDocument()
                    .forEach(e -> System.out.println(
                            e.getId() + " " +
                                    e.getPurpose() + " "
                                    + e.getAmount() + " "
                                    + e.getSourceAccount() + " "
                                    + e.getDestinationAccount()));

        };
    }
}
