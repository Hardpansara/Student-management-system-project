import java.sql.*;
import javax.swing.JOptionPane;

public class Home {
    public static void main(String[] args) throws Exception {

        // Prompting the user for username and password
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");

        // Validating login credentials
        if (validateLogin(username, password)) {
            try {
                // Setting up database connection
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");

                // Menu-driven student management system
                int choice = 0;
                while (choice != 6) {
                    // Displaying menu options
                    String choiceString = JOptionPane.showInputDialog(
                            "Student Management System\n"
                                    + "1. Add Student\n"
                                    + "2. View Student List\n"
                                    + "3. Search Student\n"
                                    + "4. Edit Student\n"
                                    + "5. Delete Student\n"
                                    + "6. Exit\n\n"
                                    + "Enter your choice:");

                    choice = Integer.parseInt(choiceString);

                    switch (choice) {
                        case 1: {
                            // Adding a new student
                            int id = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID:"));
                            String firstName = JOptionPane.showInputDialog("Enter Student First Name:");
                            String middleName = JOptionPane.showInputDialog("Enter Student Middle Name:");
                            String lastName = JOptionPane.showInputDialog("Enter Student Last Name:");
                            int age = Integer.parseInt(JOptionPane.showInputDialog("Enter Student Age:"));

                            // Validating contact number
                            boolean contactNumberFlag = false;
                            int contactNumber = 0;
                            while (!contactNumberFlag) {
                                String contactNumberStr = JOptionPane.showInputDialog("Enter Student Contact Number:");
                                contactNumber = Integer.parseInt(contactNumberStr);

                                if (String.valueOf(contactNumber).length() == 10) {
                                    contactNumberFlag = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid Number!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            // Validating email address
                            boolean mailCheck = false;
                            String email = "";
                            while (!mailCheck) {
                                email = JOptionPane.showInputDialog("Enter Email Address:");

                                if (email.contains("@")) {
                                    mailCheck = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid Email Address!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            // Adding the student to the database
                            String department = JOptionPane.showInputDialog("Enter Department:");
                            String course = JOptionPane.showInputDialog("Enter Course");

                            addStudent(connection, id, firstName, middleName, lastName, age, contactNumber, email, department, course);
                            break;
                        }
                        case 2: {
                            // Viewing the list of all students
                            viewStudentList(connection);
                            break;
                        }
                        case 3: {
                            // Searching for a student by name or ID
                            String searchQuery = JOptionPane.showInputDialog("Enter Student First Name or ID to search:");
                            searchStudent(connection, searchQuery);
                            break;
                        }
                        case 4: {
                            // Editing student information
                            int editId = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID to edit:"));
                            String editFirstName = JOptionPane.showInputDialog("Enter Corrected First Name:");
                            String editMiddleName = JOptionPane.showInputDialog("Enter Corrected Middle Name:");
                            String editLastName = JOptionPane.showInputDialog("Enter Corrected Last Name:");
                            int editAge = Integer.parseInt(JOptionPane.showInputDialog("Enter Corrected Age:"));

                            // Validating corrected contact number
                            boolean editContactNumberFlag = false;
                            int editContactNumber = 0;
                            while (!editContactNumberFlag) {
                                String editContactNumberStr = JOptionPane.showInputDialog("Enter corrected Contact Number:");
                                editContactNumber = Integer.parseInt(editContactNumberStr);

                                if (String.valueOf(editContactNumber).length() == 10) {
                                    editContactNumberFlag = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid Number!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            // Validating corrected email address
                            boolean editMailCheck = false;
                            String editEmail = "";
                            while (!editMailCheck) {
                                editEmail = JOptionPane.showInputDialog("Enter Corrected Email Address:");

                                if (editEmail.contains("@")) {
                                    editMailCheck = true;
                                } else {
                                    JOptionPane.showMessageDialog(null, "Invalid Email Address!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }

                            // Updating the student information in the database
                            String editDepartment = JOptionPane.showInputDialog("Enter Corrected Department:");
                            String editCourse = JOptionPane.showInputDialog("Enter Corrected Course");

                            editStudent(connection, editId, editFirstName, editMiddleName, editLastName, editAge, editContactNumber, editEmail, editDepartment, editCourse);
                            break;
                        }
                        case 5: {
                            // Deleting a student from the database
                            int deleteId = Integer.parseInt(JOptionPane.showInputDialog("Enter Student ID to delete:"));
                            deleteStudent(connection, deleteId);
                            break;
                        }
                        case 6: {
                            // Exiting the program
                            JOptionPane.showMessageDialog(null, "Exiting the program.");
                            connection.close();
                            System.exit(0);
                        }
                        default: {
                            // Handling invalid choice
                            JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                // Handling database-related errors
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Handling invalid login credentials
            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to validate login credentials
    private static boolean validateLogin(String username, String password) {
        return "admin".equals(username) && "password".equals(password);
    }

    // Method to add a new student to the database
    private static void addStudent(Connection connection, int id, String firstName, String middleName, String lastName, int age, int contactNumber, String email, String department, String course) throws SQLException {
        String query = "INSERT INTO student (id, FirstName, MiddleName, LastName, Age, ContactNumber, Email, Department, Course) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, firstName);
        preparedStatement.setString(3, middleName);
        preparedStatement.setString(4, lastName);
        preparedStatement.setInt(5, age);
        preparedStatement.setInt(6, contactNumber);
        preparedStatement.setString(7, email);
        preparedStatement.setString(8, department);
        preparedStatement.setString(9, course);
        preparedStatement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to view the list of all students in the database
    private static void viewStudentList(Connection connection) throws SQLException {
        String query = "SELECT * FROM student";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        StringBuilder studentList = new StringBuilder();

        while (resultSet.next()) {
            studentList.append("\n\n");
            studentList.append("ID: ").append(resultSet.getInt("id")).append("\n");
            studentList.append("Name: ").append(resultSet.getString("FirstName")).append(" ")
                    .append(resultSet.getString("MiddleName")).append(" ").append(resultSet.getString("LastName")).append("\n");
            studentList.append("Age: ").append(resultSet.getInt("Age")).append("\n");
            studentList.append("Contact Number: ").append(resultSet.getInt("ContactNumber")).append("\n");
            studentList.append("Email: ").append(resultSet.getString("Email")).append("\n");
            studentList.append("Department: ").append(resultSet.getString("Department")).append("\n");
            studentList.append("Course: ").append(resultSet.getString("Course")).append("\n");
            studentList.append("-----------------------\n\n");
        }

        if (studentList.length() == 0) {
            studentList.append("No students found.");
        }

        JOptionPane.showMessageDialog(null, studentList.toString(), "Student List", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to search for a student in the database
    private static void searchStudent(Connection connection, String searchQuery) throws SQLException {
        String query = "SELECT * FROM student WHERE id = ? OR FirstName LIKE ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int id;

        try {
            id = Integer.parseInt(searchQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "%" + searchQuery + "%");
        } catch (NumberFormatException e) {
            id = -1;
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, "%" + searchQuery + "%");
        }

        ResultSet resultSet = preparedStatement.executeQuery();
        StringBuilder searchResult = new StringBuilder();

        while (resultSet.next()) {
            searchResult.append("\n\n");
            searchResult.append("ID: ").append(resultSet.getInt("id")).append("\n");
            searchResult.append("Name: ").append(resultSet.getString("FirstName")).append(" ")
                    .append(resultSet.getString("MiddleName")).append(" ").append(resultSet.getString("LastName")).append("\n");
            searchResult.append("Age: ").append(resultSet.getInt("Age")).append("\n");
            searchResult.append("Contact Number: ").append(resultSet.getInt("ContactNumber")).append("\n");
            searchResult.append("Email: ").append(resultSet.getString("Email")).append("\n");
            searchResult.append("Department: ").append(resultSet.getString("Department")).append("\n");
            searchResult.append("Course: ").append(resultSet.getString("Course")).append("\n");
            searchResult.append("-----------------------");
        }

        if (searchResult.length() == 0) {
            searchResult.append("No matching students found.");
        }

        JOptionPane.showMessageDialog(null, searchResult.toString(), "Search Result", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to edit the information of an existing student in the database
    private static void editStudent(Connection connection, int id, String editFirstName, String editMiddleName, String editLastName, int editAge, int editContactNumber, String editEmail, String editDepartment, String editCourse) throws SQLException {
        String query = "UPDATE student SET FirstName = ?, MiddleName = ?, LastName = ?, Age = ?, ContactNumber = ?, Email = ?, Department = ?, Course = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, editFirstName);
        preparedStatement.setString(2, editMiddleName);
        preparedStatement.setString(3, editLastName);
        preparedStatement.setInt(4, editAge);
        preparedStatement.setInt(5, editContactNumber);
        preparedStatement.setString(6, editEmail);
        preparedStatement.setString(7, editDepartment);
        preparedStatement.setString(8, editCourse);
        preparedStatement.setInt(9, id);

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Student updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Student with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method to delete a student from the database
    private static void deleteStudent(Connection connection, int id) throws SQLException {
        String query = "DELETE FROM student WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);

        int rowsDeleted = preparedStatement.executeUpdate();
        if (rowsDeleted > 0) {
            JOptionPane.showMessageDialog(null, "Student with ID " + id + " deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Student with ID " + id + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
