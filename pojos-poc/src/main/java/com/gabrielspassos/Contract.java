package com.gabrielspassos;

import com.gabrielspassos.innter.ConnectCreditCardRequest;
import com.gabrielspassos.innter.UpdateCreditCardResponse;

public interface Contract {

    UpdateCreditCardResponse update(ConnectCreditCardRequest request);
}
