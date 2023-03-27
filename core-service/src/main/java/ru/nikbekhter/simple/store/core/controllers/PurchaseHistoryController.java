package ru.nikbekhter.simple.store.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.nikbekhter.simple.store.core.api.PurchaseHistoryDto;
import ru.nikbekhter.simple.store.core.converters.PurchaseHistoryConverter;
import ru.nikbekhter.simple.store.core.entities.PurchaseHistory;
import ru.nikbekhter.simple.store.core.servises.PurchaseHistoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/history")
public class PurchaseHistoryController {
    private final PurchaseHistoryService historyService;
    private final PurchaseHistoryConverter historyConverter;

    @GetMapping("/all")
    public List<PurchaseHistoryDto> findAll() {
        return historyService.findAll()
                .stream()
                .map(historyConverter::entityToDto)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<PurchaseHistoryDto> findAllByEmail(@RequestHeader String username) {
        return historyService.findAllByEmail(username)
                .stream()
                .map(historyConverter::entityToDto)
                .collect(Collectors.toList());
    }
}
