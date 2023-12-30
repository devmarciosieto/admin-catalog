package br.com.mmmsieto.application.category.create;

import br.com.mmmsieto.application.UseCase;
import br.com.mmmsieto.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase
        extends UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {


}
