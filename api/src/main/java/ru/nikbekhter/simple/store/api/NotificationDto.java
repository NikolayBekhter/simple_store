package ru.nikbekhter.simple.store.api;

public class NotificationDto {
    private Long id;
    private String title;
    private String createdAt;
    private String content;
    private String sendTo;

    public NotificationDto() {
    }

    public NotificationDto(Long id, String title, String createdAt, String content, String sendTo) {
        this.id = id;
        this.title = title;
        this.createdAt = createdAt;
        this.content = content;
        this.sendTo = sendTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }
}
