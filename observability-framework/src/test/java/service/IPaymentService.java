package service;

import java.util.List;

public interface IPaymentService {

    List<String> checkoutPayment();

    String processPayment();

}
