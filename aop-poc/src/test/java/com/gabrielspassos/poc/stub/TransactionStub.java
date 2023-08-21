package com.gabrielspassos.poc.stub;

import com.gabrielspassos.poc.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.UUID;

public class TransactionStub {

    public static TransactionDTO create() {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setId(UUID.randomUUID().toString());
        transactionDTO.setType("Debit Card");
        transactionDTO.setAccount("123456");
        transactionDTO.setAmount(BigDecimal.TEN);
        return transactionDTO;
    }

}
