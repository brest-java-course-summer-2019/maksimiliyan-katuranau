DROP TABLE IF EXISTS course;
CREATE TABLE course
(
    course_id   int(11)     NOT NULL AUTO_INCREMENT,
    course_name varchar(45) NOT NULL UNIQUE,
    date        datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    teacher     varchar(45) NOT NULL,
    PRIMARY KEY (course_id),
);

DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    student_id int(11)     NOT NULL AUTO_INCREMENT,
    first_name varchar(45) NOT NULL,
    last_name  varchar(45) NOT NULL,
    age        int(11)     NOT NULL,
    PRIMARY KEY (student_id)
);

DROP TABLE IF EXISTS student_course;
CREATE TABLE student_course
(
    student_id int(11) NOT NULL,
    course_id  int(11) NOT NULL,
    PRIMARY KEY (student_id, course_id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course (course_id)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT fk_student FOREIGN KEY (student_id) REFERENCES student (student_id)
        ON DELETE CASCADE ON UPDATE NO ACTION
);