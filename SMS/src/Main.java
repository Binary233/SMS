import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManagementSystem system = new StudentManagementSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("请选择要执行的操作:");
            System.out.println("1. 添加学生信息");
            System.out.println("2. 查询学生信息");
            System.out.println("3. 更新学生信息");
            System.out.println("4. 查询所有学生信息");
            System.out.println("5. 根据关键字查询学生信息");
            System.out.println("6. 删除学生信息");
            System.out.println("7. 备份学生信息");
            System.out.println("8. 导入学生信息");
            System.out.println("9. 导出学生信息");
            System.out.println("0. 退出程序");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // 添加学生信息
                    System.out.println("请输入学生信息：");
                    System.out.print("学号: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("姓名: ");
                    String name = scanner.nextLine();
                    System.out.print("年龄: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("性别: ");
                    String gender = scanner.nextLine();
                    System.out.print("出生日期 (yyyy-MM-dd): ");
                    String birthdateStr = scanner.nextLine();
                    Date birthdate = parseDate(birthdateStr);
                    System.out.print("地址: ");
                    String address = scanner.nextLine();
                    System.out.print("电话: ");
                    String phone = scanner.nextLine();
                    System.out.print("电子邮件: ");
                    String email = scanner.nextLine();
                    System.out.print("学院: ");
                    String college = scanner.nextLine();

                    Student student = new Student(studentId, name, age, gender, birthdate, address, phone, email, college);
                    system.addStudent(student);
                    System.out.println("学生信息添加成功。");
                    break;
                case 2:
                    // 查询学生信息
                    System.out.print("请输入要查询的学生的学号: ");
                    int searchId = scanner.nextInt();
                    Student retrievedStudent = system.getStudentById(searchId);
                    if (retrievedStudent != null) {
                        System.out.println("学生信息:");
                        System.out.println("学号: " + retrievedStudent.getStudentId());
                        System.out.println("姓名: " + retrievedStudent.getName());
                        System.out.println("年龄: " + retrievedStudent.getAge());
                        System.out.println("性别: " + retrievedStudent.getGender());
                        System.out.println("出生日期: " + retrievedStudent.getBirthdate());
                        System.out.println("地址: " + retrievedStudent.getAddress());
                        System.out.println("电话: " + retrievedStudent.getPhone());
                        System.out.println("电子邮件: " + retrievedStudent.getEmail());
                        System.out.println("学院: " + retrievedStudent.getCollege());
                    } else {
                        System.out.println("未找到学生信息。");
                    }
                    break;
                case 3:
                    // 更新学生信息
                    System.out.print("请输入要更新的学生的学号: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine();
                    Student studentToUpdate = system.getStudentById(updateId);
                    if (studentToUpdate != null) {
                        System.out.println("请输入更新后的学生信息：");
                        System.out.print("姓名: ");
                        String updatedName = scanner.nextLine();
                        System.out.print("年龄: ");
                        int updatedAge = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("性别: ");
                        String updatedGender = scanner.nextLine();
                        System.out.print("出生日期 (yyyy-MM-dd): ");
                        String updatedBirthdateStr = scanner.nextLine();
                        Date updatedBirthdate = parseDate(updatedBirthdateStr);
                        System.out.print("地址: ");
                        String updatedAddress = scanner.nextLine();
                        System.out.print("电话: ");
                        String updatedPhone = scanner.nextLine();
                        System.out.print("电子邮件: ");
                        String updatedEmail = scanner.nextLine();
                        System.out.print("学院: ");
                        String updatedCollege = scanner.nextLine();

                        studentToUpdate.setName(updatedName);
                        studentToUpdate.setAge(updatedAge);
                        studentToUpdate.setGender(updatedGender);
                        studentToUpdate.setBirthdate(updatedBirthdate);
                        studentToUpdate.setAddress(updatedAddress);
                        studentToUpdate.setPhone(updatedPhone);
                        studentToUpdate.setEmail(updatedEmail);
                        studentToUpdate.setCollege(updatedCollege);

                        system.updateStudent(studentToUpdate);
                        System.out.println("学生信息更新成功。");
                    } else {
                        System.out.println("未找到要更新的学生信息。");
                    }
                    break;
                case 4:
                    // 查询所有学生信息
                    List<Student> allStudents = system.getAllStudents();
                    System.out.println("所有学生信息:");
                    for (Student s : allStudents) {
                        System.out.println("学号: " + s.getStudentId() + ", 姓名: " + s.getName());
                    }
                    break;
                case 5:
                    // 根据关键字查询学生信息
                    System.out.print("请输入要查询的关键字: ");
                    String keyword = scanner.nextLine();
                    List<Student> searchResults = system.searchStudents(keyword);
                    System.out.println("查询结果:");
                    for (Student s : searchResults) {
                        System.out.println("学号: " + s.getStudentId() + ", 姓名: " + s.getName());
                    }
                    break;
                case 6:
                    // 删除学生信息
                    System.out.print("请输入要删除的学生的学号: ");
                    int deleteId = scanner.nextInt();
                    scanner.nextLine();
                    boolean deleteSuccess = system.deleteStudent(deleteId);
                    if (!deleteSuccess) {
                        System.out.println("未找到要删除的学生信息。");
                    } else {
                        System.out.println("学生信息删除成功。");
                    }
                    break;
                case 7:
                    // 备份学生信息
                    System.out.print("请输入备份文件名: ");
                    String backupFilename = scanner.nextLine();
                    system.backupData(backupFilename);
                    System.out.println("学生信息备份成功。");
                    break;
                case 8:
                    // 导入学生信息
                    System.out.print("请输入要导入的文件名: ");
                    String importFilename = scanner.nextLine();
                    system.importData(importFilename);
                    System.out.println("学生信息导入成功。");
                    break;
                case 9:
                    // 导出学生信息
                    System.out.print("请输入导出文件名: ");
                    String exportFilename = scanner.nextLine();
                    system.exportData(exportFilename);
                    System.out.println("学生信息导出成功。");
                    break;
                case 0:
                    // 退出程序
                    System.out.println("程序已退出。");
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效的选项，请重新选择。");
                    break;
            }

            System.out.println(); // 打印空行，以提升可读性
        }
    }

    // 解析日期字符串为Date对象
    private static Date parseDate(String dateStr) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}