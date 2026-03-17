package Bai6;

import Bai6.discount.*;
import Bai6.payment.*;
import Bai6.notification.*;
import Bai6.factory.*;

public class OrderService {

    private DiscountStrategy discount;

    private PaymentMethod payment;

    private NotificationService notification;

    public OrderService(SalesChannelFactory factory) {

        discount = factory.createDiscountStrategy();
        payment = factory.createPaymentMethod();
        notification = factory.createNotificationService();

    }

    public void processOrder(double price, int quantity) {

        double total = price * quantity;

        double finalAmount = discount.applyDiscount(total);

        payment.pay(finalAmount);

        notification.notifyUser();

    }

}