package br.com.mmmsieto.application.category.create;

import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.domain.category.CategoryGateway;
import br.com.mmmsieto.domain.validation.handler.Notification;
import br.com.mmmsieto.domain.validation.handler.ThrowsValidationHandler;

import java.util.Objects;

public class DefaultCreateCategoryUseCase extends CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultCreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand aCommand) {

        final var notification = Notification.create();

        final var aCategory = Category.newCategory(aCommand.name(), aCommand.description(), aCommand.isActive());
        aCategory.validate(notification);

        if (notification.hasError()) {

        }

        return CreateCategoryOutput.from(categoryGateway.create(aCategory));
    }
}
