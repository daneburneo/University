create table courses
(
    course_id          int         not null primary key auto_increment,
    course_name        varchar(25) not null,
    course_coordinator varchar(30) not null,
    course_level       varchar(10) not null,
    course_director    varchar(20) not null
);

create table disciplines
(

    discipline_id   int         not null primary key auto_increment,
    discipline_name varchar(20) not null

);

create table students
(

    student_id        int         not null primary key auto_increment,
    student_firstname varchar(20) not null,
    student_lastname  varchar(20) not null,
    student_ssn       varchar(20) not null
);

create table teachers
(

    teacher_id         int         not null primary key auto_increment,
    teacher_firstname  varchar(20) not null,
    teacher_lastname   varchar(20) not null,
    teacher_ssn        int         not null,
    teacher_graduation varchar(20) not null,
    teacher_email      varchar(20) not null
);

create table courses_disciplines
(
    courses_disciplines_id integer not null primary key auto_increment,
    course_fk              integer not null,
    discipline_fk          integer not null,
    foreign key (course_fk) references courses (course_id),
    foreign key (discipline_fk) references disciplines (discipline_id)

);

create table courses_teachers
(
    courses_teachers_id integer not null primary key auto_increment,
    course_fk           integer not null,
    teacher_fk          integer not null,
    foreign key (course_fk) references courses (course_id),
    foreign key (teacher_fk) references teachers (teacher_id)

);

create table disciplines_teachers
(
    disciplines_teachers_id integer not null primary key auto_increment,
    discipline_fk           integer not null,
    teacher_fk              integer not null,
    foreign key (discipline_fk) references disciplines (discipline_id),
    foreign key (teacher_fk) references teachers (teacher_id)

);

create table disciplines_students
(

    disciplines_students_id integer not null primary key auto_increment,
    discipline_fk           integer not null,
    student_fk              integer not null,
    foreign key (discipline_fk) references disciplines (discipline_id),
    foreign key (student_fk) references students (student_id)

);

create table students_teachers
(

    students_teachers_id integer not null primary key auto_increment,
    student_fk           integer not null,
    teacher_fk           integer not null,
    foreign key (teacher_fk) references teachers (teacher_id),
    foreign key (student_fk) references students (student_id)

);

create table students_courses
(
    students_courses_id integer not null primary key auto_increment,
    student_fk           integer not null,
    course_fk           integer not null,
    foreign key (course_fk) references courses (course_id),
    foreign key (student_fk) references students (student_id)
);
