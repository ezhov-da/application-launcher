package ru.ezhov.application.launcher;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class XMLReader {
    public static PopupMenu create()
            throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        MenuReader menuReader = new MenuReader();
        MenuReader saxp = menuReader;
        IMenuCreator menuCreator = menuReader;
        parser.parse(new File("plugins" + File.separator + "create.xml"), saxp);
        return menuCreator.getMenu();
    }
}
