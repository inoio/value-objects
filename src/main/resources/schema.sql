-- create sequence person_seq;
create table person(
    id int auto_increment primary key,
    name varchar not null,
    age int not null
);

