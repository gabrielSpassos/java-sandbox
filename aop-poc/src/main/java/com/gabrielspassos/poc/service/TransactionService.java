package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.annotation.MyCustomLog;
import com.gabrielspassos.poc.annotation.MyProducerMetrics;
import com.gabrielspassos.poc.dto.TransactionDTO;
import com.gabrielspassos.poc.producer.EmailProducer;
import com.gabrielspassos.poc.producer.SmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    @Autowired
    private EmailProducer emailProducer;

    @Autowired
    private SmsProducer smsProducer;

    @MyCustomLog
    // this here is what's called a join point
    public void transactionStuff(){
        System.out.println("In Service");
    }

    @MyCustomLog
    @MyProducerMetrics
    public boolean sendTransactionByEmail(TransactionDTO transactionDTO) {
        return emailProducer.produceEmail(transactionDTO);
    }

    @MyCustomLog
    @MyProducerMetrics
    public boolean sendTransactionBySms(TransactionDTO transactionDTO) {
        return smsProducer.produceSms(transactionDTO);
    }
}
