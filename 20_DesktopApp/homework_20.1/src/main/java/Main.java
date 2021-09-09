import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setSize(500, 300);
        jFrame.setResizable(false);
        jFrame.add(new MainForm().getMainPanel());

        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);

        jFrame.setVisible(true);
    }
}