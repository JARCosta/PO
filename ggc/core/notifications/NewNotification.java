package ggc.core.notifications;

import ggc.core.products.Product;

public class NewNotification extends AbstractNotification {
    public NewNotification(Product product, double price) {
        super("NEW", product, price);
    }

    @Override
    void update() {
        super.update();
        _observers.clear();
    }
    }
