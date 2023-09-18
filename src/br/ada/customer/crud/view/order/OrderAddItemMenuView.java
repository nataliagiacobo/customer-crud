package br.ada.customer.crud.view.order;

import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.usecases.*;
import br.ada.customer.crud.view.component.convert.impl.BigDecimalTextConverter;
import br.ada.customer.crud.view.component.convert.impl.IntegerTextConverter;
import br.ada.customer.crud.view.component.menu.AbstractMenuOptionView;
import br.ada.customer.crud.view.component.menu.MenuOptionView;
import br.ada.customer.crud.view.component.text.impl.ObjectInputView;
import br.ada.customer.crud.view.component.text.impl.TextInputView;

import java.math.BigDecimal;

public class OrderAddItemMenuView extends AbstractMenuOptionView implements MenuOptionView {

    public static final String MENU_CODE = "2";
    public static final Integer MENU_ORDER = 2;
    private ICustomerUseCase customerUseCase;
    private IOrderUseCase orderUseCase;
    private IOrderItemUseCase orderItemUseCase;
    private IProductUseCase productUseCase;

    public OrderAddItemMenuView(ICustomerUseCase customerUseCase, IOrderUseCase orderUseCase, IOrderItemUseCase orderItemUseCase, IProductUseCase productUseCase) {
        super(MENU_CODE, MENU_ORDER);
        this.customerUseCase = customerUseCase;
        this.orderUseCase = orderUseCase;
        this.orderItemUseCase = orderItemUseCase;
        this.productUseCase = productUseCase;
    }

    @Override
    public void render() {
        System.out.println(MENU_CODE + " - Adicionar item");
    }

    @Override
    public void selected() {
        TextInputView txtDocument = new TextInputView("Informe o documento do cliente:");
        txtDocument.render();

        Customer customer = customerUseCase.findByDocument(txtDocument.value());
        Order order = orderUseCase.findByCustomer(customer);
        if (order == null)
            throw new RuntimeException("Cliente não possui pedido ativo!");

        TextInputView txtCodProduto = new TextInputView("Informe o código do produto: ");
        txtCodProduto.render();

        ObjectInputView<Integer> txtAmount = new ObjectInputView<>("Informe a quantidade: ", new IntegerTextConverter());
        txtAmount.render();

        ObjectInputView<BigDecimal> txtPrice = new ObjectInputView<>("Informe o preço:", new BigDecimalTextConverter());
        txtPrice.render();
        orderItemUseCase.addItem(order, productUseCase.findByBarcode(txtCodProduto.value()), txtPrice.value(), txtAmount.value());
    }

}
