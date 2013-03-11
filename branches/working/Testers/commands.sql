create table contacts (name text, lastname text, phone integer, email text, id integer primary key);
create table events (name text, type text, date integer, contact_id integer, id integer primary key, description text);
create table messages(id integer primary key, message text, date integer, type text);
create table configuration(date_format text, sound_path text, language text, skin_path text, eortologio_url text);
.exit