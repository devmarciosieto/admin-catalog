package br.com.mmmsieto;

import br.com.mmmsieto.infrastructure.Main;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.AbstractEnvironment;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {

    @Test
    public void testMain() {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "test");
        assertNotNull(new Main());
        Main.main(new String[]{});
    }

}