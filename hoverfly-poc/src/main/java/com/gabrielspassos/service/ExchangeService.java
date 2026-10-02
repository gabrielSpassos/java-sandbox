package com.gabrielspassos.service;

import com.gabrielspassos.client.ExchangeClient;
import com.gabrielspassos.client.response.UsdResponse;
import com.gabrielspassos.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExchangeService {

    @Autowired
    private UserService userService;

    @Autowired
    private ExchangeClient exchangeClient;

    public UsdResponse getUsdExchange(String userId) {
        UserEntity user = userService.findById(userId);

        return exchangeClient.getUsdToBrl();
    }
}
