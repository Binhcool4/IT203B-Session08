package Bai6.factory;

import Bai6.discount.*;
import Bai6.payment.*;
import Bai6.notification.*;

public interface SalesChannelFactory {

    DiscountStrategy createDiscountStrategy();

    PaymentMethod createPaymentMethod();

    NotificationService createNotificationService();

}