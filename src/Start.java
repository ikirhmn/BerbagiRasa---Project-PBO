package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Start extends AbstractStart {

    public Start() {
        super("BerbagiRasa");
    }

    @Override
    protected JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBounds(0, 0, 1600, 80);
        headerPanel.setBackground(new Color(50, 150, 64));
        headerPanel.setLayout(null);

        JLabel appName = new JLabel("BerbagiRasa");
        appName.setBounds(20, 20, 300, 40);
        appName.setFont(new Font("Poppins", Font.BOLD, 34));
        appName.setForeground(Color.WHITE);
        headerPanel.add(appName);

        return headerPanel;
    }

    @Override
    protected JPanel createMainPanel() {
        JPanel panelPertama = new JPanel();
        panelPertama.setBounds(50, 150, 650, 700);
        panelPertama.setBackground(new Color(240, 240, 240));
        panelPertama.setLayout(null);

        JLabel titleLabel = new JLabel("BerbagiRasa");
        titleLabel.setBounds(30, 50, 600, 80);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 54));
        titleLabel.setForeground(new Color(22, 22, 22));
        panelPertama.add(titleLabel);

        JLabel sloganLabel = new JLabel("<html><div style='text-align: justify;'>"
                + "Selamat datang di platform yang tepat untuk menghubungkan<br>"
                + "kebaikan Anda dengan mereka yang membutuhkan.<br>"
                + "Kami hadir untuk membantu mendistribusikan surplus makanan<br>"
                + "Anda kepada panti asuhan, menciptakan kebahagiaan dan kebermanfaatan<br>"
                + "di tengah kehidupan yang penuh makna."
                + "</div></html>");
        sloganLabel.setBounds(30, 100, 600, 200);
        sloganLabel.setFont(new Font("Poppins", Font.PLAIN, 16));
        sloganLabel.setForeground(Color.DARK_GRAY);
        panelPertama.add(sloganLabel);

        JButton mulaiButton = new JButton("Mulai Jelajahi");
        mulaiButton.setBounds(30, 300, 200, 50);
        mulaiButton.setBackground(new Color(50, 150, 64));
        mulaiButton.setForeground(Color.WHITE);
        mulaiButton.setFont(new Font("Poppins", Font.BOLD, 18));
        mulaiButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        mulaiButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                mulaiButton.setBackground(new Color(70, 216, 89));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                mulaiButton.setBackground(new Color(50, 150, 64));
            }
        });

        ActionListener mulai = e -> {
            new loginas();
        };
        mulaiButton.addActionListener(mulai);

        panelPertama.add(mulaiButton);
        return panelPertama;
    }

    @Override
    protected JPanel createAdditionalPanel() {
        JPanel panelKedua = new JPanel();
        panelKedua.setBounds(750, 70, 600, 700);
        panelKedua.setBackground(new Color(240, 240, 240));
        panelKedua.setLayout(new BorderLayout());

        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = new ImageIcon("assets\\start.png");
        imageLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        panelKedua.add(imageLabel, BorderLayout.CENTER);

        return panelKedua;
    }

    public static void main(String[] args) {
        new Start();
    }
}
