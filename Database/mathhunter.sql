create table if not exists users
(
    name     varchar(45) not null primary key,
    password varchar(45) not null
);

create table if not exists players
(
    id     int         not null auto_increment,
    name   varchar(45) not null,
    points int         not null,
    primary key (id)
);

create table if not exists user_player
(
    playerID   int          not null auto_increment,
    userName   varchar(100) not null,
    playerName varchar(100) not null,

    primary key (playerID),
    CONSTRAINT players FOREIGN KEY (playerID) REFERENCES players (id)
);

create table if not exists vocabs
(
    id            int(100) not null primary key auto_increment,
    germanVocab   varchar(100),
    englischVocab varchar(100)
);

DELIMITER $$

create procedure addVocab(
    in germanVocabIn varchar(100),
    englischVocabIn varchar(100)
)
begin
    declare existsVocab int(50);
    select germanVocab from vocabs where germanVocab = germanVocabIn into existsVocab;
    if (existsVocab is null) then
        insert into vocabs (germanVocab, englischVocab)
        values (germanVocabIn, englischVocabIn);
    end if;
end $$

create procedure addUser(
    in usernameIn varchar(100),
    passwordIn varchar(100),
    playerNameIn varchar(100)
)
begin
    declare playerIDVar int;
    declare userNamevar varchar(100);
    select name from users where name = usernameIn into userNamevar;
    if (userNamevar is null) then
        insert into users (name, password) values (usernameIn, passwordIn);
        insert into players (name, points) values (playerNameIn, 10);
        select id from players where playerNameIn = name into playerIDVar;
        insert into user_player (playerID, userName, playerName) values (playerID, usernameIn, playerNameIn);
    end if;
end $$;

call addUser('root', 'admin', 'ADMIN');
call addUser('test', 'admin', 'testPlayer');
call addUser('test2', 'admin', 'test');

call addVocab('Tisch', 'desk');
call addVocab('unterscheiden', 'distingouish');
call addVocab('Abend', 'evening');
call addVocab('aber', 'however');
call addVocab('alles', 'everything');
call addVocab('installieren', 'install');
call addVocab('anwenden', 'apply');
call addVocab('Speicher', 'memory');
call addVocab('Gerät', 'device');
call addVocab('Detektor', 'detector');
call addVocab('Fälschung', 'forgery');




