import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystem {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=StudentDB;encrypt=false";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "341122";

    // 添加学生信息
    public void addStudent(Student student) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "INSERT INTO students (student_id, name, age, gender, birthdate, address, phone, email, college) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, student.getStudentId());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getAge());
            statement.setString(4, student.getGender());
            statement.setDate(5, new java.sql.Date(student.getBirthdate().getTime()));
            statement.setString(6, student.getAddress());
            statement.setString(7, student.getPhone());
            statement.setString(8, student.getEmail());
            statement.setString(9, student.getCollege());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除学生信息
    public boolean deleteStudent(int studentId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "DELETE FROM students WHERE student_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, studentId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 更新学生信息
    public void updateStudent(Student student) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "UPDATE students SET name = ?, age = ?, gender = ?, birthdate = ?, address = ?, phone = ?, email = ?, college = ? WHERE student_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getGender());
            statement.setDate(4, new java.sql.Date(student.getBirthdate().getTime()));
            statement.setString(5, student.getAddress());
            statement.setString(6, student.getPhone());
            statement.setString(7, student.getEmail());
            statement.setString(8, student.getCollege());
            statement.setInt(9, student.getStudentId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 根据学号查询学生信息
    public Student getStudentById(int studentId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM students WHERE student_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, studentId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setBirthdate(resultSet.getDate("birthdate"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setCollege(resultSet.getString("college"));
                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 查询所有学生信息
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM students";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setBirthdate(resultSet.getDate("birthdate"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setCollege(resultSet.getString("college"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 根据姓名、作者名、学院查询学生信息
    public List<Student> searchStudents(String keyword) {
        List<Student> students = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT * FROM students WHERE name LIKE ? OR address LIKE ? OR college LIKE ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, "%" + keyword + "%");
            statement.setString(2, "%" + keyword + "%");
            statement.setString(3, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student();
                student.setStudentId(resultSet.getInt("student_id"));
                student.setName(resultSet.getString("name"));
                student.setAge(resultSet.getInt("age"));
                student.setGender(resultSet.getString("gender"));
                student.setBirthdate(resultSet.getDate("birthdate"));
                student.setAddress(resultSet.getString("address"));
                student.setPhone(resultSet.getString("phone"));
                student.setEmail(resultSet.getString("email"));
                student.setCollege(resultSet.getString("college"));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // 备份学生信息
    public void backupData(String filename) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             FileWriter writer = new FileWriter(filename)) {
            String sql = "SELECT * FROM students";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int studentId = resultSet.getInt("student_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Date birthdate = resultSet.getDate("birthdate");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String college = resultSet.getString("college");

                String studentInfo = String.format("%d,%s,%d,%s,%s,%s,%s,%s,%s",
                        studentId, name, age, gender, birthdate, address, phone, email, college);

                writer.write(studentInfo);
                writer.write("\n");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    // 导入学生信息
    public void importData(String filename) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] studentData = line.split(",");
                if (studentData.length == 9) {
                    int studentId = Integer.parseInt(studentData[0]);
                    String name = studentData[1];
                    int age = Integer.parseInt(studentData[2]);
                    String gender = studentData[3];
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date birthdate = dateFormat.parse(studentData[4]);
                    String address = studentData[5];
                    String phone = studentData[6];
                    String email = studentData[7];
                    String college = studentData[8];

                    Student student = new Student(studentId, name, age, gender, birthdate, address, phone, email, college);
                    addStudent(student);
                }
            }
        } catch (SQLException | IOException | ParseException e) {
            e.printStackTrace();
        }
    }


    // 导出学生信息
    public void exportData(String filename) {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             FileWriter writer = new FileWriter(filename)) {
            List<Student> students = getAllStudents();
            for (Student student : students) {
                String studentInfo = String.format("%d,%s,%d,%s,%s,%s,%s,%s,%s",
                        student.getStudentId(), student.getName(), student.getAge(),
                        student.getGender(), student.getBirthdate(), student.getAddress(),
                        student.getPhone(), student.getEmail(), student.getCollege());

                writer.write(studentInfo);
                writer.write("\n");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}