DROP TABLE IF EXISTS class;
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS teacher;
DROP TABLE IF EXISTS login;


-- Creating Class Table
CREATE TABLE IF NOT EXISTS class (
    class_id VARCHAR(50) PRIMARY KEY,
    class_name VARCHAR(50) NOT NULL,
    class_teacher VARCHAR(50) NOT NULL
    );

-- Creating Student Table
CREATE TABLE IF NOT EXISTS student (
    student_id VARCHAR(50) PRIMARY KEY,
    student_name VARCHAR(50) NOT NULL,
    student_age INT NOT NULL,
    student_class VARCHAR(50) NOT NULL,
    contact_info VARCHAR(50) NOT NULL,
    );

-- Creating Teacher Table
CREATE TABLE IF NOT EXISTS teacher (
    teacher_id VARCHAR(50) PRIMARY KEY,
    teacher_name VARCHAR(50) NOT NULL,
    teacher_email VARCHAR(50) NOT NULL,
    contact_info VARCHAR(50) NOT NULL,
    teacher_class VARCHAR(50) NOT NULL,
    );

-- Create Login Table
CREATE TABLE IF NOT EXISTS login (
    Role VARCHAR(50) NOT NULL,
    Username VARCHAR(50) PRIMARY KEY,
    Password VARCHAR(50) NOT NULL
    );