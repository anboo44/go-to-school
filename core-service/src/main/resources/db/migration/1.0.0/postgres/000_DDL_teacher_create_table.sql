-- Create teacher table
create table teacher (
   id serial primary key,
   age smallint not null,
   name varchar(255) not null,
   work_start_date date not null,
   created_at timestamp,
   updated_at timestamp
);
