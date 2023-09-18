package br.ada.customer.crud.factory;

import br.ada.customer.crud.integration.database.MemoryDatabase;
import br.ada.customer.crud.integration.email.OrderEmailNotifierImpl;
import br.ada.customer.crud.integration.email.OrderPlaceNotifierImpl;
import br.ada.customer.crud.integration.email.OrderShippingNotifierImpl;
import br.ada.customer.crud.integration.email.SendEmail;
import br.ada.customer.crud.integration.memoryrepository.OrderEntityMerge;
import br.ada.customer.crud.integration.memoryrepository.OrderMemoryRepositoryImpl;
import br.ada.customer.crud.usecases.*;
import br.ada.customer.crud.usecases.impl.*;
import br.ada.customer.crud.usecases.repository.OrderRepository;

public class OrderFactory {

    public static IOrderUseCase createUseCase() {
        return new OrderCreateUseCaseImpl(
                createRepository(),
                CustomerFactory.createRepository()
        );
    }

    public static IOrderItemUseCase orderItemUseCase() {
        return new OrderItemUseCaseImpl(
                createRepository()
        );
    }

    public static IOrderPlaceUseCase placeOrderUseCase() {
        return new OrderPlaceUseCaseImpl(
                createRepository(),
                createOrderPlaceNotifier()
        );
    }

    public static IOrderPayUseCase payOrderUseCase() {

        return new OrderPayUseCaseImp(
                createRepository(),
                createPaidNotifier()
        );
    }

    public static IOrderShippingUseCase shippingUseCase() {
        return new OrderShippingUseCaseImpl(
                createRepository(),
                createShippingNotifier()
        );
    }

    public static OrderRepository createRepository() {
        return new OrderMemoryRepositoryImpl(
                MemoryDatabase.getInstance(),
                new OrderEntityMerge(MemoryDatabase.getInstance())
        );
    }

    public static INotifierPaidOrderUseCase createPaidNotifier() {
        return new OrderEmailNotifierImpl(new SendEmail());
    }

    public static IShippingNotifierUseCase createShippingNotifier() {
        return new OrderShippingNotifierImpl(new SendEmail());
    }

    public static INotifierOrderPlaceUseCase createOrderPlaceNotifier(){
        return new OrderPlaceNotifierImpl(new SendEmail());
    }
}
