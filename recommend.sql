/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/4/12 14:28:21                           */
/*==============================================================*/


drop table if exists movie;

drop table if exists user;

drop table if exists user_like;

drop table if exists user_rating;

/*==============================================================*/
/* Table: movie                                                 */
/*==============================================================*/
create table movie
(
   id                   bigint(11) not null auto_increment,
   movieName            varchar(32),
   movieRanking         bigint,
   movieImg             varchar(255),
   movieScore           double,
   type                 varchar(32),
   primary key (id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   id                   bigint(11) not null auto_increment,
   username             varchar(32),
   password             varchar(255),
   primary key (id)
);

/*==============================================================*/
/* Table: user_like                                             */
/*==============================================================*/
create table user_like
(
   id                   bigint(11) not null auto_increment,
   user_id              bigint(11) not null,
   movie_id             bigint(11) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: user_rating                                           */
/*==============================================================*/
create table user_rating
(
   id                   bigint(11) not null,
   movie_id             bigint(11),
   user_movieScore      double,
   user_id              bigint(11),
   primary key (id)
);

alter table user_like add constraint FK_Reference_1 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_like add constraint FK_Reference_2 foreign key (movie_id)
      references movie (id) on delete restrict on update restrict;

alter table user_rating add constraint FK_Reference_3 foreign key (user_id)
      references user (id) on delete restrict on update restrict;

alter table user_rating add constraint FK_Reference_4 foreign key (movie_id)
      references movie (id) on delete restrict on update restrict;

