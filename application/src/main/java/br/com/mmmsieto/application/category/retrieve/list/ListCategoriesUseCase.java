package br.com.mmmsieto.application.category.retrieve.list;

import br.com.mmmsieto.application.UseCase;
import br.com.mmmsieto.domain.category.CategorySearchQuery;
import br.com.mmmsieto.domain.pagination.Pagination;
import br.com.mmmsieto.domain.pagination.SearchQuery;

public abstract class ListCategoriesUseCase
        extends UseCase<SearchQuery, Pagination<CategoryListOutput>> {
}
