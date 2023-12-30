package br.com.mmmsieto.application.category.retrieve.list;

import br.com.mmmsieto.domain.category.CategoryGateway;
import br.com.mmmsieto.domain.pagination.Pagination;
import br.com.mmmsieto.domain.pagination.SearchQuery;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultListCategoriesUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutput> execute(final SearchQuery aQuery) {
        return this.categoryGateway.findAll(aQuery)
                .map(CategoryListOutput::from);
    }

}
