CREATE TABLE movies (
    id serial,
    title varchar(30),
    release_date timestamp ,
    synopsis text,
    usr_rating int
);

INSERT INTO movies
    (title, release_date, synopsis, usr_rating)
VALUES (
    'The Fellowship of the Ring',
    '2001-12-10',
    'A meek Hobbit from the Shire and eight companions set out on a journey to destroy the powerful One Ring and save Middle-earth from the Dark Lord Sauron',
    9
);


