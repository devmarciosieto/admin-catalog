package br.com.mmmsieto.application.category.retrieve.get;

import br.com.mmmsieto.domain.category.CategoryGateway;
import br.com.mmmsieto.domain.category.CategoryID;
import br.com.mmmsieto.domain.exceptions.DomainException;
import br.com.mmmsieto.domain.exceptions.NotFoundException;
import br.com.mmmsieto.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(final String anIn) {
        final var anCategoryID = CategoryID.from(anIn);
        return this.categoryGateway.findById(anCategoryID)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(anCategoryID));
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(new Error("Category with ID %s not found".formatted(anId.getValue())));
    }

}
