package ggc.core.notifications;

import ggc.core.products.Product;

public class BargainNotification extends AbstractNotification {
    public BargainNotification(Product product, double price) {
        super("BARGAIN", product, price);
    }
}
