package iwkms.ecommerce.libs.exception;

public class PaymentFailedException extends RuntimeException {
    
    public PaymentFailedException(String message) {
        super(message);
    }
}
