package br.com.mmmsieto.application.category.update;

import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.domain.category.CategoryGateway;
import br.com.mmmsieto.domain.category.CategoryID;
import br.com.mmmsieto.domain.exceptions.DomainException;
import br.com.mmmsieto.domain.exceptions.NotFoundException;
import br.com.mmmsieto.domain.validation.Error;
import br.com.mmmsieto.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class DefaultUpdateCategoryUseCase extends UpdateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultUpdateCategoryUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Either<Notification, UpdateCategoryOutput> execute(final UpdateCategoryCommand aCommand) {
        final var anId = CategoryID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aDescription = aCommand.description();
        final var isActive = aCommand.isActive();

        final var aCategory = categoryGateway.findById(anId)
                .orElseThrow(notFound(anId));

        final var notification = Notification.create();
        aCategory.update(aName, aDescription, isActive)
                .validate(notification);

        return notification.hasError() ? Left(notification) : update(aCategory);
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () -> NotFoundException.with(new Error("Category with ID %s not found".formatted(anId.getValue())));
    }

private Either<Notification, UpdateCategoryOutput> update(final Category aCategory) {
        return Try(() -> categoryGateway.update(aCategory))
                .toEither()
                .bimap(Notification::create, UpdateCategoryOutput::fron);
    }

}
