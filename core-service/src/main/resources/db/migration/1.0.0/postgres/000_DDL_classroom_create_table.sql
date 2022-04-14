-- Create classroom table
create table classroom (
   id smallserial primary key,
   code varchar(10) not null,
   size smallint not null,
   created_at timestamp,
   updated_at timestamp
);
