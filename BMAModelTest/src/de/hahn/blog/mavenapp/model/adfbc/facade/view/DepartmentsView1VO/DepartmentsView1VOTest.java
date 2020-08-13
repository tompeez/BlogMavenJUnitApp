package de.hahn.blog.mavenapp.model.adfbc.facade.view.DepartmentsView1VO;

import de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule.BMAAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.*;
import static org.junit.Assert.*;

public class DepartmentsView1VOTest {
    private BMAAppModuleAMFixture fixture1 = BMAAppModuleAMFixture.getInstance();

    public DepartmentsView1VOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("DepartmentsView1");
        assertNotNull(view);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
