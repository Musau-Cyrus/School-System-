package model;

public class Class {
    private String classId;
    private String className;
    private String classTeacher;

    public Class(String classId, String className, String classTeacher) {
        this.classId = classId;
        this.className = className;
        this.classTeacher = classTeacher;
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public String getClassTeacher() {
        return classTeacher;
    }
}