package br.com.mmmsieto.domain.validation.handler;

import br.com.mmmsieto.domain.exceptions.DomainException;
import br.com.mmmsieto.domain.validation.Error;
import br.com.mmmsieto.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationHandler implements ValidationHandler {
    @Override
    public ValidationHandler append(Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public <T> T validate(final Validation<T> aValidation) {
        try {
            return aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }

    @Override
    public boolean hasError() {
        return ValidationHandler.super.hasError();
    }

    @Override
    public Error firstError() {
        return ValidationHandler.super.firstError();
    }
}
