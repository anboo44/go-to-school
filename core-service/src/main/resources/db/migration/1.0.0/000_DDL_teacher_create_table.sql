-- Create teacher table
create table teacher (
   id int auto_increment primary key,
   age int not null,
   name varchar(255) not null,
   work_start_date date not null,
   created_at timestamp not null default current_timestamp,
   updated_at timestamp not null default current_timestamp on update current_timestamp
) engine = InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
