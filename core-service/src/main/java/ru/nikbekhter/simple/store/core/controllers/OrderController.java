package ru.nikbekhter.simple.store.core.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikbekhter.simple.store.api.OrderDto;
import ru.nikbekhter.simple.store.api.ResourceNotFoundException;
import ru.nikbekhter.simple.store.core.converters.OrderConverter;
import ru.nikbekhter.simple.store.core.servises.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
@Log4j2
public class OrderController {
    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username) {
        System.out.println(username + " оформил заказ!");
        orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> getOrders(@RequestHeader String username) {
        log.info(orderService.getOrder(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList()));
        return orderService.getOrder(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
    }

    @GetMapping("/payment/{orderId}")
    public void payment(@RequestHeader String username, @PathVariable Long orderId) throws ResourceNotFoundException {
        orderService.payment(username, orderId);
    }

    @GetMapping("/is_refund/{orderId}")
    public boolean isRefundOrder(@PathVariable Long orderId) {
        return orderService.isRefundOrder(orderId);
    }

    @GetMapping("/refund/{orderId}")
    public void refundOrder(@RequestHeader String username, @PathVariable Long orderId) {
        orderService.refundPayment(username, orderId);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    private ResponseEntity<String> handleNotFound(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
