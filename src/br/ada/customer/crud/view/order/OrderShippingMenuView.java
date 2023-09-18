package br.ada.customer.crud.view.order;

import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.ICustomerUseCase;
import br.ada.customer.crud.usecases.IOrderShippingUseCase;
import br.ada.customer.crud.usecases.IOrderUseCase;
import br.ada.customer.crud.view.component.menu.AbstractMenuOptionView;
import br.ada.customer.crud.view.component.menu.MenuOptionView;
import br.ada.customer.crud.view.component.text.impl.TextInputView;

public class OrderShippingMenuView extends AbstractMenuOptionView implements MenuOptionView {

    public static final String MENU_CODE = "5";
    public static final Integer MENU_ORDER = 5;
    private ICustomerUseCase customerUseCase;
    private IOrderUseCase orderUseCase;
    private IOrderShippingUseCase orderShippingUseCase;
    public OrderShippingMenuView(ICustomerUseCase customerUseCase, IOrderUseCase orderUseCase, IOrderShippingUseCase orderShippingUseCase) {
        super(MENU_CODE, MENU_ORDER);
        this.customerUseCase = customerUseCase;
        this.orderUseCase = orderUseCase;
        this.orderShippingUseCase = orderShippingUseCase;
    }

    @Override
    public void render() {
        System.out.println(MENU_CODE + " - Enviar o pedido");
    }

    @Override
    public void selected() {
        TextInputView txtDocument = new TextInputView("Informe o documento do cliente:");
        txtDocument.render();

        Customer customer = customerUseCase.findByDocument(txtDocument.value());
        Order order = orderUseCase.findByCustomer(customer);
        if (order == null)
            throw new RuntimeException("Cliente não possui pedido ativo!");
        orderShippingUseCase.shipping(order);
    }

}
