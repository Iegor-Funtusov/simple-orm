package ua.com.alevel.factory;

import ua.com.alevel.config.CoroneConfig;

/**
 * @author Iehor Funtusov, created 02/11/2020 - 7:53 PM
 */

public abstract class AbstractConnection {

    public abstract void connect(CoroneConfig config);
}
