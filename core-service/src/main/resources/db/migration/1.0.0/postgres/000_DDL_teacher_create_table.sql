-- Create teacher table
create table teacher (
   id serial primary key,
   age smallint not null,
   name varchar(255) not null,
   work_start_date date not null,
   classroom_id smallint null,
   created_at timestamp,
   updated_at timestamp,
   constraint te_cl foreign key(classroom_id) REFERENCES classroom(id)
);
