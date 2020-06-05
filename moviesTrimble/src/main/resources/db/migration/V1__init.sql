CREATE TABLE IF NOT EXISTS movies(
    id serial primary key,
    title varchar(30),
    release_date timestamp,
    synopsis text,
    user_rating int
)