package ru.ezhov.application.launcher;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ApplicationLauncher {
    private static final Logger logger = Logger.getLogger(ApplicationLauncher.class.getName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager()
                    .readConfiguration(
                            ApplicationLauncher.class
                                    .getResourceAsStream("/logger.properties"));
        } catch (IOException ex) {
            Logger.getLogger(ApplicationLauncher.class.getName())
                    .log(Level.SEVERE, "Error on read logger conf", ex);
            System.out.println("Error on read logger conf");
        } catch (SecurityException ex) {
            Logger.getLogger(ApplicationLauncher.class.getName())
                    .log(Level.SEVERE, "Error on read logger conf", ex);
            System.out.println("Error on read logger conf");
        }
        runApp();
    }

    private static void runApp() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ApplicationLauncher.logger.log(Level.INFO, "Run application");

                if (SystemTray.isSupported()) {
                    ApplicationLauncher.logger.log(Level.INFO, "SystemTray isSupported");
                    SystemTray tray = SystemTray.getSystemTray();

                    TrayIcon icon =
                            new TrayIcon(
                                    ru.ezhov.application.launcher.SettingsApp.IconApp.ICON_APP, "RUNNER APPLICATION");
                    try {
                        ApplicationLauncher.logger.log(Level.INFO, "Create menu");
                        PopupMenu menu = XMLReader.create();
                        icon.setPopupMenu(menu);
                        ApplicationLauncher.logger.log(Level.INFO, "App is ready");
                    } catch (ParserConfigurationException ex) {
                        ApplicationLauncher.logger.log(Level.SEVERE, "Error read menu", ex);
                    } catch (SAXException ex) {
                        ApplicationLauncher.logger.log(Level.SEVERE, "Error read menu", ex);
                    } catch (IOException ex) {
                        ApplicationLauncher.logger.log(Level.SEVERE, "Error read menu", ex);
                    }
                    try {
                        tray.add(icon);
                    } catch (AWTException ex) {
                        ApplicationLauncher.logger.log(Level.SEVERE, "Not add tray to panel", ex);
                        ApplicationLauncher.logger.log(Level.INFO, "App close");
                        System.exit(2);
                    }
                } else {
                    ApplicationLauncher.logger.log(Level.WARNING, "SystemTray not supported");
                    ApplicationLauncher.logger.log(Level.INFO, "App close");
                    System.exit(1);
                }
            }
        });
    }
}
