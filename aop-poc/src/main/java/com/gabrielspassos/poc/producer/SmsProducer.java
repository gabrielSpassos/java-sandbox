package com.gabrielspassos.poc.producer;

import com.gabrielspassos.poc.dto.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class SmsProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailProducer.class);

    public boolean produceSms(TransactionDTO transactionDTO) {
        LOGGER.info("Produced SMS for {}", transactionDTO);

        return getRandom() % 2 == 0;
    }

    private int getRandom() {
        Random random = new Random();
        int max = 10;
        int min = 0;
        return random.nextInt(max - min) + min;
    }
}
