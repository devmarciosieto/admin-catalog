package br.com.mmmsieto.infrastructure.category;

import br.com.mmmsieto.MySQLGatewayTest;
import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.domain.category.CategoryID;
import br.com.mmmsieto.domain.pagination.SearchQuery;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryJpaEntity;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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


    @Test
    public void givenAValidCategory_whenCallsUpdate_shouldReturnCategoryUpdated() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory("Film", null, expectedIsActive);

        assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        assertEquals(1, categoryRepository.count());

        final var actualInvalidEntity = categoryRepository.findById(aCategory.getId().getValue()).get();

        assertEquals("Film", actualInvalidEntity.getName());
        assertNull(actualInvalidEntity.getDescription());
        assertEquals(expectedIsActive, actualInvalidEntity.isActive());

        final var aUpdatedCategory = aCategory.clone()
                .update(expectedName, expectedDescription, expectedIsActive);

        final var actualCategory = categoryMySQLGateway.update(aUpdatedCategory);

        assertEquals(1, categoryRepository.count());

        assertEquals(aCategory.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        assertTrue(aCategory.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
        assertEquals(aCategory.getDeletedAt(), actualCategory.getDeletedAt());
        assertNull(actualCategory.getDeletedAt());

        final var actualEntity = categoryRepository.findById(aCategory.getId().getValue()).get();

        assertEquals(aCategory.getId().getValue(), actualEntity.getId());
        assertEquals(expectedName, actualEntity.getName());
        assertEquals(expectedDescription, actualEntity.getDescription());
        assertEquals(expectedIsActive, actualEntity.isActive());
        assertEquals(aCategory.getCreatedAt(), actualEntity.getCreatedAt());
        assertTrue(aCategory.getUpdatedAt().isBefore(actualCategory.getUpdatedAt()));
        assertEquals(aCategory.getDeletedAt(), actualEntity.getDeletedAt());
        assertNull(actualEntity.getDeletedAt());
    }

    @Test
    public void givenAPrePersistedCategoryAndValidCategoryId_whenTryToDeleteIt_shouldDeleteCategory() {
        final var aCategory = Category.newCategory("Filmes", null, true);

        assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        assertEquals(1, categoryRepository.count());

        categoryMySQLGateway.deleteById(aCategory.getId());

        assertEquals(0, categoryRepository.count());
    }

    @Test
    public void givenInvalidCategoryId_whenTryToDeleteIt_shouldDeleteCategory() {
        assertEquals(0, categoryRepository.count());

        categoryMySQLGateway.deleteById(CategoryID.from("invalid"));

        assertEquals(0, categoryRepository.count());
    }

    @Test
    public void givenAPrePersistedCategoryAndValidCategoryId_whenCallsFindById_shouldReturnCategory() {
        final var expectedName = "Filmes";
        final var expectedDescription = "A categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectedName, expectedDescription, expectedIsActive);

        assertEquals(0, categoryRepository.count());

        categoryRepository.saveAndFlush(CategoryJpaEntity.from(aCategory));

        assertEquals(1, categoryRepository.count());

        final var actualCategory = categoryMySQLGateway.findById(aCategory.getId()).get();

        assertEquals(1, categoryRepository.count());

        assertEquals(aCategory.getId(), actualCategory.getId());
        assertEquals(expectedName, actualCategory.getName());
        assertEquals(expectedDescription, actualCategory.getDescription());
        assertEquals(expectedIsActive, actualCategory.isActive());
        assertEquals(aCategory.getCreatedAt(), actualCategory.getCreatedAt());
        assertEquals(aCategory.getUpdatedAt(), actualCategory.getUpdatedAt());
        assertEquals(aCategory.getDeletedAt(), actualCategory.getDeletedAt());
        assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenValidCategoryIdNotStored_whenCallsFindById_shouldReturnEmpty() {
        assertEquals(0, categoryRepository.count());

        final var actualCategory = categoryMySQLGateway.findById(CategoryID.from("empty"));

        assertTrue(actualCategory.isEmpty());
    }

    @Test
    public void givenPrePersistedCategories_whenCallsFindAll_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var filmes = Category.newCategory("Filmes", null, true);
        final var series = Category.newCategory("Séries", null, true);
        final var documentarios = Category.newCategory("Documentários", null, true);

        assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        assertEquals(3, categoryRepository.count());

        final var query = new SearchQuery(0, 1, "", "name", "asc");
        final var actualResult = categoryMySQLGateway.findAll(query);

        assertEquals(expectedPage, actualResult.currentPage());
        assertEquals(expectedPerPage, actualResult.perPage());
        assertEquals(expectedTotal, actualResult.total());
        assertEquals(expectedPerPage, actualResult.items().size());
        assertEquals(documentarios.getId(), actualResult.items().get(0).getId());
    }

    @Test
    public void givenEmptyCategoriesTable_whenCallsFindAll_shouldReturnEmptyPage() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 0;

        assertEquals(0, categoryRepository.count());

        final var query = new SearchQuery(0, 1, "", "name", "asc");
        final var actualResult = categoryMySQLGateway.findAll(query);

        assertEquals(expectedPage, actualResult.currentPage());
        assertEquals(expectedPerPage, actualResult.perPage());
        assertEquals(expectedTotal, actualResult.total());
        assertEquals(0, actualResult.items().size());
    }

    @Test
    public void givenFollowPagination_whenCallsFindAllWithPage1_shouldReturnPaginated() {
        var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 3;

        final var filmes = Category.newCategory("Filmes", null, true);
        final var series = Category.newCategory("Séries", null, true);
        final var documentarios = Category.newCategory("Documentários", null, true);

        assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        assertEquals(3, categoryRepository.count());

        var query = new SearchQuery(0, 1, "", "name", "asc");
        var actualResult = categoryMySQLGateway.findAll(query);

        assertEquals(expectedPage, actualResult.currentPage());
        assertEquals(expectedPerPage, actualResult.perPage());
        assertEquals(expectedTotal, actualResult.total());
        assertEquals(expectedPerPage, actualResult.items().size());
        assertEquals(documentarios.getId(), actualResult.items().get(0).getId());

        // Page 1
        expectedPage = 1;

        query = new SearchQuery(1, 1, "", "name", "asc");
        actualResult = categoryMySQLGateway.findAll(query);

        assertEquals(expectedPage, actualResult.currentPage());
        assertEquals(expectedPerPage, actualResult.perPage());
        assertEquals(expectedTotal, actualResult.total());
        assertEquals(expectedPerPage, actualResult.items().size());
        assertEquals(filmes.getId(), actualResult.items().get(0).getId());

        // Page 2
        expectedPage = 2;

        query = new SearchQuery(2, 1, "", "name", "asc");
        actualResult = categoryMySQLGateway.findAll(query);

        assertEquals(expectedPage, actualResult.currentPage());
        assertEquals(expectedPerPage, actualResult.perPage());
        assertEquals(expectedTotal, actualResult.total());
        assertEquals(expectedPerPage, actualResult.items().size());
        assertEquals(series.getId(), actualResult.items().get(0).getId());
    }

    @Test
    public void givenPrePersistedCategoriesAndDocAsTerms_whenCallsFindAllAndTermsMatchsCategoryName_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 1;

        final var filmes = Category.newCategory("Filmes", null, true);
        final var series = Category.newCategory("Séries", null, true);
        final var documentarios = Category.newCategory("Documentários", null, true);

        assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        assertEquals(3, categoryRepository.count());

        final var query = new SearchQuery(0, 1, "doc", "name", "asc");
        final var actualResult = categoryMySQLGateway.findAll(query);

        assertEquals(expectedPage, actualResult.currentPage());
        assertEquals(expectedPerPage, actualResult.perPage());
        assertEquals(expectedTotal, actualResult.total());
        assertEquals(expectedPerPage, actualResult.items().size());
        assertEquals(documentarios.getId(), actualResult.items().get(0).getId());
    }

    @Test
    public void givenPrePersistedCategoriesAndMaisAssistidaAsTerms_whenCallsFindAllAndTermsMatchsCategoryDescription_shouldReturnPaginated() {
        final var expectedPage = 0;
        final var expectedPerPage = 1;
        final var expectedTotal = 1;

        final var filmes = Category.newCategory("Filmes", "A categoria mais assistida", true);
        final var series = Category.newCategory("Séries", "Uma categoria assistida", true);
        final var documentarios = Category.newCategory("Documentários", "A categoria menos assistida", true);

        assertEquals(0, categoryRepository.count());

        categoryRepository.saveAll(List.of(
                CategoryJpaEntity.from(filmes),
                CategoryJpaEntity.from(series),
                CategoryJpaEntity.from(documentarios)
        ));

        assertEquals(3, categoryRepository.count());

        final var query = new SearchQuery(0, 1, "MAIS ASSISTIDA", "name", "asc");
        final var actualResult = categoryMySQLGateway.findAll(query);

        assertEquals(expectedPage, actualResult.currentPage());
        assertEquals(expectedPerPage, actualResult.perPage());
        assertEquals(expectedTotal, actualResult.total());
        assertEquals(expectedPerPage, actualResult.items().size());
        assertEquals(filmes.getId(), actualResult.items().get(0).getId());
    }

//    @Test
//    public void givenPrePersistedCategories_whenCallsExistsByIds_shouldReturnIds() {
//        // given
//        final var filmes = Category.newCategory("Filmes", "A categoria mais assistida", true);
//        final var series = Category.newCategory("Séries", "Uma categoria assistida", true);
//        final var documentarios = Category.newCategory("Documentários", "A categoria menos assistida", true);
//
//        assertEquals(0, categoryRepository.count());
//
//        categoryRepository.saveAll(List.of(
//                CategoryJpaEntity.from(filmes),
//                CategoryJpaEntity.from(series),
//                CategoryJpaEntity.from(documentarios)
//        ));
//
//        assertEquals(3, categoryRepository.count());
//
//        final var expectedIds = List.of(filmes.getId(), series.getId());
//
//        final var ids = List.of(filmes.getId(), series.getId(), CategoryID.from("123"));
//
//        // when
//        final var actualResult = categoryMySQLGateway.existsByIds(ids);
//
//        assertTrue(
//                expectedIds.size() == actualResult.size() &&
//                        expectedIds.containsAll(actualResult)
//        );
//    }
}