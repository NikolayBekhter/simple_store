package ru.nikbekhter.simple.store.core.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.core.api.CartDto;
import ru.nikbekhter.simple.store.core.entities.Order;
import ru.nikbekhter.simple.store.core.entities.OrderItem;
import ru.nikbekhter.simple.store.core.integrations.CartServiceIntegration;
import ru.nikbekhter.simple.store.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private  final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public Order createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        System.out.println("createOrder--------" + cartDto);
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        orderRepository.save(order);

        List<OrderItem> orderItems = cartDto.getItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(productService.findProductById(cartItem.getProductId()));
                    orderItem.setOrder(order);
                    orderItem.setPrice(cartItem.getPrice());
                    orderItem.setPricePerProduct(cartItem.getPricePerProduct());
                    orderItem.setQuantity(cartItem.getQuantity());
                    return orderItemService.save(orderItem);
                }).toList();
        return order;
    }

    public List<Order> getOrder(String username) {
        return orderRepository.findAllByUsername(username);
    }

    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
