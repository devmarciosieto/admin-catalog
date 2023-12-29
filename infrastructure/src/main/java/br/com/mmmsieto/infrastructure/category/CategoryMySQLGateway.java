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
    public Category create(final Category aCategory) {
        return save(aCategory);
    }

    @Override
    public void deleteById(final CategoryID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<Category> findById(final CategoryID anId) {
        return this.repository.findById(anId.getValue())
                .map(CategoryJpaEntity::toAggregate);
    }

    @Override
    public Category update(final Category aCategory) {
        return save(aCategory);
    }

    @Override
    public Pagination<Category> findAll(SearchQuery aQuery) {
        return null;
    }

    private Category save(final Category aCategory) {
        return this.repository.save(CategoryJpaEntity.from(aCategory)).toAggregate();
    }
}
