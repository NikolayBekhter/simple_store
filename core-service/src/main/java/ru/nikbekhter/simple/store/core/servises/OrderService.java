package ru.nikbekhter.simple.store.core.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.core.api.*;
import ru.nikbekhter.simple.store.core.entities.Order;
import ru.nikbekhter.simple.store.core.entities.OrderItem;
import ru.nikbekhter.simple.store.core.entities.Product;
import ru.nikbekhter.simple.store.core.integrations.CartServiceIntegration;
import ru.nikbekhter.simple.store.core.integrations.UserServiceIntegration;
import ru.nikbekhter.simple.store.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService productService;
    private final OrderItemService orderItemService;
    private final OrderRepository orderRepository;
    private  final CartServiceIntegration cartServiceIntegration;
    private final UserServiceIntegration userServiceIntegration;
    private final PurchaseHistoryService historyService;

    @Transactional
    public Order createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
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

    @Transactional
    public void payment(String username, Long orderId) throws ResourceNotFoundException {
        Order order = orderRepository.findById(orderId).get();
        if (!order.isStatus()) {

            List<UserDto> listUserDto = new ArrayList<>();
            List<Product> listProduct = new ArrayList<>();
            List<PurchaseHistoryDto> historyDtoList = new ArrayList<>();

            for (OrderItem orderItem : order.getItems().stream().toList()) {
                UserDto userDto = new UserDto();
                PurchaseHistoryDto historyDto = new PurchaseHistoryDto();
                Product product = new Product();
                        product.setId(orderItem.getProduct().getId());
                        product.setQuantity(orderItem.getQuantity());
                userDto.setEmail(orderItem.getProduct().getOrganization().getOwner());
                userDto.setBalance(orderItem.getPrice());
                historyDto.setEmail(username);
                historyDto.setProductTitle(orderItem.getProduct().getTitle());
                historyDto.setOrganization(orderItem.getProduct().getOrganization().getTitle());
                historyDto.setQuantity(orderItem.getQuantity());
                listUserDto.add(userDto);
                listProduct.add(product);
                historyDtoList.add(historyDto);
            }

            for (Product product : listProduct) {
                productService.changeQuantity(product);
            }

            userServiceIntegration.payment(username, order.getTotalPrice());

            for (PurchaseHistoryDto historyDto : historyDtoList) {
                historyService.save(historyDto);
            }

            for (UserDto userDto : listUserDto) {
                userServiceIntegration.receivingProfit(userDto);
            }


            order.setStatus(true);
        }
        orderRepository.save(order);
    }
}
