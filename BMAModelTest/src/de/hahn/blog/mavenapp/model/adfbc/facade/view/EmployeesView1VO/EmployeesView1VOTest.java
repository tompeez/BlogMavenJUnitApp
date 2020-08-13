package de.hahn.blog.mavenapp.model.adfbc.facade.view.EmployeesView1VO;

import de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule.BMAAppModuleAMFixture;

import oracle.jbo.ViewObject;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class EmployeesView1VOTest {
    private BMAAppModuleAMFixture fixture1 = BMAAppModuleAMFixture.getInstance();

    public EmployeesView1VOTest() {
    }

    @Test
    public void testAccess() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("EmployeesView1");
        assertNotNull(view);
    }

    @Test
    public void testCountRows() {
        ViewObject view = fixture1.getApplicationModule().findViewObject("EmployeesView1");
        long count = view.getEstimatedRowCount();
        assertEquals("The number of rows is wrong!", 107, count);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }
}
