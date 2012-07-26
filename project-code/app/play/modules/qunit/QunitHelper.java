package play.modules.qunit;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import play.Application;
import play.api.templates.Html;
import play.mvc.Result;

/**
*
* @author Guillaume Cusnieux
* @email gcusnieux@gmail.com
*
*/
public class QunitHelper {

    private static final String TEST_FOLDER = "qunit/";

    public static Result test(String filename) throws IOException {
        if (play.Play.isProd()) {
            return play.mvc.Controller.notFound();
        }
        return play.mvc.Controller.ok(html(filename));
    }

    public static Html html(String filename) throws IOException {
        Application application = play.Play.application();
        File js = application.getFile(TEST_FOLDER + filename + ".js");
        File html = application.getFile(TEST_FOLDER + filename + ".html");
        String content = html.exists() ? FileUtils.readFileToString(html) : "";

        return views.html.qunit.qunit.render(filename, content,
                FileUtils.readFileToString(js));
    }

}
