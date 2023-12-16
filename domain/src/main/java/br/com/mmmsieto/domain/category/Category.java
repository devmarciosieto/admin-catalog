package br.com.mmmsieto.domain.category;

import java.time.Instant;
import java.util.UUID;

public class Category {
    private String id;
    private String name;
    private String description;
    private Boolean active;
    private Instant createdAt;
    private Instant updateAt;
    private Instant deletedAt;


    private Category(
            final String id,
            final String name,
            final String description,
            final Boolean active,
            final Instant createdAt,
            final Instant updateAt,
            final Instant deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.active = active;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deletedAt = deletedAt;
    }

    public static Category newCategory(String name, String description, Boolean active) {
        final var id = UUID.randomUUID().toString();
        return new Category(
                id,
                name,
                description,
                active,
                Instant.now(),
                Instant.now(),
                null
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsActive() {
        return active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }


}
