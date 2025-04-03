desc members;
desc study_records;

insert into members values (1, 'admin', '관리자' , 'admin' ,'ADMIN');
insert into members values (2, 'test1', '테스트1' , '1234' ,'STUDENT');
insert into members values (3, 'test2', '테스트2' , '1234' ,'STUDENT');
insert into members values (4, 'test3', '테스트3' , '1234' ,'STUDENT');
insert into members values (5, 'test4', '테스트4' , '1234' ,'STUDENT');



insert into study_records values(  CURTIME() ,CURDATE() , 30, 2 , 1 ,'공부기록1');
insert into study_records values(  CURTIME() ,CURDATE() , 150, 3 , 2 ,'공부기록2');
insert into study_records values(  CURTIME() ,CURDATE() , 10, 2 , 3 ,'공부기록3');
insert into study_records values(  CURTIME() ,CURDATE() , 5, 4 , 4 ,'공부기록4');


select * from members;
select * from study_records;


desc members;

