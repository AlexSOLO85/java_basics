import javax.swing.*;

public class MainForm {
    private static final String DELIMITER = " ";
    private static final String CLEAR_TEXT = "";
    private static final String MESSAGE = "Поле \"Фамилия\" и \"Имя\" обязательны к заполнению";
    private static final String TITLE = "Ошибка!";

    private JPanel mainPanel;
    private JPanel collapseInputPanel;
    private JPanel collapsePanel;
    private JPanel expandPanel;
    private JTextField surnameTextField;
    private JTextField nameTextField;
    private JTextField middleNameTextField;
    private JTextField resultTextField;
    private JButton collapseButton;
    private JButton expandButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainForm() {
        assert expandPanel != null;
        expandPanel.setVisible(false);

        collapseButton.addActionListener(e -> {
            if (surnameTextField.getText().isEmpty() && nameTextField.getText().isEmpty() ||
                    surnameTextField.getText().isEmpty() && !nameTextField.getText().isEmpty() ||
                    !surnameTextField.getText().isEmpty() && nameTextField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, MESSAGE,
                        TITLE, JOptionPane.ERROR_MESSAGE);
            } else {
                resultTextField.setText(surnameTextField.getText() +
                        DELIMITER +
                        nameTextField.getText() +
                        DELIMITER +
                        middleNameTextField.getText());

                surnameTextField.setText(CLEAR_TEXT);
                nameTextField.setText(CLEAR_TEXT);
                middleNameTextField.setText(CLEAR_TEXT);

                collapsePanel.setVisible(false);
                collapseButton.setVisible(false);
                expandButton.setVisible(true);
                expandPanel.setVisible(true);
            }
        });

        expandButton.addActionListener(e -> {
            String[] strings = resultTextField.getText().split(DELIMITER);

            if (strings.length == 3 || strings.length == 2) {
                surnameTextField.setText(strings[0]);
                nameTextField.setText(strings[1]);
                if (strings.length == 2) {
                    middleNameTextField.setText(CLEAR_TEXT);
                } else {
                    middleNameTextField.setText(strings[2]);
                }

                resultTextField.setText(CLEAR_TEXT);

                expandPanel.setVisible(false);
                expandButton.setVisible(false);
                collapseButton.setVisible(true);
                collapsePanel.setVisible(true);
            }

            if (strings.length == 0 || strings.length == 1) {
                JOptionPane.showMessageDialog(mainPanel, MESSAGE,
                        TITLE, JOptionPane.ERROR_MESSAGE);

                resultTextField.setText(CLEAR_TEXT);

                expandPanel.setVisible(true);
                expandButton.setVisible(true);
                collapseButton.setVisible(false);
                collapsePanel.setVisible(false);
            }
        });
    }
}