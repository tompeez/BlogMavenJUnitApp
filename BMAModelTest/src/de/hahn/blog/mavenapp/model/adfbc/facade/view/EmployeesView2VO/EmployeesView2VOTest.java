package de.hahn.blog.mavenapp.model.adfbc.facade.view.EmployeesView2VO;

import de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule.BMAAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class EmployeesView2VOTest {
    private BMAAppModuleAMFixture fixture1 = BMAAppModuleAMFixture.getInstance();

    public EmployeesView2VOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("EmployeesView2");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
