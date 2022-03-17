insert into article(id, title, contents) values(1, 'aaa','111');
insert into article(id, title, contents) values(2, 'bbb','111');
insert into article(id, title, contents) values(3, 'ccc','111');

-- article 더미 데이터
insert into article(id, title, contents) values(4, 'movie?','commentgo');
insert into article(id, title, contents) values(5, 'hi?','123');
insert into article(id, title, contents) values(6, 'bye?','321');

-- comment 더미 데이터
--- 4번 게시글의 댓글들
insert into comment(id, article_id, nickname, body) values(1, 4, 'Park','good');
insert into comment(id, article_id, nickname, body) values(2, 4, 'Kim','goood');
insert into comment(id, article_id, nickname, body) values(3, 4, 'Choi','goood');

--- 5번 게시글의 댓글들
insert into comment(id, article_id, nickname, body) values(4, 5, 'Park','goodaadsfadsf');
insert into comment(id, article_id, nickname, body) values(5, 5, 'Kim','gasdfadsfadfoood');
insert into comment(id, article_id, nickname, body) values(6, 5, 'Choi','goadsfood');

--- 6번 게시글의 댓글들
insert into comment(id, article_id, nickname, body) values(7, 6, 'Park','gooq32414d ㄱ');
insert into comment(id, article_id, nickname, body) values(8, 6, 'Kim','ㄱgooo1241dㄱㄱ');
insert into comment(id, article_id, nickname, body) values(9, 6, 'Choi','ㄱg5124123oood');
