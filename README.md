# Student-management-system-project
Description:

This Java application is a simple Student Management System that interacts with a MySQL database to perform basic operations related to student records. The system provides a graphical user interface (GUI) using Java's Swing library and utilizes JDBC to connect to a MySQL database.

**Key Features:**

1. **User Authentication:**
   - The system requires users to log in with a username and password. The default credentials are "admin" for the username and "password" for the password.

2. **Menu-Driven Interface:**
   - Users interact with the system through a menu-driven interface, providing a user-friendly experience.
   - Menu options include adding a student, viewing the student list, searching for a student, editing student information, deleting a student, and exiting the program.

3. **Database Connectivity:**
   - The application connects to a MySQL database hosted locally on the default port (3306).
   - The connection credentials used in the example are a username "root" with an empty password. Users should adjust these details based on their MySQL setup.

4. **Student Operations:**
   - **Adding a Student:**
     - Users can input details such as student ID, name, age, contact number, email, department, and course. The application validates input, including contact numbers and email addresses.
   - The information is then added to the database.

   - **Viewing Student List:**
     - Users can view a list of all students in the database, displaying details like ID, name, age, contact number, email, department, and course.

   - **Searching for a Student:**
     - Users can search for a student by entering either the student's first name or ID. The system performs a partial match on the first name.

   - **Editing Student Information:**
     - Users can edit an existing student's information by providing the student's ID. The application updates the database with the corrected details.

   - **Deleting a Student:**
     - Users can delete a student from the database by providing the student's ID.

5. **Error Handling:**
   - The application includes error handling for invalid input during student creation and login validation.
   - Database-related errors are caught and displayed in a dialog box.

**Usage:**
1. Execute the Java program.
2. Enter the username and password when prompted.
3. Choose from the menu options to perform various operations on student records.

**Note:**
- Users should customize the database connection details (URL, username, password) based on their MySQL setup.
- This system is designed for educational purposes and can be expanded for more advanced features and security enhancements.
