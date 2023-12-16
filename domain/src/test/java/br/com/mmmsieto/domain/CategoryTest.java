package br.com.mmmsieto.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    public void testNewCategory() {
        assertNotNull(new Category());
    }

}