package br.com.mmmsieto.infrastructure.persistence;

import br.com.mmmsieto.MySQLGatewayTest;
import br.com.mmmsieto.domain.category.Category;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryJpaEntity;
import br.com.mmmsieto.infrastructure.category.persistence.CategoryRepository;
import org.hibernate.PropertyValueException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;

@MySQLGatewayTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @Test
    public void givenAnInvalidNullName_whenCallsSave_shouldReturnError() {
        final var expectedPropertyName = "name";
        final var expectedMessage = "not-null property references a null or transient value : br.com.mmmsieto.infrastructure.category.persistence.CategoryJpaEntity.name";

        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setName(null);

        final var actualException =
                assertThrows(DataIntegrityViolationException.class, () -> repository.save(anEntity));

        final var actualCause =
                assertInstanceOf(PropertyValueException.class, actualException.getCause());

        assertEquals(expectedPropertyName, actualCause.getPropertyName());
        assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    public void givenAnInvalidNullCreatedAt_whenCallsSave_shouldReturnError() {
        final var expectedPropertyName = "createdAt";
        final var expectedMessage = "not-null property references a null or transient value : br.com.mmmsieto.infrastructure.category.persistence.CategoryJpaEntity.createdAt";

        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setCreatedAt(null);

        final var actualException =
                assertThrows(DataIntegrityViolationException.class, () -> repository.save(anEntity));

        final var actualCause =
                assertInstanceOf(PropertyValueException.class, actualException.getCause());

        assertEquals(expectedPropertyName, actualCause.getPropertyName());
        assertEquals(expectedMessage, actualCause.getMessage());
    }

    @Test
    public void givenAnInvalidNullUpdatedAt_whenCallsSave_shouldReturnError() {
        final var expectedPropertyName = "updatedAt";
        final var expectedMessage = "not-null property references a null or transient value : br.com.mmmsieto.infrastructure.category.persistence.CategoryJpaEntity.updatedAt";

        final var aCategory = Category.newCategory("Filmes", "A categoria mais assistida", true);

        final var anEntity = CategoryJpaEntity.from(aCategory);
        anEntity.setUpdatedAt(null);

        final var actualException =
                assertThrows(DataIntegrityViolationException.class, () -> repository.save(anEntity));

        final var actualCause =
                assertInstanceOf(PropertyValueException.class, actualException.getCause());

        assertEquals(expectedPropertyName, actualCause.getPropertyName());
        assertEquals(expectedMessage, actualCause.getMessage());
    }
}

