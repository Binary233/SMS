import java.util.Date;

public class Student {
    private int studentId;
    private String name;
    private int age;
    private String gender;
    private Date birthdate;
    private String address;
    private String phone;
    private String email;
    private String college;

    public Student() {
    }

    public Student(int studentId, String name, int age, String gender, Date birthdate, String address, String phone, String email, String college) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.birthdate = birthdate;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.college = college;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", college='" + college + '\'' +
                '}';
    }
}