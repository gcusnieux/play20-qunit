package play.modules.qunit;

import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.start;
import static play.test.Helpers.stop;
import static play.test.Helpers.testServer;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import play.Application;
import play.libs.F.Callback;
import play.test.FakeApplication;
import play.test.TestBrowser;

/**
 *
 * @author Guillaume Cusnieux
 * @email gcusnieux@gmail.com
 *
 */
public class QunitRunner {

    static FakeApplication app;

    @BeforeClass
    public static void setup() {
        app = fakeApplication();
        start(app);
    }

    @AfterClass
    public static void after() {
        stop(app);
    }

    final List<String> errors = new ArrayList<String>();

    public void run() throws QunitFailedException {
        Application application = play.Play.application();
        File folder = application.getFile("/qunit");
        Collection<File> jsFiles = FileUtils.listFiles(folder,
                new String[] { "js" }, true);

        for (File jsFile : jsFiles) {
            launchTest(jsFile);
        }

        if (errors.size() > 0) {
            for (String err : errors) {
                log(err);
            }
            throw new QunitFailedException();
        }
    }

    private void launchTest(final File jsFile) {

        log("==========================================");
        running(testServer(3333, fakeApplication()), HTMLUNIT,
                new Callback<TestBrowser>() {
                    public void invoke(TestBrowser browser) {
                        String testname = jsFile.getName()
                                .replace(".js", "");
                        String url = "http://localhost:3333/qunit/" + testname;
                        browser.goTo(url);
                        log("Started the " + testname);

                        log(browser.$("body").getTexts());

                        try {
                            Thread.sleep(500L);
                        } catch (InterruptedException e) {
                        }
                        log(browser.$("#qunit-testresult")
                                .getTexts());

                        log("------------------------------------------");

                        FluentList fls = browser.$("#qunit-tests > li > strong");
                        Iterator it = fls.iterator();
                        int index = 0;
                        while (it.hasNext()) {
                            log("   " + ++index + ". "
                                    + ((FluentWebElement) it.next()).getText());
                        }

                        int nbErrors = Integer.parseInt(browser.$("span.failed")
                                .getTexts()
                                .get(0)
                                .toString()
                                .trim());

                        if (nbErrors > 0) {

                            errors.add("x The ".concat(testname)
                                    + " failed with " + nbErrors + " error"
                                    + (nbErrors > 1 ? "s" : "") + "!");
                            errors.add("Goto " + url + " for more details");
                        } else {
                            log("+ The ".concat(testname)
                                    .concat(" is passed !"));
                        }
                    }
                });

    }

    private void log(Object err) {
        System.out.println("[Qunit test] " + err);
    }

}
