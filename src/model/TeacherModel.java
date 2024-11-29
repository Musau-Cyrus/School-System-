package model;

public class TeacherModel {
    private String id;
    private String name;
    private String subject;
    private String contactInfo;
    private String teacherClass;

    public TeacherModel(String id, String name, String subject, String contactInfo) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.contactInfo = contactInfo;
        this.teacherClass = teacherClass;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getClassIncharge() {
        return teacherClass;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                ", teacherClass='" + teacherClass + '\'' +
                '}';
    }


}