package br.com.mmmsieto.infrastructure;

import br.com.mmmsieto.infrastructure.configuration.WebServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;


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