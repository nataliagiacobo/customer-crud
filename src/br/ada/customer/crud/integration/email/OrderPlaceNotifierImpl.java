package br.ada.customer.crud.integration.email;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.INotifierOrderPlaceUseCase;

public class OrderPlaceNotifierImpl implements INotifierOrderPlaceUseCase {

    SendEmail sendEmail;

    public OrderPlaceNotifierImpl(SendEmail sendEmail) {
        this.sendEmail = sendEmail;
    }

    @Override
    public void notify(Order order) {
        sendEmail.send("comunicado@loja.com", order.getCustomer().getEmail(), "Pedido realizado aguardando pagamento!");
    }
}
