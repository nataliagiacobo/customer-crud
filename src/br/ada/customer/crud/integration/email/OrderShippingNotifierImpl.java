package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.IShippingNotifierUseCase;

public class OrderShippingNotifierImpl implements IShippingNotifierUseCase {

    private SendEmail sendEmail;

    public OrderShippingNotifierImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@loja.com", order.getCustomer().getEmail(), "Pedido enviado!");
    }
}
