package qunit;

import org.junit.Test;

import play.modules.qunit.QunitFailedException;
import play.modules.qunit.QunitRunner;

/**
 *
 * @author Guillaume Cusnieux
 * @email gcusnieux@gmail.com
 */

public class QunitTest extends QunitRunner {


    //Remove "expected=QunitFailedException.class" for the right test result
    @Override
    @Test(expected=QunitFailedException.class)
    public void run() throws QunitFailedException {
        super.run();
    }

}
