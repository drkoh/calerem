CREATE TABLE contacts 
(name TEXT, 
 lastname TEXT,
 phone INTEGER,
 email TEXT,
 _id INTEGER PRIMARY KEY);
CREATE TABLE events 
(name TEXT, type TEXT,
 date INTEGER,
 contact_id INTEGER,
 _id INTEGER PRIMARY KEY,
 description TEXT);
CREATE TABLE messages
(_id INTEGER PRIMARY KEY,
 message TEXT,
 date INTEGER,
 type TEXT);
CREATE TABLE configuration
(date_format TEXT,
 sound_path TEXT,
 language TEXT,
 skin_path TEXT,
 eortologio_url TEXT);
CREATE TABLE celebration 
(name TEXT,
 _id INTEGER PRIMARY KEY,
 type TEXT,
 date int);
CREATE TABLE synchronize_log
(date int,
 _id INTEGER PRIMARY KEY,
 type TEXT);
CREATE TABLE message_log
(date int,
 _id INTEGER PRIMARY KEY,
 type TEXT,
 message TEXT,
 contact_id INTEGER);
CREATE TABLE android_metadata 
(locale TEXT DEFAULT 'en_US');
INSERT INTO android_metadata 
values ('en_US');
.exit