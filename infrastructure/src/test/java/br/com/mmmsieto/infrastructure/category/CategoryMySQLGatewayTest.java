package br.com.mmmsieto.infrastructure.category;

import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.infrastructure.MySQLGatewayTest;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@MySQLGatewayTest
class CategoryMySQLGatewayTest {

    @Autowired
    private CategoryMySQLGateway categoryMySQLGateway;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void test() {
        assertNotNull(categoryMySQLGateway);
        assertNotNull(categoryRepository);
    }

    @Test
    void givenValidCategory_whenCallsCreate_shouldReturnANewCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "Filmes em geral";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertEquals(0, categoryRepository.count());

        final var createdCategory = categoryMySQLGateway.create(aCategory);

        assertEquals(1, categoryRepository.count());

        assertEquals(aCategory.getId(), createdCategory.getId());
        assertEquals(expectedName, createdCategory.getName());
        assertEquals(expectedDescription, createdCategory.getDescription());
        assertEquals(expectedIsActive, createdCategory.isActive());
        assertEquals(aCategory.getCreatedAt(), createdCategory.getCreatedAt());
        assertEquals(aCategory.getUpdatedAt(), createdCategory.getUpdatedAt());
        assertEquals(aCategory.getDeletedAt(), createdCategory.getDeletedAt());
        assertNull(createdCategory.getDeletedAt());

        final var actualEntity = categoryRepository.findById(createdCategory.getId().getValue()).orElseThrow();

        assertEquals(aCategory.getId().getValue(), actualEntity.getId());
        assertEquals(expectedName, actualEntity.getName());
        assertEquals(expectedDescription, actualEntity.getDescription());
        assertEquals(expectedIsActive, actualEntity.isActive());
        assertEquals(aCategory.getCreatedAt(), actualEntity.getCreatedAt());
        assertEquals(aCategory.getUpdatedAt(), actualEntity.getUpdatedAt());
        assertEquals(aCategory.getDeletedAt(), actualEntity.getDeletedAt());
        assertNull(actualEntity.getDeletedAt());
    }


}