create table  if not exists users
(
    id       int NOT NULL auto_increment,
    name     varchar(45) not null,
    password varchar(45) not null,
    primary key (id)
);

create table if not exists players
(
    id    int not null auto_increment,
    name  varchar(45) not null,
    points int not null,
    primary key (id)
);

create table if not exists player_user
(
    playerID int not null auto_increment,
    userID int not null,
    playerName varchar(100) not null,
    primary key (playerID),

    CONSTRAINT playerID FOREIGN KEY (playerID) REFERENCES players (id)
);

DELIMITER $$
create procedure addUser(
in  usernameIn varchar(100),
    passwordIn varchar(100),
    playerNameIn varchar(100)
)
begin
declare userIDvar int;
declare userNamevar varchar(100);
select name from users where name = usernameIn into userNamevar;
if (userNamevar is null ) then
    insert into users (name, password) values (usernameIn, passwordIn);
    insert into players (name, points) values (playerNameIn, 0);
    select id from users where name = usernameIn into userIDvar;
    insert into player_user (userID, playerName) values (userIDvar, playerNameIn);
end if;
end $$;

select * from USERS;

call addUser('Yannick Weber', '389u4sdj', 'YanWeb10');
call addUser('Rene Steinmaurer', '34u50jkdf', 'Steini94');
call addUser('Peter Sandratsch', '349jeoifsdfss', 'Sandratsch');
call addUser('Peter Sandratsch', '34sdfsdf', 'Sandratsch44');
call addUser('Anton Schachl', 'asdfio4wskldfasldkjf', 'Schachi93');



