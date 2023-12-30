package br.com.mmmsieto.application.category.update;

import br.com.mmmsieto.application.UseCase;
import br.com.mmmsieto.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase
        extends UseCase<UpdateCategoryCommand, Either<Notification, UpdateCategoryOutput>> {
}
