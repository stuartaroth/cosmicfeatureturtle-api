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
  primary key (id_comment),
  foreign key (id_feature) references feature (id_feature),
  foreign key (id_user) references user (id_user),
  unique (id_user, id_feature, body)
);

insert into user (name, password, api_key) values ('bill', sha2('password', 224), uuid());
insert into user (name, password, api_key) values ('ben', sha2('password', 224), uuid());
insert into user (name, password, api_key) values ('bev', sha2('password', 224), uuid());
insert into user (name, password, api_key) values ('richie', sha2('password', 224), uuid());
insert into user (name, password, api_key) values ('eddie', sha2('password', 224), uuid());
insert into user (name, password, api_key) values ('mike', sha2('password', 224), uuid());
insert into user (name, password, api_key) values ('stan', sha2('password', 224), uuid());

insert into feature (id_user, title, idea) values (1, 'Bill 1', 'This is  Bill\'s first idea.');
insert into vote(id_user, id_feature, upvote) values (1, 1, true);
insert into vote(id_user, id_feature, upvote) values (2, 1, true);
insert into vote(id_user, id_feature, upvote) values (3, 1, true);
insert into vote(id_user, id_feature, upvote) values (4, 1, true);
insert into vote(id_user, id_feature, upvote) values (5, 1, true);
insert into vote(id_user, id_feature, upvote) values (6, 1, true);
insert into vote(id_user, id_feature, upvote) values (7, 1, true);
insert into comment(id_user, id_feature, body) values (2, 1, 'Nice idea. +1');
insert into comment(id_user, id_feature, body) values (3, 1, 'Cool');

insert into feature (id_user, title, idea) values (1, 'Bill 2', 'This is  Bill\'s second idea.');
insert into vote(id_user, id_feature, upvote) values (1, 2, true);
insert into vote(id_user, id_feature, upvote) values (2, 2, false);
insert into vote(id_user, id_feature, upvote) values (3, 2, true);
insert into vote(id_user, id_feature, upvote) values (4, 2, false);
insert into vote(id_user, id_feature, upvote) values (5, 2, true);
insert into vote(id_user, id_feature, upvote) values (6, 2, false);
insert into vote(id_user, id_feature, upvote) values (7, 2, true);
insert into comment(id_user, id_feature, body) values (3, 2, 'Positive Comment');
insert into comment(id_user, id_feature, body) values (4, 2, 'Questioning Comment');

insert into feature (id_user, title, idea) values (1, 'Bill 3', 'This is  Bill\'s third idea.');
insert into vote(id_user, id_feature, upvote) values (1, 3, true);
insert into vote(id_user, id_feature, upvote) values (2, 3, true);
insert into vote(id_user, id_feature, upvote) values (3, 3, false);
insert into vote(id_user, id_feature, upvote) values (4, 3, true);
insert into vote(id_user, id_feature, upvote) values (5, 3, true);
insert into vote(id_user, id_feature, upvote) values (6, 3, false);
insert into vote(id_user, id_feature, upvote) values (7, 3, false);
insert into comment(id_user, id_feature, body) values (7, 3, 'Negative Comment');


insert into feature (id_user, title, idea) values (1, 'Bill 4', 'This is  Bill\'s fourth idea.');
insert into vote(id_user, id_feature, upvote) values (1, 4, true);
insert into vote(id_user, id_feature, upvote) values (2, 4, true);
insert into vote(id_user, id_feature, upvote) values (3, 4, true);
insert into vote(id_user, id_feature, upvote) values (4, 4, true);
insert into vote(id_user, id_feature, upvote) values (5, 4, true);
insert into vote(id_user, id_feature, upvote) values (6, 4, true);
insert into vote(id_user, id_feature, upvote) values (7, 4, true);
insert into comment(id_user, id_feature, body) values (1, 4, 'Explanation Comment');


insert into feature (id_user, title, idea) values (1, 'Bill 5', 'This is  Bill\'s fifth idea.');
insert into vote(id_user, id_feature, upvote) values (1, 5, true);
insert into vote(id_user, id_feature, upvote) values (2, 5, false);
insert into vote(id_user, id_feature, upvote) values (3, 5, false);
insert into vote(id_user, id_feature, upvote) values (4, 5, false);
insert into vote(id_user, id_feature, upvote) values (5, 5, true);
insert into vote(id_user, id_feature, upvote) values (6, 5, false);
insert into comment(id_user, id_feature, body) values (1, 5, 'Very Negative Comment');


insert into feature (id_user, title, idea) values (2, 'Ben 1', 'This is  Ben\'s first idea.');
insert into vote(id_user, id_feature, upvote) values (1, 6, false);
insert into vote(id_user, id_feature, upvote) values (2, 6, true);
insert into vote(id_user, id_feature, upvote) values (3, 6, true);
insert into vote(id_user, id_feature, upvote) values (4, 6, true);
insert into vote(id_user, id_feature, upvote) values (5, 6, true);
insert into vote(id_user, id_feature, upvote) values (6, 6, false);
insert into vote(id_user, id_feature, upvote) values (7, 6, true);
insert into comment(id_user, id_feature, body) values (5, 6, 'Positive Comment');
insert into comment(id_user, id_feature, body) values (6, 6, 'Questioning Comment');
insert into comment(id_user, id_feature, body) values (7, 6, 'Another Comment');

insert into feature (id_user, title, idea) values (2, 'Ben 2', 'This is  Ben\'s second idea.');
insert into vote(id_user, id_feature, upvote) values (1, 7, true);
insert into vote(id_user, id_feature, upvote) values (2, 7, true);
insert into vote(id_user, id_feature, upvote) values (3, 7, true);
insert into vote(id_user, id_feature, upvote) values (4, 7, false);
insert into vote(id_user, id_feature, upvote) values (5, 7, true);
insert into vote(id_user, id_feature, upvote) values (6, 7, true);
insert into vote(id_user, id_feature, upvote) values (7, 7, true);
insert into comment(id_user, id_feature, body) values (1, 7, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 7, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 7, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 7, 'Comment Comment');


insert into feature (id_user, title, idea) values (2, 'Ben 3', 'This is  Ben\'s third idea.');
insert into vote(id_user, id_feature, upvote) values (1, 8, true);
insert into vote(id_user, id_feature, upvote) values (2, 8, true);
insert into vote(id_user, id_feature, upvote) values (3, 8, false);
insert into vote(id_user, id_feature, upvote) values (4, 8, true);
insert into vote(id_user, id_feature, upvote) values (5, 8, true);
insert into vote(id_user, id_feature, upvote) values (6, 8, false);
insert into vote(id_user, id_feature, upvote) values (7, 8, true);
insert into comment(id_user, id_feature, body) values (1, 8, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 8, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 8, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 8, 'Comment Comment');

insert into feature (id_user, title, idea) values (2, 'Ben 4', 'This is  Ben\'s fourth idea.');
insert into vote(id_user, id_feature, upvote) values (1, 9, true);
insert into vote(id_user, id_feature, upvote) values (2, 9, true);
insert into vote(id_user, id_feature, upvote) values (3, 9, true);
insert into vote(id_user, id_feature, upvote) values (4, 9, true);
insert into vote(id_user, id_feature, upvote) values (5, 9, true);
insert into vote(id_user, id_feature, upvote) values (6, 9, true);
insert into comment(id_user, id_feature, body) values (1, 9, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 9, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 9, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 9, 'Comment Comment');

insert into feature (id_user, title, idea) values (3, 'Bev 1', 'This is  Bev\'s first idea.');
insert into vote(id_user, id_feature, upvote) values (1, 10, true);
insert into vote(id_user, id_feature, upvote) values (2, 10, true);
insert into vote(id_user, id_feature, upvote) values (3, 10, true);
insert into vote(id_user, id_feature, upvote) values (4, 10, true);
insert into vote(id_user, id_feature, upvote) values (5, 10, true);
insert into vote(id_user, id_feature, upvote) values (6, 10, true);
insert into vote(id_user, id_feature, upvote) values (7, 10, true);
insert into comment(id_user, id_feature, body) values (1, 10, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 10, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 10, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 10, 'Comment Comment');

insert into feature (id_user, title, idea) values (3, 'Bev 2', 'This is  Bev\'s second idea.');insert into vote(id_user, id_feature, upvote) values (1, 1, true);
insert into vote(id_user, id_feature, upvote) values (2, 11, true);
insert into vote(id_user, id_feature, upvote) values (3, 11, true);
insert into vote(id_user, id_feature, upvote) values (5, 11, true);
insert into vote(id_user, id_feature, upvote) values (6, 11, true);
insert into vote(id_user, id_feature, upvote) values (7, 11, true);
insert into comment(id_user, id_feature, body) values (1, 11, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 11, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 11, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 11, 'Comment Comment');

insert into feature (id_user, title, idea) values (3, 'Bev 3', 'This is  Bev\'s third idea.');
insert into vote(id_user, id_feature, upvote) values (1, 12, true);
insert into vote(id_user, id_feature, upvote) values (2, 12, true);
insert into vote(id_user, id_feature, upvote) values (3, 12, true);
insert into vote(id_user, id_feature, upvote) values (4, 12, true);
insert into vote(id_user, id_feature, upvote) values (5, 12, true);
insert into vote(id_user, id_feature, upvote) values (6, 12, true);
insert into comment(id_user, id_feature, body) values (1, 12, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 12, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 12, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 12, 'Comment Comment');

insert into feature (id_user, title, idea) values (4, 'Richie 1', 'This is  Richie\'s first idea.');
insert into vote(id_user, id_feature, upvote) values (1, 13, true);
insert into vote(id_user, id_feature, upvote) values (2, 13, true);
insert into vote(id_user, id_feature, upvote) values (3, 13, true);
insert into vote(id_user, id_feature, upvote) values (4, 13, true);
insert into vote(id_user, id_feature, upvote) values (5, 13, false);
insert into vote(id_user, id_feature, upvote) values (6, 13, true);
insert into comment(id_user, id_feature, body) values (1, 13, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 13, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 13, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 13, 'Comment Comment');

insert into feature (id_user, title, idea) values (4, 'Richie 2', 'This is  Richie\'s second idea.');
insert into vote(id_user, id_feature, upvote) values (1, 14, false);
insert into vote(id_user, id_feature, upvote) values (2, 14, true);
insert into vote(id_user, id_feature, upvote) values (3, 14, true);
insert into vote(id_user, id_feature, upvote) values (4, 14, true);
insert into vote(id_user, id_feature, upvote) values (5, 14, true);
insert into vote(id_user, id_feature, upvote) values (6, 14, true);
insert into vote(id_user, id_feature, upvote) values (7, 14, false);
insert into comment(id_user, id_feature, body) values (1, 14, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 14, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 14, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 14, 'Comment Comment');

insert into feature (id_user, title, idea) values (4, 'Richie 3', 'This is  Richie\'s third idea.');
insert into vote(id_user, id_feature, upvote) values (1, 15, true);
insert into vote(id_user, id_feature, upvote) values (2, 15, true);
insert into vote(id_user, id_feature, upvote) values (3, 15, false);
insert into vote(id_user, id_feature, upvote) values (4, 15, true);
insert into vote(id_user, id_feature, upvote) values (5, 15, false);
insert into vote(id_user, id_feature, upvote) values (6, 15, false);
insert into comment(id_user, id_feature, body) values (1, 15, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 15, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 15, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 15, 'Comment Comment');

insert into feature (id_user, title, idea) values (5, 'Eddie 1', 'This is  Eddie\'s first idea.');
insert into vote(id_user, id_feature, upvote) values (3, 16, true);
insert into vote(id_user, id_feature, upvote) values (4, 16, true);
insert into vote(id_user, id_feature, upvote) values (5, 16, false);
insert into vote(id_user, id_feature, upvote) values (6, 16, false);
insert into vote(id_user, id_feature, upvote) values (7, 16, true);
insert into comment(id_user, id_feature, body) values (1, 16, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 16, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 16, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 16, 'Comment Comment');

insert into feature (id_user, title, idea) values (5, 'Eddie 2', 'This is  Eddie\'s second idea.');
insert into vote(id_user, id_feature, upvote) values (1, 17, false);
insert into vote(id_user, id_feature, upvote) values (2, 17, true);
insert into vote(id_user, id_feature, upvote) values (5, 17, true);
insert into vote(id_user, id_feature, upvote) values (6, 17, true);
insert into comment(id_user, id_feature, body) values (1, 17, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 17, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 17, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 17, 'Comment Comment');

insert into feature (id_user, title, idea) values (5, 'Eddie 3', 'This is  Eddie\'s third idea.');
insert into vote(id_user, id_feature, upvote) values (1, 18, true);
insert into vote(id_user, id_feature, upvote) values (3, 18, false);
insert into vote(id_user, id_feature, upvote) values (4, 18, false);
insert into vote(id_user, id_feature, upvote) values (5, 18, true);
insert into vote(id_user, id_feature, upvote) values (6, 18, false);
insert into comment(id_user, id_feature, body) values (1, 18, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 18, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 18, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 18, 'Comment Comment');

insert into feature (id_user, title, idea) values (6, 'Mike 1', 'This is  Mike\'s first idea.');
insert into vote(id_user, id_feature, upvote) values (1, 19, true);
insert into vote(id_user, id_feature, upvote) values (2, 19, true);
insert into vote(id_user, id_feature, upvote) values (4, 19, false);
insert into vote(id_user, id_feature, upvote) values (5, 19, true);
insert into vote(id_user, id_feature, upvote) values (6, 19, true);
insert into comment(id_user, id_feature, body) values (1, 19, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 19, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 19, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 19, 'Comment Comment');

insert into feature (id_user, title, idea) values (6, 'Mike 2', 'This is  Mike\'s second idea.');
insert into vote(id_user, id_feature, upvote) values (1, 20, true);
insert into vote(id_user, id_feature, upvote) values (2, 20, true);
insert into vote(id_user, id_feature, upvote) values (3, 20, false);
insert into vote(id_user, id_feature, upvote) values (4, 20, true);
insert into vote(id_user, id_feature, upvote) values (7, 20, true);
insert into comment(id_user, id_feature, body) values (1, 20, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 20, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 20, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 20, 'Comment Comment');

insert into feature (id_user, title, idea) values (6, 'Mike 3', 'This is  Mike\'s third idea.');
insert into vote(id_user, id_feature, upvote) values (1, 21, true);
insert into vote(id_user, id_feature, upvote) values (3, 21, true);
insert into vote(id_user, id_feature, upvote) values (6, 21, false);
insert into comment(id_user, id_feature, body) values (1, 21, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (2, 21, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (3, 21, 'Comment Comment');
insert into comment(id_user, id_feature, body) values (4, 21, 'Comment Comment');

insert into feature (id_user, title, idea) values (7, 'Stan', 'This is Stan\'s only idea.');
insert into vote(id_user, id_feature, upvote) values (1, 22, false);
insert into vote(id_user, id_feature, upvote) values (2, 22, false);
insert into vote(id_user, id_feature, upvote) values (3, 22, false);
insert into vote(id_user, id_feature, upvote) values (4, 22, false);
insert into vote(id_user, id_feature, upvote) values (5, 22, false);
insert into vote(id_user, id_feature, upvote) values (6, 22, false);
insert into vote(id_user, id_feature, upvote) values (7, 22, true);
insert into comment(id_user, id_feature, body) values (1, 22, ':(');
insert into comment(id_user, id_feature, body) values (2, 22, ':(');
insert into comment(id_user, id_feature, body) values (3, 22, ':(');
insert into comment(id_user, id_feature, body) values (4, 22, ':(');
insert into comment(id_user, id_feature, body) values (5, 22, ':(');
insert into comment(id_user, id_feature, body) values (6, 22, ':(');