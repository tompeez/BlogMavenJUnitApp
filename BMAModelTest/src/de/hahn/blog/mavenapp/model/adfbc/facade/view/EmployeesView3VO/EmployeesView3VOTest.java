package de.hahn.blog.mavenapp.model.adfbc.facade.view.EmployeesView3VO;

import de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule.BMAAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class EmployeesView3VOTest {
    private BMAAppModuleAMFixture fixture1 = BMAAppModuleAMFixture.getInstance();

    public EmployeesView3VOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("EmployeesView3");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
