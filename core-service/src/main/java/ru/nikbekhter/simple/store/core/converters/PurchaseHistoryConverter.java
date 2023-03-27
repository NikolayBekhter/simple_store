package ru.nikbekhter.simple.store.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nikbekhter.simple.store.core.api.OrderDto;
import ru.nikbekhter.simple.store.core.api.PurchaseHistoryDto;
import ru.nikbekhter.simple.store.core.entities.Order;
import ru.nikbekhter.simple.store.core.entities.PurchaseHistory;

import java.time.format.*;

@Component
@RequiredArgsConstructor
public class PurchaseHistoryConverter {
    public PurchaseHistoryDto entityToDto(PurchaseHistory history) {
        PurchaseHistoryDto historyDto = new PurchaseHistoryDto();
        historyDto.setId(history.getId());
        historyDto.setEmail(history.getEmail());
        historyDto.setProductTitle(history.getProductTitle());
        historyDto.setOrganization(history.getOrganization());
        historyDto.setQuantity(history.getQuantity());
        historyDto.setDatePurchase(history.getDatePurchase().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        return historyDto;
    }

    public PurchaseHistory dtoToEntity(PurchaseHistoryDto historyDto) {
        PurchaseHistory history = new PurchaseHistory();
        history.setEmail(historyDto.getEmail());
        history.setProductTitle(historyDto.getProductTitle());
        history.setOrganization(historyDto.getOrganization());
        history.setQuantity(historyDto.getQuantity());
        return history;
    }
}
