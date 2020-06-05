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
    10
);

INSERT INTO movies
    (title, release_date, synopsis, usr_rating)
VALUES (
        'Pulp Fiction',
        '1994-05-06',
            'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption',
        9
);

INSERT INTO movies
    (title, release_date, synopsis, usr_rating)
VALUES ('The Godfather',
        '1972-10-05',
        'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son',
        9
);

INSERT INTO movies
    (title, release_date, synopsis, usr_rating)
VALUES ('The Dark Knight',
        '2008-11-05',
        'When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice',
        10
);


