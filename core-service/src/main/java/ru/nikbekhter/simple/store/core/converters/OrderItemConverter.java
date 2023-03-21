package ru.nikbekhter.simple.store.core.converters;

import org.springframework.stereotype.Component;
import ru.nikbekhter.simple.store.core.api.OrderItemDto;
import ru.nikbekhter.simple.store.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        orderItemDto.setOrderId(orderItem.getOrder().getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setPrice(orderItem.getPrice());
        return orderItemDto;
    }
}
