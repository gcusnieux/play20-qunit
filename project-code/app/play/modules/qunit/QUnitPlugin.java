package play.modules.qunit;

import play.Application;
import play.Logger;
import play.Logger.ALogger;
import play.Plugin;

/**
 *
 * @author Guillaume Cusnieux
 * @email gcusnieux@gmail.com
 *
 */
public class QUnitPlugin extends Plugin {

    ALogger logger = Logger.of("qunit");
    private boolean enabled = false;

    private final Application application;

    public QUnitPlugin(Application application) {
        this.application = application;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (application.isDev() || application.isTest()) {
            enabled = true;
            logger.debug("qunit is enabled");
        } else {
            enabled = false;
            logger.debug("qunit is disabled");
        }
    }

    @Override
    public boolean enabled() {
        return enabled;
    }

}
