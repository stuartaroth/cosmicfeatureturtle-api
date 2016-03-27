drop database cosmicfeatureturtle;
create database cosmicfeatureturtle;
use cosmicfeatureturtle;

create table user(
  id_user int not null auto_increment,
  name varchar(255) not null,
  password varchar(255) not null,
  api_key varchar(255) not null,
  primary key (id_user),
  unique (name)
);

create table feature(
  id_feature int not null auto_increment,
  id_user int default null,
  title varchar(100) default null,
  idea varchar(510) default null,
  date_created datetime not null default now(),
  date_edited datetime not null default now(),
  primary key (id_feature),
  foreign key (id_user) references user (id_user),
  unique (id_user, title)
);

create table vote(
  id_vote int not null auto_increment,
  id_user int not null,
  id_feature int not null,
  upvote boolean not null,
  primary key (id_vote),
  foreign key (id_feature) references feature (id_feature),
  foreign key (id_user) references user (id_user),
  unique (id_user, id_feature)
);

create table comment(
  id_comment int not null auto_increment,
  id_user int not null,
  id_feature int not null,
  body varchar(510) not null,
  date_created datetime not null default now(),
  date_edited datetime not null default now(),
  primary key (id_comment),
  foreign key (id_feature) references feature (id_feature),
  foreign key (id_user) references user (id_user),
  unique (id_user, id_feature, body)
);
