package br.com.mmmsieto.application.category.create;

import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.domain.category.CategoryID;

public record CreateCategoryOutput(
        CategoryID id
) {

    public static CreateCategoryOutput from(final Category category) {
        return new CreateCategoryOutput(category.getId());
    }
}
