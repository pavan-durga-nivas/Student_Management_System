package WORK;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentManagementUI extends JFrame {
    private JTextField sidField, snameField, cgpaField;
    private JTextArea outputArea;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public StudentManagementUI() {
        setTitle("Student Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // UI Components
        String[] options = {"Add Student", "Update Student", "Delete Student", "View Student"};
        JComboBox<String> optionsComboBox = new JComboBox<>(options);
        optionsComboBox.addActionListener(new OptionsActionListener());

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Add Student Panel
        JPanel addPanel = createStudentPanel("Add Student");

        // Update Student Panel
        JPanel updatePanel = createStudentPanel("Update Student");

        // Delete Student Panel
        JPanel deletePanel = createDeletePanel();

        // View Student Panel
        JPanel viewPanel = createViewPanel();

        // Adding panels to the CardLayout panel
        cardPanel.add(addPanel, "Add Student");
        cardPanel.add(updatePanel, "Update Student");
        cardPanel.add(deletePanel, "Delete Student");
        cardPanel.add(viewPanel, "View Student");

        // Output Area
        outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Adding components to the main frame
        add(optionsComboBox, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Set initial panel
        cardLayout.show(cardPanel, "Add Student");
    }

    private JPanel createStudentPanel(String actionCommand) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        sidField = new JTextField(10);
        snameField = new JTextField(10);
        cgpaField = new JTextField(10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        panel.add(sidField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Student Name:"), gbc);

        gbc.gridx = 1;
        panel.add(snameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("CGPA:"), gbc);

        gbc.gridx = 1;
        panel.add(cgpaField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JButton actionButton = new JButton(actionCommand);
        actionButton.addActionListener(new StudentActionListener());
        panel.add(actionButton, gbc);

        return panel;
    }

    private JPanel createDeletePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        sidField = new JTextField(10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        panel.add(sidField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton deleteButton = new JButton("Delete Student");
        deleteButton.addActionListener(new StudentActionListener());
        panel.add(deleteButton, gbc);

        return panel;
    }

    private JPanel createViewPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        sidField = new JTextField(10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        panel.add(sidField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JButton viewButton = new JButton("View Student");
        viewButton.addActionListener(new StudentActionListener());
        panel.add(viewButton, gbc);

        return panel;
    }

    private class OptionsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedOption = (String) comboBox.getSelectedItem();
            cardLayout.show(cardPanel, selectedOption);
        }
    }

    private class StudentActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            try {
                int sid = Integer.parseInt(sidField.getText());
                String sname = snameField.getText();
                float cgpa = cgpaField.getText().isEmpty() ? 0 : Float.parseFloat(cgpaField.getText());

                switch (actionCommand) {
                    case "Add Student":
                        StudentDatabaseOperations.insertStudent(sid, sname, cgpa);
                        outputArea.setText("Student added successfully!");
                        break;

                    case "Update Student":
                        StudentDatabaseOperations.updateStudent(sid, sname, cgpa);
                        outputArea.setText("Student updated successfully!");
                        break;

                    case "Delete Student":
                        StudentDatabaseOperations.deleteStudent(sid);
                        outputArea.setText("Student deleted successfully!");
                        break;

                    case "View Student":
                        String result = StudentDatabaseOperations.viewStudent(sid);
                        outputArea.setText(result);
                        break;
                }
            } catch (Exception ex) {
                outputArea.setText("Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentManagementUI ui = new StudentManagementUI();
            ui.setVisible(true);
        });
    }
}
