package de.hahn.blog.mavenapp.model.adfbc.facade.applicationModule;

import java.util.Hashtable;

import javax.naming.InitialContext;

import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.ApplicationModule;
import oracle.jbo.ApplicationModuleHome;
import oracle.jbo.JboContext;
import oracle.jbo.client.Configuration;

import org.apache.commons.lang3.StringUtils;

public class BlogMavenConfiguration {
    private static ADFLogger log = ADFLogger.createADFLogger(BlogMavenConfiguration.class);

    public static ApplicationModule createRootApplicationModule(String def, String config) {
        // sollen Debugmeldungen erzeugt werden?
        String debug = System.getProperty("de.hahn.blog.maven.debug");
        boolean debuginfo = false;
        boolean debugfine = false;
        boolean debugfinest = false;
        if (debug != null && !debug.isEmpty()) {
            if (debug.equals("0")) {
                debuginfo = false;
                debugfine = false;
                debugfinest = false;
            } else if (debug.equals("1")) {
                debuginfo = true;
                debugfine = false;
                debugfinest = false;
            } else if (debug.equals("2")) {
                debuginfo = true;
                debugfine = true;
                debugfinest = false;
            } else if (debug.equals("3")) {
                debuginfo = true;
                debugfine = true;
                debugfinest = true;
            }
        }

        if (debuginfo) {
            log.info("def:" + def + " config:" + config);
        }
        if (debugfinest) {
            // die gelesenen SystemProperties und das Environment ausgeben
            log.info("MyConfiguration: " + System.getProperties() + "---END");
            log.info("MyConfiguration ENV: " + System.getenv() + "---END-ENV");
        }

        ApplicationModule appModule = null;
        // die Systemparameter für die Verdindung laden
        String jdbcurl = System.getProperty("de.hahn.blog.maven.test.jdbcUrl");
        String jdbcuser = System.getProperty("de.hahn.blog.maven.test.jdbcUser");
        String jdbcpwd = System.getProperty("de.hahn.blog.maven.test.jdbcPwd");
        String testrun = System.getProperty("de.hahn.blog.maven.testrun");

        if (debuginfo) {
            log.info("Properties: testRun:-" + testrun + "- jdbcurl:-" + jdbcurl + "- jdbcuser:-" + jdbcuser + "-");
        }
        // über den Parameter de.hahn.blog.maven.testrun entscheiden, ob der Workaround verwendet weerden soll oder nicht
        if (isTestrun()) {
            ADFContext adfContext = null;
            try {
                if (debuginfo) {
                    log.info("Create AM for test run...");
                }
                //leeren ADFContext anlegen, damit der Lookup keine unnötige Meldung wirft
                adfContext = ADFContext.initADFContext(null, null, null, null);

                // Environment für den Lookup vorbereiten
                Hashtable ht = new Hashtable(4);
                ht.put(JboContext.INITIAL_CONTEXT_FACTORY, JboContext.JBO_CONTEXT_FACTORY);
                ht.put(JboContext.DEPLOY_PLATFORM, JboContext.PLATFORM_LOCAL);
                // Principle auf 'anonymous' setzten um getUserName() und HistoryColumns verwalten zu können!
                ht.put("java.naming.security.principal", "admin");
                ht.put("jbo.user.principal", "anonymous");
                InitialContext ctx = new InitialContext(ht);
                if (debugfine) {
                    log.info("initialContext:" + ctx);
                }
                Object amHome = ctx.lookup(def);
                if (debugfinest) {
                    log.info("ApplicationModuleHome:" + amHome);
                }
                ApplicationModuleHome ahome = (ApplicationModuleHome) amHome;
                // ApplicationModule anlegen über das ApplicationModuleHome
                ApplicationModule applicationModule = ahome.create();
                if (debugfine) {
                    log.info("am:" + applicationModule);
                }
                //                applicationModule.getTransaction().connectToDataSource(ht,"java:comp/env/jdbc/HRDS", "hr", "hr",false);
                // ApplicationModule mit der DB verbinden
                applicationModule.getTransaction().connect(jdbcurl, jdbcuser, jdbcpwd);
                if (debuginfo) {
                    log.info("...created " + def + " am=" + applicationModule);
                }
                appModule = applicationModule;
            } catch (Exception e) {
                log.severe(e);
                e.printStackTrace();
            } finally {
                ADFContext.resetADFContext(adfContext);
            }
        } else {
            if (debugfinest) {
                log.info("Create AM for normal run");
            }
            //Kein Testrun also normal ApplicationModule erzeugen
            appModule = Configuration.createRootApplicationModule(def, config);
            if (debuginfo) {
                log.info("...created " + def + " am=" + appModule);
            }
        }
        return appModule;
    }

    /**
     * Prüft ob ein Testlauf aktive ist.
     * @return true falls das SystemProperty de.hahn.blog.maven.testrun auf true gesetzt ist, false sonst
     */
    public static boolean isTestrun() {
        String testrun = System.getProperty("de.hahn.blog.maven.testrun");
        if (StringUtils.equalsIgnoreCase("true", testrun)) {
            return true;
        }

        return false;
    }

    public static void releaseRootApplicationModule(ApplicationModule appModule) {
        BlogMavenConfiguration.releaseRootApplicationModule(appModule, false);
    }

    public static void releaseRootApplicationModule(ApplicationModule appModule, boolean remove) {
        // sollen Debugmeldungen erzeugt werden?
        String debug = System.getProperty("de.hahn.blog.maven.debug");
        boolean debuginfo = false;
        boolean debugfine = false;
        boolean debugfinest = false;
        if (debug != null && !debug.isEmpty()) {
            if (debug.equals("0")) {
                debuginfo = false;
                debugfine = false;
                debugfinest = false;
            } else if (debug.equals("1")) {
                debuginfo = true;
                debugfine = false;
                debugfinest = false;
            } else if (debug.equals("2")) {
                debuginfo = true;
                debugfine = true;
                debugfinest = false;
            } else if (debug.equals("3")) {
                debuginfo = true;
                debugfine = true;
                debugfinest = true;
            }
        }

        try {
            if (null != appModule) {
                if (isTestrun()) {
                    if (debuginfo) {
                        log.info("Disconnrect testRun am=" + appModule + " name=" + appModule.getDefFullName());
                    }
                    // Falls ein Testrun vorliegt, wird das aM einfach von der DB disconnected. Der rest wird mit GC abgeräumt
                    appModule.getTransaction().disconnect();
                } else {
                    if (debuginfo) {
                        log.info("Disconnrect normal am=" + appModule + " name=" + appModule.getDefFullName());
                    }
                    // normale Verarbeitung
                    Configuration.releaseRootApplicationModule(appModule, remove);
                }
                appModule = null;
            }
        } catch (Exception e) {
            log.warning("Fixture konnte nicht freigegeben werden.", e);
        }
    }
}
