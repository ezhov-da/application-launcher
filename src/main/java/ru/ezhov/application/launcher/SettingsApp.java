package ru.ezhov.application.launcher;

import javax.swing.*;

public class SettingsApp {
    public static class XmlApp {
        public static final String TAG_BASIC = "menuitem";
        public static final String TAG_REFRESH = "refresh";
        public static final String TAG_EXIT = "exit";
        public static final String ATTR_NAME = "name";
        public static final String ATTR_KEYS = "keys";
        public static final String ATTR_CLASS = "class";
    }

    public static class StringApp {
        public static final String RUN_APP = "RUNNER APPLICATION";
        public static final String FOLDER_PLUGIN = "plugins";
        public static final String NAME_CREATE_FILE = "create.xml";
        public static final String FILE_EXT = ".jar";
    }

    public static class IconApp {
        public static final java.awt.Image ICON_APP =
                new ImageIcon(
                        SettingsApp.class.getResource("/start_app.png")).getImage();
    }

}
