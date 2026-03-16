package service;

import com.gabrielspassos.annotation.LatencyTracked;

import java.util.List;
import java.util.Random;

public class PaymentService {

    private final Random random = new Random();

    @LatencyTracked("payment.checkout")
    public List<String> checkoutPayment() {
        simulateWork(50);

        return List.of("itemA", "itemB");
    }

    @LatencyTracked("payment.process")
    public String processPayment() {
       if (!validatePayment()) {
           throw new IllegalStateException("test exception");
       }

        simulateWork(120);

        return "payment-ok";
    }

    @LatencyTracked("payment.validate")
    private boolean validatePayment() {
        simulateWork(80);

        return true;
    }

    private void simulateWork(int maxMillis) {
        try {
            Thread.sleep(random.nextInt(maxMillis));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
