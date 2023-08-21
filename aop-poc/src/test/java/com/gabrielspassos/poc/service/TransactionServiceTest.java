package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.dto.TransactionDTO;
import com.gabrielspassos.poc.stub.TransactionStub;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    void shouldRunTheLogAspect() {
        transactionService.transactionStuff();
    }

    @Test
    void shouldSendByEmail() {
        TransactionDTO transactionDTO = TransactionStub.create();

        Boolean result = transactionService.sendTransactionByEmail(transactionDTO);

        assertNotNull(result);
    }

    @Test
    void shouldSendBySms() {
        TransactionDTO transactionDTO = TransactionStub.create();

        Boolean result = transactionService.sendTransactionBySms(transactionDTO);

        assertNotNull(result);
    }

}