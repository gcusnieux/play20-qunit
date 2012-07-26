package controllers;

import java.io.IOException;

import play.modules.qunit.QunitHelper;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author Guillaume Cusnieux
 * @email gcusnieux@gmail.com
 */
public class Qunit extends Controller {

    public static Result test(String filename) throws IOException {
        return QunitHelper.test(filename);
    }

}