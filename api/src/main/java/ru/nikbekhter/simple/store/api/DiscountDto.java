package ru.nikbekhter.simple.store.api;

import java.time.LocalDateTime;

public class DiscountDto {
    private Long id;
    private int dis;
    private LocalDateTime startDate;
    private LocalDateTime expiryDate;

    public DiscountDto() {
    }

    public DiscountDto(Long id, int dis, LocalDateTime startDate, LocalDateTime expiryDate) {
        this.id = id;
        this.dis = dis;
        this.startDate = startDate;
        this.expiryDate = expiryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDis() {
        return dis;
    }

    public void setDis(int dis) {
        this.dis = dis;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
