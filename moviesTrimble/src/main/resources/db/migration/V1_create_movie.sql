CREATE TABLE IF NOT EXISTS movie(
    id serial primary key,
    title varchar(30),
    release_date timestamp,
    synopsis text,
    user_rating int
)  ENGINE=INNODB;