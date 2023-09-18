package br.ada.customer.crud.view.order;

import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.ICustomerUseCase;
import br.ada.customer.crud.usecases.IOrderUseCase;
import br.ada.customer.crud.view.component.menu.AbstractMenuOptionView;
import br.ada.customer.crud.view.component.menu.MenuOptionView;
import br.ada.customer.crud.view.component.text.impl.TextInputView;

public class OrderCreateView extends AbstractMenuOptionView implements MenuOptionView {

    public static final String MENU_CODE = "1";
    public static final Integer MENU_ORDER = 1;

    private IOrderUseCase useCase;

    private ICustomerUseCase customerUseCase;

    public OrderCreateView(IOrderUseCase useCase, ICustomerUseCase customerUseCase) {
        super(MENU_CODE, MENU_ORDER);
        this.useCase = useCase;
        this.customerUseCase = customerUseCase;
    }

    @Override
    public void render() {
        System.out.println(MENU_CODE + " - Criar um novo pedido");
    }

    @Override
    public void selected() {
        TextInputView txtDocument = new TextInputView("Informe o documento do cliente:");
        txtDocument.render();

        Customer customer = customerUseCase.findByDocument(txtDocument.value());
        if (customer == null)
            throw new RuntimeException("Não existe cliente com o documento informado");
        Order order = useCase.create(customer);
        System.out.println("Pedido " + order.getId() + " criado com sucesso!");
    }

}
