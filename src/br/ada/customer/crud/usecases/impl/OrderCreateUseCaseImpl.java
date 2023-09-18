package br.ada.customer.crud.usecases.impl;

import br.ada.customer.crud.model.Customer;
import br.ada.customer.crud.model.Order;
import br.ada.customer.crud.model.OrderStatus;
import br.ada.customer.crud.usecases.IOrderUseCase;
import br.ada.customer.crud.usecases.repository.CustomerRepository;
import br.ada.customer.crud.usecases.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderCreateUseCaseImpl implements IOrderUseCase {

    private OrderRepository repository;
    private CustomerRepository customerRepository;

    public OrderCreateUseCaseImpl(OrderRepository repository, CustomerRepository customerRepository) {
        this.repository = repository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Order create(Customer customer) {
        validCustomer(customer);
        Order order = new Order();
        order.setCustomer(customer);
        order.setItems(new ArrayList<>());
        order.setStatus(OrderStatus.OPEN);
        order.setShippingAddress("Minha casa sempre");
        order.setOrderedAt(LocalDateTime.now());
        repository.save(order);
        return order;
    }

    /* Considerando que só há um pedido ativo por cliente */
    @Override
    public Order findByCustomer(Customer customer) {
        Order order = null;
        List<Order> list;
        if(customer != null) {
            list = repository.findByCustomer(customer).stream().filter(o -> o.getStatus() != OrderStatus.FINISH).toList();
            if (!list.isEmpty())
                order = list.get(0);
        }
        return order;
    }


    private void validCustomer(Customer customer) {
        Customer found = customerRepository.findByDocument(customer.getDocument());
        if (found == null) {
            throw new IllegalStateException("Cliente não encontrado");
        }
    }

}
