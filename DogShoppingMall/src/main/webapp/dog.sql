/*
 * (예) 비교를 위해 "임초롱초롱빛나리"를 name1, name2 로 저장하면
 * 1. name1 varchar2(20) : 길이의 개수가 "바이트 개수"
 * 		-> 한글 8자리 *3 = 24byte이므로 저장 불가
 * 2. name2 nvarchar2(20) : 길이의 개수가 "글자 개수"
 */

drop table dog;
drop sequence dog_seq;

create table dog (
	id number primary key, --상품아이디: PL= unique+no null (MYsql : auto_increment 속성으로 자동 1증가 )
	kind nvarchar2(20) not null unique, --개품종:unique 자동 index 생성
	country nvarchar2(20) not null,
	price number not null,
	height number,
	weight number,
	content nvarchar2(400), 
	image nvarchar2(40) not null, 
	readcount number
);
--오라클에서는 직접 sequence 생성하여 사용함 
create sequence dog_seq;
	
insert into dog values(dog_seq.nextval,'푸들','프랑스',1000,1,20 ,'털많다.','pu.jpg',0);
insert into dog values(dog_seq.nextval,'불독','독일',2000,1,20 ,'못생겼다.','bul.jpg',0);
insert into dog values(dog_seq.nextval,'진도개','대한민국',3000,1,20 ,'최고다.','jin.jpg',0);
insert into dog values(dog_seq.nextval,'허스키','북극',4000,1,20 ,'멋지다.','h.jpg',0);

commit --주의 ; 넣으면 오류

select * from dog;
---------------------------------------------------------------------------------------------
/*MYSQL*/
create table dog (
	id int auto_increment primary key, --상품아이디 자동 1증가
	kind nvarchar2(20) not null,
	country nvarchar2(20) not null,
	price int not null,
	height int,/*원산지*/
	weight int,/*평균 개 신장*/
	content nvarchar2(400), /*평균 개 체중*/
	image nvarchar2(40) not null,/*개설명*/
	readcount int/*조회수*/
);
()
insert into dog(kind,country,price,height,weight,content,image,readcount) values('푸들','프랑스',1000,1,20 ,'털많다.','pu.jpg',0);
insert into dog(kind,country,price,height,weight,content,image,readcount) values('불독','독일',2000,1,20 ,'못생겼다.','bul.jpg',0);
insert into dog(kind,country,price,height,weight,content,image,readcount) values('진도개','대한민국',3000,1,20 ,'최고다.','jin.jpg',0);
insert into dog(kind,country,price,height,weight,content,image,readcount) values('허스키','북극',4000,1,20 ,'멋지다.','h.jpg',0);

commit --주의 ; 넣으면 오류

update dog
set readcount = readcount + 1
where id = 1;

select * from dog;

select *
from dog
where id = ?;

