package model;

public class Student {
    private String id;
    private String name;
    private String age;
    private String studentClass;
    private String contactInfo;

    public Student(String id, String name, String age, String studentClass, String contactInfo) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.studentClass = studentClass;
        this.contactInfo = contactInfo;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public String getContactInfo() {
        return contactInfo;
    }
}