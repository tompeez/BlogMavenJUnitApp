package de.hahn.blog.mavenapp.model.adfbc.facade.view.DepartmentsView2VO;

import de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule.BMAAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class DepartmentsView2VOTest {
    private BMAAppModuleAMFixture fixture1 = BMAAppModuleAMFixture.getInstance();

    public DepartmentsView2VOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("DepartmentsView2");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
