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
    englischVocab varchar(100),
    difficulty varchar(50)
);

create table if not exists calculations(
    id int (100) not null primary key auto_increment,
    calculation varchar(100),
    result int(100) not null
);

DELIMITER $$

create procedure addVocab(
    in germanVocabIn varchar(100),
    englischVocabIn varchar(100),
    difficultyIn varchar(50)
)
begin
    declare existsVocab int(50);
    select germanVocab from vocabs where germanVocab = germanVocabIn into existsVocab;
    if (existsVocab is null) then
        insert into vocabs (germanVocab, englischVocab, difficulty)
        values (germanVocabIn, englischVocabIn, difficultyIn);
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
        insert into user_player (playerID, userName, playerName) values (playerIDVar, usernameIn, playerNameIn);
    end if;
end $$;

call addUser('root', 'admin', 'ADMIN');
call addUser('test', 'admin', 'testPlayer');
call addUser('test2', 'admin', 'test');

call addVocab('Tisch', 'desk','1');
call addVocab('unterscheiden', 'distingouish','2');
call addVocab('Abend', 'evening','1');
call addVocab('aber', 'however','1');
call addVocab('alles', 'everything','1');
call addVocab('installieren', 'install','1');
call addVocab('anwenden', 'apply','1');
call addVocab('Speicher', 'memory','1');
call addVocab('Gerät', 'device','2');
call addVocab('Detektor', 'detector','2');
call addVocab('Fälschung', 'forgery','2');

insert into calculations (calculation, result) VALUES ('25+17', 42);
insert into calculations (calculation, result) VALUES ('42+100', 142);
insert into calculations (calculation, result) VALUES ('9*8', 72);
insert into calculations (calculation, result) VALUES ('54/6', 9);
insert into calculations (calculation, result) VALUES ('70-33', 37);




