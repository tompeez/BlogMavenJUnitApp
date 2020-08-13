package de.hahn.blog.mavenapp.model.adfbc.facade;

import de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule.BMAAppModuleAMFixture;
import de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule.BMAAppModuleAMTest;
import de.hahn.blog.mavenapp.model.adfbc.facade.view.DepartmentsView1VO.DepartmentsView1VOTest;
import de.hahn.blog.mavenapp.model.adfbc.facade.view.DepartmentsView2VO.DepartmentsView2VOTest;
import de.hahn.blog.mavenapp.model.adfbc.facade.view.EmployeesView1VO.EmployeesView1VOTest;
import de.hahn.blog.mavenapp.model.adfbc.facade.view.EmployeesView2VO.EmployeesView2VOTest;
import de.hahn.blog.mavenapp.model.adfbc.facade.view.EmployeesView3VO.EmployeesView3VOTest;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ EmployeesView3VOTest.class, EmployeesView2VOTest.class, DepartmentsView2VOTest.class,
                      EmployeesView1VOTest.class, DepartmentsView1VOTest.class, BMAAppModuleAMTest.class
    })
public class AllBMAAppModuleTests {
    @BeforeClass
    public static void setUp() {
    }

    @AfterClass
    public static void tearDown() throws Exception {
        BMAAppModuleAMFixture.getInstance().release();
    }
}
