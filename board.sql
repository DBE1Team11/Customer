create table board (
	bno int auto_increment primary key,
    title varchar(45) not null,
    content text not null,
    writer varchar(30) not null,
    view_cnt int default 0 null,
    comment_cnt int default 0 null,
    reg_date datetime null,
    up_date datetime null
);
drop table board;
select * from board;
SELECT count(*) FROM board;
delete from board where bno = 39 and writer = 'asdf';

insert into 
board(title,content,writer)
values("hi1","hi2","hi3");