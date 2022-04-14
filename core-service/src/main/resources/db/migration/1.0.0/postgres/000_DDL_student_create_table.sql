-- Create student table
CREATE TABLE student (
  id serial PRIMARY KEY,
  name VARCHAR (255) NOT NULL,
  gender VARCHAR (10) NOT NULL,
  date_of_birth DATE NOT NULL,
  parent_name VARCHAR (255) NOT NULL,
  group_type smallint NOT NULL,
  classroom_id smallint NULL,
  created_at TIMESTAMP NOT NULL,
  updated_at TIMESTAMP NULL,
  constraint st_cl foreign key(classroom_id) REFERENCES classroom(id)
);