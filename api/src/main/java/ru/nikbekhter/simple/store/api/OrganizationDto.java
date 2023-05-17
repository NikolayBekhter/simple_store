package ru.nikbekhter.simple.store.api;

public class OrganizationDto {
    private Long id;
    private String title;
    private String description;
    private String owner;
    private boolean isActive;

    public OrganizationDto() {
    }

    public OrganizationDto(Long id, String title, String description, String owner, boolean isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.isActive = isActive;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
