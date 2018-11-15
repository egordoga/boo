insert into user values (1, 'Cat', '1111')
insert into user values (2, 'Dog', '2222')
insert into user values (3, 'Cow', '3333')
insert into user values (4, 'Tiger', '4444')

insert into category values (1, 'люкс')
insert into category values (2, 'полулюкс')
insert into category values (3, 'стандарт')
insert into category values (4, 'эконом')

insert into option values (1, 'завтрак', 10)
insert into option values (2, 'уборка', 7)
insert into option values (3, 'минибар', 15)
insert into option values (4, 'глажка', 7)
insert into option values (5, 'нет', 0)

insert into room values (1, 10, 100, 1)
insert into room values (2, 11, 70, 2)
insert into room values (3, 12, 50, 3)
insert into room values (4, 13, 30, 4)
insert into room values (5, 14, 100, 1)
insert into room values (6, 15, 70, 2)
insert into room values (7, 16, 50, 3)
insert into room values (8, 17, 30, 4)
insert into room values (9, 18, 100, 1)
insert into room values (10, 19, 70, 2)
insert into room values (11, 20, 50, 3)
insert into room values (12, 21, 30, 4)

insert into booking values (1, '2018-01-20', '2018-01-15', 1 , 1)
insert into booking values (2, '2018-02-20', '2018-02-15', 12, 2)
insert into booking values (3, '2018-11-20', '2018-11-15', 6 , 3)
insert into booking values (4, '2018-12-31', '2018-11-15', 11, 4)
insert into booking values (5, '2018-12-26', '2018-12-20', 5 , 1)
insert into booking values (6, '2018-11-30', '2018-11-22', 2 , 2)
insert into booking values (7, '2018-12-01', '2018-11-25', 10, 3)
insert into booking values (8, '2018-12-20', '2018-11-05', 4 , 4)
insert into booking values (9, '2018-11-20', '2018-11-15', 8 , 1)
insert into booking values (10,'2018-12-05', '2018-12-01', 2 , 2)
insert into booking values (11,'2019-01-20', '2018-12-27', 5 , 3)
insert into booking values (12,'2018-12-22', '2018-12-15', 1 , 4)

insert into booking_option values (1, 1)
insert into booking_option values (2, 2)
insert into booking_option values (3, 3)
insert into booking_option values (4, 4)
--insert into booking_option values (null, 5)
insert into booking_option values (3, 6)
--insert into booking_option values (null, 7)
--insert into booking_option values (null, 8)
insert into booking_option values (4, 9)
insert into booking_option values (2, 10)
insert into booking_option values (2, 11)
--insert into booking_option values (null, 12)