package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.model.Product;
import br.ada.customer.crud.usecases.IOrderItemUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;

public class OrderItemUseCaseImpl implements IOrderItemUseCase {

    private OrderRepository repository;

    public OrderItemUseCaseImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderItem addItem(Order order, Product product, BigDecimal price, Integer amount) {
        validOrderStatus(order);
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(product);
        orderItem.setSaleValue(price);
        orderItem.setAmount(amount);
        order.getItems().add((orderItem));
        this.repository.save(order);
        return orderItem;
    }

    @Override
    public OrderItem changeAmount(Order order, Product product, Integer amount) {
        validOrderStatus(order);
        for (OrderItem item : order.getItems()){
            if (item.getProduct().compareTo(product) == 0){
                item.setAmount(amount);
                repository.save(order);
                return item;
            }
        }
        return null;
    }

    @Override
    public void removeItem(Order order, Product product) {
        validOrderStatus(order);
        order.getItems().removeIf(item -> item.getProduct().compareTo(product) == 0);
        repository.save(order);
    }

    private void validOrderStatus(Order order){
        if (order.getStatus() != OrderStatus.OPEN){
            throw new RuntimeException("Pedido não está aberto, não é possível alterar o item!");
        }
    }
}
