package ru.ezhov.application.launcher;

import com.tulskiy.keymaster.common.HotKeyListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MenuReader
        extends DefaultHandler
        implements IMenuCreator {
    private static final Logger logger = Logger.getLogger(MenuReader.class.getName());
    private static final PopupMenu POPUP_MENU = new PopupMenu();


    private MenuItem menuItem;

    private String nameMenu;

    private HotKeyListener hotKeyListener;


    public PopupMenu getMenu() {
        return POPUP_MENU;
    }

    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes) throws SAXException {
        if (qName.equals("menuitem")) {
            this.nameMenu = (attributes.getValue("name")
                    + " --> "
                    + attributes.getValue("keys"));
            this.menuItem = new MenuItem(this.nameMenu);
            POPUP_MENU.add(this.menuItem);
            setListeners(attributes.getValue("keys"), attributes.getValue("class"));
        } else if (qName.equals("exit")) {
            POPUP_MENU.addSeparator();
            this.nameMenu = attributes.getValue("name");
            MenuItem menuExit = new MenuItem(this.nameMenu);
            menuExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    MenuReader.logger.log(Level.INFO, "Exit application");

                    GlobalListeners.stopProvider();
                    System.exit(0);
                }
            });
            POPUP_MENU.add(menuExit);
        }
    }


    private void setListeners(String keys, String clazz) {
        try {
            this.hotKeyListener = LoadTreatment.getLoader(clazz);
            GlobalListeners.setProvider(keys, this.hotKeyListener);
        } catch (ClassNotFoundException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            logger.log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
