package ru.nikbekhter.simple.store.core.api;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseHistoryDto {
    private Long id;
    private String email;
    private String productTitle;
    private String organization;
    private int quantity;
    private String datePurchase;
}
