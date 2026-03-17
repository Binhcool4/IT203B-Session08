package Bai6.factory;

import Bai6.discount.*;
import Bai6.payment.*;
import Bai6.notification.*;

public class MobileAppFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscountStrategy() {

        return new FirstTimeDiscount();

    }

    public PaymentMethod createPaymentMethod() {

        return new MomoPayment();

    }

    public NotificationService createNotificationService() {

        return new PushNotification();

    }

}