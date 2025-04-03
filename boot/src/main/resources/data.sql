insert into users values ('test1@gmail.com','1234','test1');
insert into users values ('test2@gmail.com','1234','test2');
insert into users values ('test3@gmail.com','1234','test3');
insert into users values ('test4@gmail.com','1234','test4');

insert into user_authenticate_entity values(1,'ADMIN','test1','test1' );
insert into user_authenticate_entity values(1,'MANAGER','test1','test1' );
insert into user_authenticate_entity values(1,'USER','test1','test1' );
insert into user_authenticate_entity values(1,'USER','test2','test2' );
insert into user_authenticate_entity values(1,'USER','test3','test3' );
insert into user_authenticate_entity values(1,'MANAGER','test3','test3' );
desc users;