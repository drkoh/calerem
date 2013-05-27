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
 type TEXT,
 contact_id INTEGER);
CREATE TABLE configuration
(date_format TEXT,
 sound_path TEXT,
 language TEXT,
 skin_path TEXT,
 eortologiourlgr TEXT,
 eortologiourlen TEXT);
 INSERT INTO configuration values('dd-MM-yyyy','','','','http://www.eortologio.gr/rss/si_av_me_el.xml','http://www.eortologio.gr/rss/si_av_me_en.xml');
CREATE TABLE celebrations 
(name TEXT,
 _id INTEGER PRIMARY KEY,
 type TEXT,
 date TEXT);
CREATE TABLE synchronize_log
(date int,
 _id INTEGER PRIMARY KEY,
 type TEXT);
CREATE TABLE android_metadata 
(locale TEXT DEFAULT 'en_US');
INSERT INTO android_metadata 
values ('en_US');
.exit