package br.ada.customer.crud.view.order;

import br.ada.customer.crud.usecases.*;
import br.ada.customer.crud.view.component.View;
import br.ada.customer.crud.view.component.menu.AbstractMenuOptionView;
import br.ada.customer.crud.view.component.menu.MenuOptionView;
import br.ada.customer.crud.view.component.menu.impl.GoBackOptionView;
import br.ada.customer.crud.view.component.menu.impl.SubMenuView;

import java.util.Arrays;

public class OrderMenuView extends AbstractMenuOptionView implements MenuOptionView {

    public static final String MENU_CODE = "3";
    public static final Integer MENU_ORDER = 3;

    private IProductUseCase productUseCase;
    private ICustomerUseCase customerUseCase;
    private IOrderUseCase orderUseCase;
    private IOrderItemUseCase orderItemUseCase;
    private IOrderPlaceUseCase orderPlaceUseCase;
    private IOrderPayUseCase orderPayUseCase;
    private IOrderShippingUseCase orderShippingUseCase;
    private View comeFrom;

    public OrderMenuView(IProductUseCase productUseCase, ICustomerUseCase customerUseCase, IOrderUseCase orderUseCase, IOrderItemUseCase orderItemUseCase, IOrderPlaceUseCase orderPlaceUseCase, IOrderPayUseCase orderPayUseCase, IOrderShippingUseCase orderShippingUseCase, View comeFrom) {
        super(MENU_CODE, MENU_ORDER);
        this.productUseCase = productUseCase;
        this.customerUseCase = customerUseCase;
        this.orderUseCase = orderUseCase;
        this.orderItemUseCase = orderItemUseCase;
        this.orderPlaceUseCase = orderPlaceUseCase;
        this.orderPayUseCase = orderPayUseCase;
        this.orderShippingUseCase = orderShippingUseCase;
        this.comeFrom = comeFrom;
    }

    @Override
    public void render() {
        System.out.println(MENU_CODE + " - Pedido");
    }

    @Override
    public void selected() {
        SubMenuView subMenuView = new SubMenuView(
                Arrays.asList(
                        new OrderCreateView(orderUseCase, customerUseCase),
                        new OrderAddItemMenuView(customerUseCase, orderUseCase, orderItemUseCase, productUseCase),
                        new OrderPlaceMenuView(customerUseCase, orderUseCase, orderPlaceUseCase),
                        new OrderPayMenuView(customerUseCase, orderUseCase, orderPayUseCase),
                        new OrderShippingMenuView(customerUseCase, orderUseCase, orderShippingUseCase),
                        new GoBackOptionView(comeFrom)
                )
        );
        subMenuView.render();
    }

}
