package de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule;

import oracle.jbo.ApplicationModule;
import oracle.jbo.client.Configuration;

public class BMAAppModuleAMFixture {
    private static BMAAppModuleAMFixture fixture1 = new BMAAppModuleAMFixture();
    private ApplicationModule _am;

    private BMAAppModuleAMFixture() {
        _am =
            Configuration.createRootApplicationModule("de.hahn.blog.mavenapp.model.adfbc.facade.BMAAppModule",
                                                      "BMAAppModuleLocal");
    }

    public void setUp() {
    }

    public void tearDown() {
    }

    public static BMAAppModuleAMFixture getInstance() {
        return fixture1;
    }

    public void release() throws Exception {
        Configuration.releaseRootApplicationModule(_am, true);
    }

    public ApplicationModule getApplicationModule() {
        return _am;
    }
}
