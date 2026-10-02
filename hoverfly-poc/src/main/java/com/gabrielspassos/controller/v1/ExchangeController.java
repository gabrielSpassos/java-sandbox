package com.gabrielspassos.controller.v1;

import com.gabrielspassos.controller.v1.response.ExchangeResponse;
import com.gabrielspassos.mapper.ExchangeMapper;
import com.gabrielspassos.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @PostMapping("/users/{userId}/exchanges/usd/brl")
    public ResponseEntity<ExchangeResponse> exchangeUsdToBrl(@PathVariable String userId) {
        var exchange = exchangeService.getUsdExchange(userId);
        var exchangeResponse = ExchangeMapper.toResponse(exchange);
        return ResponseEntity.status(HttpStatus.OK).body(exchangeResponse);
    }

}
