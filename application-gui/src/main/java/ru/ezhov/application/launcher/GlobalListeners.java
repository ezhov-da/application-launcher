package ru.ezhov.application.launcher;

import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class GlobalListeners {
    private static final Logger logger = Logger.getLogger(GlobalListeners.class.getName());
    private static final Provider PROVIDER = Provider.getCurrentProvider(true);


    public static synchronized void setProvider(String s, HotKeyListener hotKeyListener) {
        logger.log(Level.INFO, "Set provider: {0}", s);
        PROVIDER.register(KeyStroke.getKeyStroke(s), hotKeyListener);
    }

    public static synchronized void stopProvider() {
        logger.log(Level.INFO, "Provider stop");
        PROVIDER.reset();
        PROVIDER.stop();
    }
}
