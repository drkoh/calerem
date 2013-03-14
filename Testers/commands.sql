create table contacts 
(name text, 
 lastname text,
 phone integer,
 email text,
 _id integer primary key);
create table events 
(name text, type text,
 date integer,
 contact_id integer,
 _id integer primary key,
 description text);
create table messages
(_id integer primary key,
 message text,
 date integer,
 type text);
create table configuration
(date_format text,
 sound_path text,
 language text,
 skin_path text,
 eortologio_url text);
create table celebration 
(name text,
 _id integer primary key,
 type text,
 date int);
create table synchronize_log
(date int,
 _id integer primary key,
 type text);
create table message_log
(date int,
 _id integer primary key,
 type text,
 message text);
create table android_metadata 
(locale TEXT DEFAULT 'en_US');
insert into android_metadata 
values ('en_US');
.exit