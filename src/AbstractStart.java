package src;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractStart extends JFrame {
    public AbstractStart(String title) {
        setTitle(title);
        setSize(1440, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        initialize();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    // Metode abstrak yang harus diimplementasikan oleh subclass
    protected abstract JPanel createHeaderPanel();
    protected abstract JPanel createMainPanel();
    protected abstract JPanel createAdditionalPanel();

    // Metode untuk menambahkan panel ke frame
    private void initialize() {
        add(createHeaderPanel());
        add(createMainPanel());
        add(createAdditionalPanel());
    }
}
