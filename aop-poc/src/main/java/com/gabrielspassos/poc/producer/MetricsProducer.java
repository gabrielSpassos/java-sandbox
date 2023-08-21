package com.gabrielspassos.poc.producer;

import com.gabrielspassos.poc.dto.TransactionMetricDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MetricsProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsProducer.class);

    public boolean produceMetrics(TransactionMetricDTO transactionMetricDTO) {
        LOGGER.info("Produced metric for {}", transactionMetricDTO);
        return true;
    }
}
