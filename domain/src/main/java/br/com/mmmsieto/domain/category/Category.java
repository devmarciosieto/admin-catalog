package br.com.mmmsieto.domain.category;

import br.com.mmmsieto.domain.AggregateRoot;
import br.com.mmmsieto.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.UUID;


public class Category extends AggregateRoot<CategoryID> implements Cloneable {
    private String name;
    private String description;
    private Boolean active;
    private Instant createdAt;
    private Instant updateAt;
    private Instant deletedAt;


    private Category(
            final CategoryID anId,
            final String aName,
            final String aDescription,
            final Boolean isActive,
            final Instant aCreatedAt,
            final Instant aUpdateAt,
            final Instant aDeletedAt
    ) {
        super(anId);
        this.name = aName;
        this.description = aDescription;
        this.active = isActive;
        this.createdAt = aCreatedAt;
        this.updateAt = aUpdateAt;
        this.deletedAt = aDeletedAt;
    }

    public static Category newCategory(String name, String description, Boolean active) {
        final var id = CategoryID.unique();
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

    public CategoryID getId() {
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

    @Override
    public void validate(final ValidationHandler handler) {
        new CategoryValidator(this, handler).validate();
    }


}
