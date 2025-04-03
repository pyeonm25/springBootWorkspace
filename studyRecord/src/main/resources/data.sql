desc members;
desc study_records;

insert into members values (null, 'admin', '관리자' , 'admin' ,'ADMIN');
insert into members values (null, 'test1', '테스트1' , '1234' ,'STUDENT');
insert into members values (null, 'test2', '테스트2' , '1234' ,'STUDENT');
insert into members values (null, 'test3', '테스트3' , '1234' ,'STUDENT');
insert into members values (null, 'test4', '테스트4' , '1234' ,'STUDENT');



insert into study_records values(  CURTIME() ,CURDATE() , 30, 2 , null ,'공부기록1');
insert into study_records values(  CURTIME() ,CURDATE() , 150, 3 , null ,'공부기록2');
insert into study_records values(  CURTIME() ,CURDATE() , 10, 2 , null ,'공부기록3');
insert into study_records values(  CURTIME() ,CURDATE() , 5, 4 , null ,'공부기록4');


select * from members;
select * from study_records;