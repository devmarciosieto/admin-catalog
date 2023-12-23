package br.com.mmmsieto.application.category.update;

import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.domain.category.CategoryID;

public record UpdateCategoryOutput(
        CategoryID id
) {

    public static UpdateCategoryOutput fron(
            final Category aCategory
            ) {
        return new UpdateCategoryOutput(
                aCategory.getId()
        );
    }
}
