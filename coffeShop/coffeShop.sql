create sequence member_tbl_seq
start with 10001
increment by 1
minvalue 10001;

drop table member_tbl;

create table member_tbl(custno number not null primary key ,
custname varchar(20) , gender char(1) , phone varchar2(13) ,
address varchar2(60), joindate date , grade char(1) , city char(2));

alter table member_tbl(custno s)

INSERT INTO member_tbl values(member_tbl_seq.nextval,'김행복','F','010-1111-2222','서울 동대문구 휘경1동','2015-12-02','A','01');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'이축복','M','010-1111-3333','서울 동대문구 휘경2동','2015-12-06','B','01');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'장믿음','F','010-1111-4444','울릉군 울릉읍 독도1리','2015-10-01','B','30');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'최사랑','M','010-1111-5555','울릉군 울릉읍 독도2리','2015-11-13','A','30');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'진평화','F','010-1111-6666','제주도 제주시 외나무골','2015-12-25','B','60');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'차공단','M','010-1111-7777','제주도 제주시 외나무골','2015-12-11','C','60');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'전진주','M','010-1111-8888','대구 수성구 고산1동','2015-11-03','A','90');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'중고차','M','010-1111-9999','대구 수성구 고산2동','2015-09-23','A','90');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'상수리','F','010-1111-0000','대구 수성구 고산3동','2015-12-03','B','90');
INSERT INTO member_tbl values(member_tbl_seq.nextval,'외다리','F','010-1111-1111','대구 수성구 사월동','2015-10-30','C','90');

create table sale_tbl(custno number not null,
saleno number not null primary key,
amount number ,
price number,
pcode varchar2(4),
sdate date );

INSERT INTO sale_tbl values(10001,1001,5,15000,'A001','19/01/01');
INSERT INTO sale_tbl values(10002,1002,5,20000,'A002','19/01/02');
INSERT INTO sale_tbl values(10003,1003,5,30000,'A003','19/01/03');
INSERT INTO sale_tbl values(10004,1004,4,20000,'A004','19/01/04');
INSERT INTO sale_tbl values(10005,1005,5,15000,'A001','19/01/05');
INSERT INTO sale_tbl values(10006,1006,1,4000,'A002','19/01/06');
INSERT INTO sale_tbl values(10001,1007,2,12000,'A003','19/01/07');
INSERT INTO sale_tbl values(10002,1008,3,15000,'A004','19/01/08');
INSERT INTO sale_tbl values(10003,1009,4,12000,'A001','19/01/09');
INSERT INTO sale_tbl values(10004,1010,5,20000,'A002','19/01/10');
INSERT INTO sale_tbl values(10005,1011,6,36000,'A003','19/01/11');
INSERT INTO sale_tbl values(10006,1012,7,35000,'A004','19/01/12');

drop table coffee_tbl;

create table coffee_tbl(pcode varchar(4) not null primary key,
pname varchar2(60) not null ,
pcost number);

/*TODO 테이블 2개 테이블 만들기*/

INSERT INTO coffee_tbl values('A001','아메리카노', 3000);
INSERT INffee_tbl values('A002','카푸치노', 4000);
INSERT INTO coffee_tbl values('A003','카페모카', 6000);
INSERT INTO coffee_tbl values('A004','카라멜마끼야또', 5000);


select nvl(max(custno), 100001)+1 to_char(sysdate,'yyyy-mm-dd') from member_tbl;

select custno, custname , decode(gender ,'M' , '남자'	,'F','여자') as gender, phone , address , to_char(joindate , 'YYYY-MM-DD'), decode(grade, 'A' ,'VIP' ,'B','일반', 'C','직원') as grade, city from member_tbl;
select custno, custname , gender, phone , address , to_char(joindate , 'YYYY-MM-DD'), grade, city from member_tbl where custno = ?;

select '아이스 아메리카노' , '3500' , '0' ,'' from dual ;
select pname , pcost , sum(amount) as amount , pcost*sum(amount) as totalSum from sale_tbl sale join coffee_tbl coffe on sale.pcode = coffe.pcode group by pname , pcost union  select '아이스 아메리카노' , 3500 , 0 , pcost*sum(amount) from dual order by totalSum desc; 
select pname , pcost , sum(amount) as amount , pcost*sum(amount) as totalSum from sale_tbl sale join coffee_tbl coffe on sale.pcode = coffe.pcode group by pname , pcost union  select '아이스 아메리카노' , 3500 , 0 , pcost*sum(amount) from dual order by totalSum desc; 