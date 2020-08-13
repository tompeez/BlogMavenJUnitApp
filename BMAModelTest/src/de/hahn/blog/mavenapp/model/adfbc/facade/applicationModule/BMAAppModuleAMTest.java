package de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule;

import de.hahn.blog.mavenapp.model.adfbc.facade.common.BMAAppModule;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class BMAAppModuleAMTest {
    private BMAAppModuleAMFixture fixture1 = BMAAppModuleAMFixture.getInstance();

    private BMAAppModule _amImpl;

    public BMAAppModuleAMTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSayHello() {
        BMAAppModule am = (BMAAppModule) fixture1.getApplicationModule();
        String hello = am.sayHello("Junit Test");
        assertEquals("Method returned wrong message!", "Hello Junit Test!", hello);
    }
}
