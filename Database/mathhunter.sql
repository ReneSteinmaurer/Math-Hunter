use math_hunter;


create table users(
    id int(11) NOT NULL,
    name varchar(45) not null,
    password varchar(45) not null,
    primary key (id)
);

create table player(
  id int(11) not null,
  name varchar(45) not null,
  level int(11) not null,
  primary key (id)
);

create table achievements(
  id int(11) not null,
  level int(11) not null,
  primary key (id)
);

create table items(
  id int(11) not null,
  name varchar(45) not null,
  power varchar(45) not null,
  primary key (id)
);

create table player_user(
  playerID int(11) not null,
  userID int(11) not null,
  primary key (playerID, userID),

  CONSTRAINT playerID FOREIGN KEY (playerID) REFERENCES player(id),
  CONSTRAINT userID FOREIGN KEY (userID) REFERENCES users(id)
);

create table player_achievement(
    playerID int(11) not null,
    achievementID int(11) not null,
    primary key (playerID, achievementID),
    CONSTRAINT playID FOREIGN KEY (playerID) REFERENCES player(id),
    CONSTRAINT achievementID FOREIGN KEY (achievementID) REFERENCES achievements(id)

);

create table player_item(
    playerID int(11) not null,
    itemID int(11) not null,
    primary key (playerID, itemID),
    CONSTRAINT playerItemID FOREIGN KEY (playerID) REFERENCES player(id),
    CONSTRAINT itemID FOREIGN KEY (itemID) REFERENCES items(id)

);



