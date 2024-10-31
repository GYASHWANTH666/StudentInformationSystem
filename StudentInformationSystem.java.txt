import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Class representing a Student
class Student {
    private String name;
    private String id;
    private String email;

    public Student(String name, String id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Email: " + email;
    }
}

// Main class for the Student Information System with GUI
public class StudentInformationSystem {
    private static ArrayList<Student> studentList = new ArrayList<>();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();
    private static JList<String> studentJList = new JList<>(listModel);

    public static void main(String[] args) {
        // Create the main JFrame
        JFrame frame = new JFrame("Student Information System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // Panel for input fields and buttons
        JPanel panel = new JPanel(new GridLayout(5, 2));

        // Input fields for student information
        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField emailField = new JTextField();

        // Add labels and text fields to panel
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        // Buttons for CRUD operations
        JButton addButton = new JButton("Add");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        JButton viewButton = new JButton("View");

        // Add buttons to the panel
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);

        // Student List Display Area
        JScrollPane scrollPane = new JScrollPane(studentJList);

        // Add event listeners for buttons
        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String id = idField.getText();
            String email = emailField.getText();

            if (!name.isEmpty() && !id.isEmpty() && !email.isEmpty()) {
                Student student = new Student(name, id, email);
                studentList.add(student);
                listModel.addElement(student.toString());
                clearFields(nameField, idField, emailField);
            } else {
                JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        updateButton.addActionListener(e -> {
            int selectedIndex = studentJList.getSelectedIndex();
            if (selectedIndex != -1) {
                String name = nameField.getText();
                String id = idField.getText();
                String email = emailField.getText();

                if (!name.isEmpty() && !id.isEmpty() && !email.isEmpty()) {
                    Student student = studentList.get(selectedIndex);
                    student.setName(name);
                    student.setId(id);
                    student.setEmail(email);
                    listModel.set(selectedIndex, student.toString());
                    clearFields(nameField, idField, emailField);
                } else {
                    JOptionPane.showMessageDialog(frame, "All fields must be filled!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a student to update!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedIndex = studentJList.getSelectedIndex();
            if (selectedIndex != -1) {
                studentList.remove(selectedIndex);
                listModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a student to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        viewButton.addActionListener(e -> {
            int selectedIndex = studentJList.getSelectedIndex();
            if (selectedIndex != -1) {
                Student student = studentList.get(selectedIndex);
                nameField.setText(student.getName());
                idField.setText(student.getId());
                emailField.setText(student.getEmail());
            } else {
                JOptionPane.showMessageDialog(frame, "Please select a student to view!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Set layout and add components to the frame
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Display the frame
        frame.setVisible(true);
    }

    // Helper method to clear text fields
    private static void clearFields(JTextField nameField, JTextField idField, JTextField emailField) {
        nameField.setText("");
        idField.setText("");
        emailField.setText("");
    }
}
