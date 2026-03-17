package Bai6.factory;

import Bai6.discount.*;
import Bai6.payment.*;
import Bai6.notification.*;

public class POSFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscountStrategy() {

        return new MemberDiscount();

    }

    public PaymentMethod createPaymentMethod() {

        return new CODPayment();

    }

    public NotificationService createNotificationService() {

        return new PrintReceipt();

    }

}