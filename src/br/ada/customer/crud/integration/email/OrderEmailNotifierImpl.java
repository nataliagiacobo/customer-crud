package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierPaidOrderUseCase;

public class OrderEmailNotifierImpl implements INotifierPaidOrderUseCase {

    private SendEmail sendEmail;

    public OrderEmailNotifierImpl(SendEmail sendEmail){ this.sendEmail = sendEmail; }
    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@loja.com", order.getCustomer().getEmail(), "Pedido pago!");
    }
}
