package br.com.mmmsieto.infrastructure.category;

import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.domain.category.CategoryGateway;
import br.com.mmmsieto.domain.category.CategoryID;
import br.com.mmmsieto.domain.pagination.Pagination;
import br.com.mmmsieto.domain.pagination.SearchQuery;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryJpaEntity;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryMySQLGateway implements CategoryGateway {

    private final CategoryRepository repository;

    public CategoryMySQLGateway(final CategoryRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }


    @Override
    public Category create(Category aCategory) {
        return this.repository.save(CategoryJpaEntity.from(aCategory)).toAggregate();
    }

    @Override
    public void deleteById(CategoryID anId) {

    }

    @Override
    public Optional<Category> findById(CategoryID anId) {
        return Optional.empty();
    }

    @Override
    public Category update(Category aCategory) {
        return null;
    }

    @Override
    public Pagination<Category> findAll(SearchQuery aQuery) {
        return null;
    }
}
