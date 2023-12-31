package br.com.mmmsieto.infrastructure.configuration.usecases;

import br.com.mmmsieto.application.category.create.CreateCategoryUseCase;
import br.com.mmmsieto.application.category.create.DefaultCreateCategoryUseCase;
import br.com.mmmsieto.application.category.delete.DefaultDeleteCategoryUseCase;
import br.com.mmmsieto.application.category.delete.DeleteCategoryUseCase;
import br.com.mmmsieto.application.category.retrieve.get.DefaultGetCategoryByIdUseCase;
import br.com.mmmsieto.application.category.retrieve.get.GetCategoryByIdUseCase;
import br.com.mmmsieto.application.category.retrieve.list.DefaultListCategoriesUseCase;
import br.com.mmmsieto.application.category.retrieve.list.ListCategoriesUseCase;
import br.com.mmmsieto.application.category.update.DefaultUpdateCategoryUseCase;
import br.com.mmmsieto.application.category.update.UpdateCategoryUseCase;
import br.com.mmmsieto.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CategoryUseCaseConfig {

    private final CategoryGateway categoryGateway;

    public CategoryUseCaseConfig(CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase() {
        return new DefaultCreateCategoryUseCase(categoryGateway);
    }

    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase() {
        return new DefaultUpdateCategoryUseCase(categoryGateway);
    }

    @Bean
    public GetCategoryByIdUseCase getCategoryByIdUseCase() {
        return new DefaultGetCategoryByIdUseCase(categoryGateway);
    }

    @Bean
    public ListCategoriesUseCase listCategoriesUseCase() {
        return new DefaultListCategoriesUseCase(categoryGateway);
    }

    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase() {
        return new DefaultDeleteCategoryUseCase(categoryGateway);
    }

}
