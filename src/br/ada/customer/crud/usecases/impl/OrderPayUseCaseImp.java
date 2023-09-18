package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.INotifierPaidOrderUseCase;
import br.ada.customer.crud.usecases.IOrderPayUseCase;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderPayUseCaseImp implements IOrderPayUseCase {

    private OrderRepository orderRepository;

    private INotifierPaidOrderUseCase notifier;

    public OrderPayUseCaseImp(OrderRepository orderRepository, INotifierPaidOrderUseCase notifier) {
        this.orderRepository = orderRepository;
        this.notifier = notifier;
    }

    @Override
    public void pay(Order order) {
        if (order.getStatus() != OrderStatus.PENDING_PAYMENT){
            throw new RuntimeException("Pedido não está apto para pagamento!");
        }
        order.setStatus(OrderStatus.PAID);
        orderRepository.update(order);
        notifier.notify(order);
    }
}
