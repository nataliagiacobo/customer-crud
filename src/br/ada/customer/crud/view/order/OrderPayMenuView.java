package br.ada.customer.crud.view.order;

import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.ICustomerUseCase;
import br.ada.customer.crud.usecases.IOrderPayUseCase;
import br.ada.customer.crud.usecases.IOrderUseCase;
import br.ada.customer.crud.view.component.menu.AbstractMenuOptionView;
import br.ada.customer.crud.view.component.menu.MenuOptionView;
import br.ada.customer.crud.view.component.text.impl.TextInputView;

public class OrderPayMenuView extends AbstractMenuOptionView implements MenuOptionView {

    public static final String MENU_CODE = "4";
    public static final Integer MENU_ORDER = 4;

    private ICustomerUseCase customerUseCase;
    private IOrderUseCase orderUseCase;
    private IOrderPayUseCase orderPayUseCase;
    public OrderPayMenuView(ICustomerUseCase customerUseCase, IOrderUseCase orderUseCase, IOrderPayUseCase orderPayUseCase) {
        super(MENU_CODE, MENU_ORDER);
        this.customerUseCase = customerUseCase;
        this.orderUseCase = orderUseCase;
        this.orderPayUseCase = orderPayUseCase;
    }

    @Override
    public void render() {
        System.out.println(MENU_CODE + " - Realizar o pagamento do pedido");
    }

    @Override
    public void selected() {
        TextInputView txtDocument = new TextInputView("Informe o documento do cliente:");
        txtDocument.render();

        Customer customer = customerUseCase.findByDocument(txtDocument.value());
        Order order = orderUseCase.findByCustomer(customer);
        if (order == null)
            throw new RuntimeException("Cliente não possui pedido ativo!");
        orderPayUseCase.pay(order);
    }
}
