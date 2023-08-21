package com.gabrielspassos.poc.aspect;

import com.gabrielspassos.poc.dto.TransactionDTO;
import com.gabrielspassos.poc.dto.TransactionMetricDTO;
import com.gabrielspassos.poc.producer.MetricsProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
public class ProducerMetricsAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducerMetricsAspect.class);

    @Autowired
    private MetricsProducer metricsProducer;

    @Pointcut("@annotation(com.gabrielspassos.poc.annotation.MyProducerMetrics)")
    public void producerMetricsPointcut() {
    }

    @AfterReturning(pointcut = "producerMetricsPointcut()", returning = "result")
    public void afterProducerMetricsPointCut(JoinPoint joinPoint, Object result) {
        LOGGER.info("Returning for class: {} ; Method : {} ",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName()
        );

        Optional<TransactionDTO> transactionDTOOptional = Arrays.stream(joinPoint.getArgs())
                .filter(arg -> arg instanceof TransactionDTO)
                .map(arg -> (TransactionDTO) arg)
                .findFirst();

        if (!transactionDTOOptional.isPresent()) {
            LOGGER.info("Without transaction to send to metrics");
            return;
        }

        TransactionMetricDTO transactionMetricDTO = new TransactionMetricDTO(transactionDTOOptional.get());
        if (result instanceof Boolean) {
            transactionMetricDTO.setSuccess((Boolean) result);
        }

        metricsProducer.produceMetrics(transactionMetricDTO);
    }
}
