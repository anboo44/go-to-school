-- Create student table
Create table student (
  id int auto_increment primary key,
  name varchar(255) not null,
  gender varchar(10) not null,
  date_of_birth date not null,
  parent_name varchar(255) not null,
  group_type varchar(10) not null
) DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;