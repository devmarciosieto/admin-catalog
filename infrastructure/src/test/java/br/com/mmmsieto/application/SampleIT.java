package br.com.mmmsieto.application;

import br.com.mmmsieto.IntegrationTest;
import br.com.mmmsieto.application.category.create.CreateCategoryUseCase;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@IntegrationTest
public class SampleIT {

    @Autowired
    private CreateCategoryUseCase useCase;

    @Autowired
    private CategoryRepository repository;

    @Test
    void testInjects() {
        assertNotNull(useCase);
        assertNotNull(repository);
    }

}
