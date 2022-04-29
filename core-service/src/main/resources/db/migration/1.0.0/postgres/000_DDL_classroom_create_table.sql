-- Create classroom table
create table classroom (
   id smallserial primary key,
   code varchar(10) not null unique,
   classroom_size smallint not null,
   teacher_id smallint null,
   created_at timestamp,
   updated_at timestamp,
   constraint classroom_teacher foreign key(teacher_id) REFERENCES teacher(id)
);
