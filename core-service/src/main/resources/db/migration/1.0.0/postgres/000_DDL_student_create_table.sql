-- Create student table
CREATE TABLE gts_student (
  id serial PRIMARY KEY,
  name VARCHAR (255) NOT NULL,
  gender VARCHAR (10) NOT NULL,
  date_of_birth DATE NOT NULL,
  parent_name VARCHAR (255) NOT NULL,
  group_type smallint NOT NULL,
  classroom_id smallint NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NULL,
  constraint fk_student_classroom foreign key(classroom_id) REFERENCES gts_classroom(id)
);