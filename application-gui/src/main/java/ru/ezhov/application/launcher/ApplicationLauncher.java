package ru.ezhov.application.launcher;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ApplicationLauncher {
    private static final Logger LOG = Logger.getLogger(ApplicationLauncher.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager()
                    .readConfiguration(
                            ApplicationLauncher.class
                                    .getResourceAsStream("/logger.properties"));
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error on read logger conf", ex);
        }
        runApp();
    }

    private static void runApp() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    LOG.log(Level.INFO, "Run application");
                    if (SystemTray.isSupported()) {
                        LOG.log(Level.INFO, "SystemTray isSupported");
                        SystemTray tray = SystemTray.getSystemTray();
                        TrayIcon icon =
                                new TrayIcon(
                                        ru.ezhov.application.launcher.SettingsApp.IconApp.ICON_APP, "RUNNER APPLICATION");
                        try {
                            LOG.log(Level.INFO, "Create menu");
                            PopupMenu menu = XMLReader.create();
                            icon.setPopupMenu(menu);
                            LOG.log(Level.INFO, "App is ready");
                            tray.add(icon);
                        } catch (Exception ex) {
                            LOG.log(Level.SEVERE, "Not add tray to panel", ex);
                            LOG.log(Level.INFO, "App close");
                            System.exit(2);
                        }
                    } else {
                        LOG.log(Level.WARNING, "SystemTray not supported");
                        LOG.log(Level.INFO, "App close");
                        System.exit(1);
                    }
                } catch (Throwable throwable) {
                    LOG.log(Level.SEVERE, "Ошибка при запуске приложения", throwable);
                }
            }
        });
    }
}
