/*사용자와 관리자 정보*/
drop table member_table
create table member_table(
id varchar(45) primary key,
grade Nvarchar(10) not null,
password varchar(256) not null,/*암호화된 비밀번호 저장위해*/
/*password varchar(256) not null,*//*암호화된 비밀번호 저장위해 : 비밀번호 찾기(복호화 안되므로 '이메일'임시 비밀번호)*/
/*SHA256password varchar(256) not null,*//*암호화된 비밀번호 저장위해 : 비밀번호 찾기(복호화 안되므로 '이메일'임시 비밀번호)*/
name Nvarchar(20) not null,
email varchar(45) not null,
phone varchar(11) not null,/*11바이트 : 01012345678, 13바이트 010-1234-5678*/
joindate timestamp default now()/*timestamp(같은지역은 같은시간대 사용위해) , now() : 오라클의 sysdate와 같다.*/ 
);

/*사용자와 관리자 '주소'*/
drop table address_table;
create table address_table(
addr_index int auto_increment primary key,/*1부터 자동 1증가*/
id varchar(45) not null references member_table,/*참조키 무결성제약조건 : 회원탈퇴시 address 삭제*/
postcode int not null,/*우편번호*/
address1 nvarchar(60) not null,
address2 Nvarchar(60) not null
);

/*등급별 세일율 -> 등급별 포인트별로 수정하여 사용해도 됨*/
drop table grade_table
create table grade_table(
	grade varchar(15) primary key,
	salerate int not null
);

insert into grade_table values('nomal',0);/*0% discount*/
insert into grade_table values('GOLD',5); /*지난달 구매금액이 50,000 이상이면 5% discount*/
insert into grade_table values('VIP',10); /*10% 100,000이상이면 discount*/

insert into grade_table values('ADMIN',0);

select * from member_table where (id = ? ) and (password = ?)

select salerate from grade_table where grade = 'nomal';
insert into member_table(id, grade, password, name, email, phone) values();
insert into address_table values();
select * from address_table where id = ?;

select * from member_table;