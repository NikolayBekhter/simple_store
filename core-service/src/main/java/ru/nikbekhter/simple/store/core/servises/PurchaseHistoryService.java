package ru.nikbekhter.simple.store.core.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikbekhter.simple.store.core.api.PurchaseHistoryDto;
import ru.nikbekhter.simple.store.core.converters.PurchaseHistoryConverter;
import ru.nikbekhter.simple.store.core.entities.PurchaseHistory;
import ru.nikbekhter.simple.store.core.repositories.PurchaseHistoryRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {
    private final PurchaseHistoryRepository repository;
    private final PurchaseHistoryConverter historyConverter;

    public PurchaseHistory save(PurchaseHistoryDto historyDto) {
        return repository.save(historyConverter.dtoToEntity(historyDto));
    }

    public List<PurchaseHistory> findAll() {
        return repository.findAll();
    }

    public List<PurchaseHistory> findAllByEmail(String email) {
        return repository.findAllByEmailIgnoreCase(email);
    }
}
