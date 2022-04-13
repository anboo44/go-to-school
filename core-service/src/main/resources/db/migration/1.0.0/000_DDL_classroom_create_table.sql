-- Create classroom table
create table classroom (
   id int auto_increment primary key,
   code varchar(10) not null,
   size int not null,
   teacher_id int null,
   created_at timestamp not null default current_timestamp,
   updated_at timestamp not null default current_timestamp on update current_timestamp,
   foreign key (teacher_id) references teacher (id)
) engine=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;
