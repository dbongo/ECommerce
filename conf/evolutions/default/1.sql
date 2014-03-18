# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  ID                        integer not null,
  name                      varchar(255),
  icon                      varchar(255),
  constraint pk_category primary key (ID))
;

create table product (
  ID                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  price                     double,
  img_url                   varchar(255),
  constraint pk_product primary key (ID))
;

create table user (
  id                        integer not null,
  name                      varchar(255),
  email                     varchar(255),
  constraint pk_user primary key (id))
;


create table product_category (
  product_ID                     integer not null,
  category_ID                    integer not null,
  constraint pk_product_category primary key (product_ID, category_ID))
;
create sequence category_seq;

create sequence product_seq;

create sequence user_seq;




alter table product_category add constraint fk_product_category_product_01 foreign key (product_ID) references product (ID) on delete restrict on update restrict;

alter table product_category add constraint fk_product_category_category_02 foreign key (category_ID) references category (ID) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists category;

drop table if exists product;

drop table if exists product_category;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists category_seq;

drop sequence if exists product_seq;

drop sequence if exists user_seq;

