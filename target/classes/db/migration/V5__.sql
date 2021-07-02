
insert into member(mem_name,mem_id,login_id,level,uid,password)
values('bob',1,'admin','admin','432323223','$2a$12$hDGGoypX5MAt5LuBnzACreZ6PNPktCoFbuESLCAq5v74wp1w2IBw.');
insert into member(mem_id,login_id,level,uid,password)
values(277,'staff','staff','432121323223','$2a$12$hDGGoypX5MAt5LuBnzACreZ6PNPktCoFbuESLCAq5v74wp1w2IBw.');
insert into member(mem_id,login_id,level,uid,password)
values(39999,'member','member','43233232234223','$2a$12$hDGGoypX5MAt5LuBnzACreZ6PNPktCoFbuESLCAq5v74wp1w2IBw.');
insert into staff(sta_id,sta_name)values(277,'王哥');
insert into bigcategory(id,big_categoryname)values (100,'体育');
insert into bigcategory(id,big_categoryname)values (200,'艺术');
insert into bigcategory(id,big_categoryname)values (31,'科技');
insert into bigcategory(id,big_categoryname)values (52,'农业');
insert into bigcategory(id,big_categoryname)values (63,'医学');
insert into smallcategory(id,small_categoryname,big_categoryid)values (11,'武术',100);
insert into smallcategory(id,small_categoryname,big_categoryid)values (32,'篮球',100);
insert into smallcategory(id,small_categoryname,big_categoryid)values (323,'天文学',31);
insert into smallcategory(id,small_categoryname,big_categoryid)values (52,'编程',31);