package ru.nikbekhter.simple.store.auth.api;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private String recipient;
    private String message;
}
