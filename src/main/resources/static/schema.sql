create schema if not exists test;

drop table if exists booking;
drop table if exists category;
drop table if exists room;
drop table if exists user;
drop table if exists option;
drop table if exists booking_option;

create temporary table category
(
  id   bigint(19) auto_increment not null,
  name varchar(20),
  primary key (id)
);


create temporary table room
(
  id          bigint(19) auto_increment not null,
  number      integer(10),
  price       decimal(19, 2),
  category_id bigint(19),
  primary key (id),
  foreign key (category_id) references category (id)
);


create temporary table user
(
  id    bigint(19) auto_increment not null,
  name  varchar(200),
  phone varchar(20),
  primary key (id)
);


create temporary table option
(
  id    bigint(19) auto_increment not null,
  name  varchar(200),
  price decimal(19, 2),
  primary key (id)
);


create temporary table booking
(
  id         bigint(19) auto_increment not null,
  end_date   date(10),
  start_date date(10),
  room_id    bigint(19),
  user_id    bigint(19),
  primary key (id),
  foreign key (room_id) references room (id),
  foreign key (user_id) references user (id)
);


create table booking_option
(
  booking_id bigint(19),
  option_id  bigint(19),
  foreign key (option_id) references option (id),
  foreign key (booking_id) references booking (id)
)

