package Bai6.factory;

import Bai6.discount.*;
import Bai6.payment.*;
import Bai6.notification.*;

public class WebsiteFactory implements SalesChannelFactory {

    public DiscountStrategy createDiscountStrategy() {

        return new WebsiteDiscount();

    }

    public PaymentMethod createPaymentMethod() {

        return new CreditCardPayment();

    }

    public NotificationService createNotificationService() {

        return new EmailNotification();

    }

}