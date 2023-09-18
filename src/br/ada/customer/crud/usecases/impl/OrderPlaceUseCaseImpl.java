package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderItem;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierOrderPlaceUseCase;
import br.ada.customer.crud.usecases.IOrderPlaceUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrderPlaceUseCaseImpl implements IOrderPlaceUseCase {

    OrderRepository repository;

    INotifierOrderPlaceUseCase notifier;

    public OrderPlaceUseCaseImpl(OrderRepository repository, INotifierOrderPlaceUseCase notifier) {
        this.repository = repository;
        this.notifier = notifier;
    }

    @Override
    public void placeOrder(Order order) {
        validOrderStatus(order);
        validOrderItemList(order.getItems());
        order.setStatus(OrderStatus.PENDING_PAYMENT);
        repository.save(order);
        notifier.notify(order);
    }

    private void validOrderStatus(Order order){
        if (order.getStatus() != OrderStatus.OPEN){
            throw new RuntimeException("Pedido não está aberto!");
        }
    }

    private void validOrderItemList (List<OrderItem> list){
        if (list == null || list.isEmpty())
            throw new RuntimeException();

        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItem item : list){
            sum = sum.add(item.getSaleValue());
        }

        if (sum.compareTo(BigDecimal.ZERO) == 0){
            throw  new RuntimeException();
        }
    }
}
