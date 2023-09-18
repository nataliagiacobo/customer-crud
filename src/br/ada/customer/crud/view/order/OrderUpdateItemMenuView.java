package br.ada.customer.crud.view.order;

import br.ada.customer.crud.view.component.menu.AbstractMenuOptionView;
import br.ada.customer.crud.view.component.menu.MenuOptionView;

public class OrderUpdateItemMenuView extends AbstractMenuOptionView implements MenuOptionView {

    public OrderUpdateItemMenuView() {
        super("4", 4);
    }

    @Override
    public void render() {
        System.out.println("4 - Alterar quantidade de um item");
    }

    @Override
    public void selected() {
        System.out.println("Foi selecionado a opção de alterar quantidade de um item do pedido");
    }

}
