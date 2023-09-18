package br.ada.customer.crud.view.order;

import br.ada.customer.crud.view.component.menu.AbstractMenuOptionView;
import br.ada.customer.crud.view.component.menu.MenuOptionView;

public class OrderRemoveItemMenuView extends AbstractMenuOptionView implements MenuOptionView {
    public OrderRemoveItemMenuView() {
        super("3", 3);
    }

    @Override
    public void render() { System.out.println("3 - Remover item"); }

    @Override
    public void selected() { System.out.println("Foi selecionado a opção de remover um item do pedido"); }

}
