package ru.nikbekhter.simple.store.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nikbekhter.simple.store.api.OrderDto;
import ru.nikbekhter.simple.store.core.entities.Order;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;
    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUsername(order.getUsername());
        orderDto.setItems(order.getItems().stream()
                .map(orderItemConverter::entityToDto)
                .collect(Collectors.toList()));
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setStatus(order.isStatus());
        orderDto.setCreatedAt(order.getCreatedAt().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        return orderDto;
    }
}
