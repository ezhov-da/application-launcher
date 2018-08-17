package ru.ezhov.application.launcher;

import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;

import javax.swing.*;

public class App implements HotKeyListener {
    private JFrame frame;

    public App() {
        frame = new JFrame("_________");
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

    }

    @Override
    public void onHotKey(HotKey hotKey) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Throwable ex) {
                    //
                }
                frame.setVisible(true);
            }
        });
    }
}
