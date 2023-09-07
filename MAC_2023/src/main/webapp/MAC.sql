/*사용자와 관리자 정보*/
drop table member_table;

create table member_table(
id varchar(45) primary key,
grade Nvarchar(10) not null,
password varchar(256) not null,/*암호화된 비밀번호 저장위해*/
/*password varchar(256) not null,*//*암호화된 비밀번호 저장위해 : 비밀번호 찾기(복호화 안되므로 '이메일'임시 비밀번호)*/
/*SHA256password varchar(256) not null,*//*암호화된 비밀번호 저장위해 : 비밀번호 찾기(복호화 안되므로 '이메일'임시 비밀번호)*/
name Nvarchar(20) not null,
email varchar(45) not null,
phone varchar(11) not null,/*11바이트 : 01012345678, 13바이트 010-1234-5678*/
temporary_password varchar(10) default 'NO',
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

/*3. 메뉴 정보 */
create table menu_table(
m_id Nvarchar(20) primary key,
category Nvarchar(50) not null,
m_name Nvarchar(45) not null,
m_price int not null,
m_detail Nvarchar(200),/*메뉴 설명*/
m_status varchar (1) not null,/*y: 판매가능, n: 판매불가*/
m_date date,/* 관리자가 메뉴 올린 날짜*/
image Nvarchar(100) not null
);

/*4. 주문 번호*/
drop table order_table;
create table order_table(
order_num int Auto_Increment primary key,/*주문번호*/
id varchar(45) not null,/*주문한 사용자의 id*/
email varchar(45) not null,/*회원 탈티 후 주문자를 구분하기 위해 추가함(처음부터 회원번호를 PK 로 했으면 제외시킴*/

order_date timestamp not null,/*주문한 날짜*/
order_status varchar(10) not null,/*사용자 모드 : 구매하기(order) , 관리자 모드 : 주문 승인(get) 주문취소(cancel)*/
totalmoney int not null/*주문한 총금액*/
);

/*5. 주문 상세 정보*/



/*6. 리뷰 정보*/
drop table review_table;
create table review_table(
review_num int auto_increment primary key,/*리뷰번호*/
u_id varchar(45) not null,/*리뷰 단 사용자 id*/
m_id Nvarchar(30) not null,/*메뉴id*/
rating int not null,/*평점*/
text Nvarchar(200)/*한줊평 적기는 null 허용*/
);

select * from menu_table;

select * from member_table where (id = ? ) and (password = ?)

select salerate from grade_table where grade = 'nomal';
insert into member_table(id, grade, password, name, email, phone) values();
insert into address_table values();
select * from address_table where id = ?;

select * from member_table;
update member_table set name= ? email= ? phone= ? where id = ? ;
update address_table set postcode = ? address1 = ? address2=? where id = ?
delete from member_table where id =?

update member_table set password = ? ,temporary_password = ? where id = ? and email = ?
delete from member_table where id =?
delete from member_table where id = '12345678';