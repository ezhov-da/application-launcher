package ru.ezhov.application.launcher;

import com.tulskiy.keymaster.common.HotKeyListener;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JarLoader {
    private static final Logger LOG =
            Logger.getLogger(JarLoader.class.getName());

    private static URLClassLoader uRLClassLoader;

    private static void loadJars()
            throws MalformedURLException {
        LOG.log(Level.INFO, "Start load jars");
        File file = new File("plugins");
        File[] listFile = file.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".jar");
            }
        });
        LOG.log(Level.INFO, "Find {0} jars",
                listFile.length);
        URL[] loadersMass = new URL[listFile.length];
        int i = 0;
        for (int size = listFile.length; i < size; i++) {
            loadersMass[i] = listFile[i].toURI().toURL();
        }
        uRLClassLoader = new URLClassLoader(loadersMass);
    }

    public static HotKeyListener getLoader(String nameClass)
            throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            MalformedURLException {
        if (uRLClassLoader == null) {
            loadJars();
        }
        Class clazz = uRLClassLoader.loadClass(nameClass);
        Object object = clazz.newInstance();
        if ((object instanceof HotKeyListener)) {
            LOG.log(Level.INFO, "Return listeners");
            return (HotKeyListener) object;
        }
        return null;
    }
}
