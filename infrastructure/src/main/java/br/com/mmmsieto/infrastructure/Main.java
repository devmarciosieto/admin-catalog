package br.com.mmmsieto.infrastructure;

import br.com.mmmsieto.application.category.create.CreateCategoryUseCase;
import br.com.mmmsieto.application.category.delete.DeleteCategoryUseCase;
import br.com.mmmsieto.application.category.retrieve.get.GetCategoryByIdUseCase;
import br.com.mmmsieto.application.category.retrieve.list.ListCategoriesUseCase;
import br.com.mmmsieto.application.category.update.UpdateCategoryUseCase;
import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryJpaEntity;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.mmmsieto.infrastructure.configuration.WebServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.sql.init.dependency.DependsOnDatabaseInitialization;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.AbstractEnvironment;

import java.util.List;


@SpringBootApplication
public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        LOG.info("[step:to-be-init] [id:1] Inicializando o Spring");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "development");
        SpringApplication.run(WebServerConfig.class, args);
        LOG.info("[step:inittialized] [id:2] Spring inicializado..");
    }

    // TODO: for development check only
//    @Bean
//    public ApplicationRunner applicationRunner(CategoryRepository categoryRepository) {
//        return args -> {
//
//            List<CategoryJpaEntity> categories = categoryRepository.findAll();
//
//            Category category = Category.newCategory("Filmes", "Description", true);
//
//            categoryRepository.saveAndFlush(CategoryJpaEntity.from(category));
//
//            categoryRepository.deleteAll();
//
//        };
//    }

    // TODO: for development check only
//    @Bean
//    @DependsOnDatabaseInitialization
//    ApplicationRunner runner(
//            @Autowired CreateCategoryUseCase createCategoryUseCase,
//            @Autowired UpdateCategoryUseCase updateCategoryUseCase,
//            @Autowired DeleteCategoryUseCase deleteCategoryUseCase,
//            @Autowired ListCategoriesUseCase listCategoriesUseCase,
//            @Autowired GetCategoryByIdUseCase getCategoryByIdUseCase
//            ) {
//        return args -> {};
//    }

}