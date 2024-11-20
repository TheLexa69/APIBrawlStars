DROP DATABASE IF EXISTS BD_Brawl;
CREATE DATABASE BD_Brawl;
USE BD_Brawl;


DROP TABLE IF EXISTS eventos;
CREATE TABLE eventos
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    startTime DATETIME     NOT NULL,
    endTime   DATETIME     NOT NULL,
    slotId    INT          NOT NULL,
    eventId   INT          NOT NULL,
    mode      VARCHAR(50)  NOT NULL,
    map       VARCHAR(100) NOT NULL,
    modifiers JSON DEFAULT NULL
);

CREATE TABLE StarPowers
(
    id        INT PRIMARY KEY,
    name      VARCHAR(50) NOT NULL,
    brawlerId INT
);

CREATE TABLE Gadgets
(
    id        INT PRIMARY KEY,
    name      VARCHAR(50) NOT NULL,
    brawlerId INT
);
CREATE TABLE Clubs
(
    tag              VARCHAR(20) PRIMARY KEY,
    nombre           VARCHAR(100) NOT NULL,
    descripcion      TEXT,
    tipo             VARCHAR(20)  NOT NULL,
    badgeId          INT,
    requiredTrophies INT,
    trophies         INT
);

CREATE TABLE ClubMembers
(
    tag       VARCHAR(20) PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    nameColor VARCHAR(10),
    role      VARCHAR(20)  NOT NULL,
    trophies  INT,
    iconId    INT,
    clubTag   VARCHAR(20)
);
CREATE TABLE Players
(
    tag                           VARCHAR(20) PRIMARY KEY,
    name                          VARCHAR(100) NOT NULL,
    nameColor                     VARCHAR(10),
    iconId                        INT,
    trophies                      INT,
    highestTrophies               INT,
    expLevel                      INT,
    expPoints                     INT,
    isQualifiedFromChampChallenge BOOLEAN,
    threeVsThreeVictories         INT,
    soloVictories                 INT,
    duoVictories                  INT,
    bestRoboRumbleTime            INT,
    bestTimeAsBigBrawler          INT,
    clubTag                       VARCHAR(20)
);

CREATE TABLE Brawlers
(
    playerTag       VARCHAR(20),
    id              INT,
    nameBrawler     VARCHAR(100) NOT NULL,
    power           INT,
    nRank           INT,
    trophies        INT,
    highestTrophies INT,
    PRIMARY KEY (playerTag, id)
);

CREATE TABLE Gears
(
    id        INT,
    name      VARCHAR(50),
    level     INT,
    playerTag VARCHAR(20),
    brawlerId INT,
    PRIMARY KEY (playerTag, brawlerId, id)
);

CREATE TABLE Battles
(
    battleTime   DATETIME,
    playerTag    VARCHAR(20),
    battleMode   VARCHAR(50),
    trophyChange INT,
    result       VARCHAR(20),
    duration     INT,
    PRIMARY KEY (battleTime, playerTag)
);
DROP TABLE Users;
CREATE TABLE Users
(
    id                INT AUTO_INCREMENT PRIMARY KEY,                                             -- Identificador único para cada usuario
    name              VARCHAR(255) NOT NULL UNIQUE,                                               -- Nombre del usuario (único)
    password          VARCHAR(255) NOT NULL,                                                      -- Contraseña hash del usuario
    role              VARCHAR(20)  NOT NULL default 'user',
    confirmation_code VARCHAR(255),                                                               -- Código de confirmación enviado al usuario
    is_active         BOOLEAN               DEFAULT FALSE,                                        -- Estado de activación del usuario
    created_at        TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,                            -- Fecha de creación del registro
    updated_at        TIMESTAMP             DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- Fecha de última actualización
);


#alter table StarPowers add Constraint fkStarBrawlerId foreign key (brawlerId) REFERENCES Brawlers(id);
alter table StarPowers
    add INDEX idx_brawlerid (brawlerId);
alter table Brawlers
    add INDEX idx_brawlerid2 (id);
alter table StarPowers
    add FOREIGN KEY (brawlerId) REFERENCES Brawlers (id);
alter table Gadgets
    add FOREIGN KEY (brawlerId) REFERENCES Brawlers (id);
alter table Gears
    add FOREIGN KEY (brawlerId) REFERENCES Brawlers (id);

alter table Players
    add FOREIGN KEY (clubTag) REFERENCES Clubs (tag);
alter table ClubMembers
    add FOREIGN KEY (clubTag) REFERENCES Clubs (tag);
alter table Battles
    add FOREIGN KEY (playerTag) REFERENCES Players (tag);
alter table Brawlers
    add FOREIGN KEY (playerTag) REFERENCES Players (tag);
alter table Gears
    add FOREIGN KEY (playerTag) REFERENCES Players (tag);

select *
from usersapi;
/** SELECT b.name, b.trophies 
FROM Brawlers b 
JOIN Players p ON b.playerTag = p.tag 
WHERE p.tag = '#80020R9Q8';

INSERT INTO Clubs (tag, name, description, type, badgeId, requiredTrophies, trophies) 
VALUES ('#2UR98V9Q2', 'Poetas del pno', 'mi amigo se comio una 06. Abstenganse moros y peruanos', 'open', 8000028, 12000, 721280);

 INSERT INTO ClubMembers (tag, name, nameColor, role, trophies, iconId, clubTag) 
VALUES 
('#R0CUV9RY', 'kingalex', '0xfff9c908', 'president', 42847, 28000583, '#2UR98V9Q2'),
('#92CQLCYJ2', 'Sandi', '0xfff9c908', 'member', 41232, 28000436, '#2UR98V9Q2'),
('#P98V9UY0Q', 'Sousa', '0xff4ddba2', 'senior', 9557, 28000430, '#2UR98V9Q2');

SELECT cm.name, cm.role, cm.trophies 
FROM ClubMembers cm 
JOIN Clubs c ON cm.clubTag = c.tag 
WHERE c.tag = '#2UR98V9Q2';

INSERT INTO Brawlers (id, name) VALUES (16000000, 'SHELLY');
INSERT INTO Brawlers (id, name) VALUES (16000001, 'COLT');
INSERT INTO StarPowers (id, name, brawlerId) VALUES (23000076, 'SHELL SHOCK', 16000000);
INSERT INTO StarPowers (id, name, brawlerId) VALUES (23000135, 'BAND-AID', 16000000);
INSERT INTO StarPowers (id, name, brawlerId) VALUES (23000077, 'SLICK BOOTS', 16000001);
INSERT INTO StarPowers (id, name, brawlerId) VALUES (23000138, 'MAGNUM SPECIAL', 16000001);
INSERT INTO Gadgets (id, name, brawlerId) VALUES (23000255, 'FAST FORWARD', 16000000);
INSERT INTO Gadgets (id, name, brawlerId) VALUES (23000288, 'CLAY PIGEONS', 16000000);
INSERT INTO Gadgets (id, name, brawlerId) VALUES (23000273, 'SPEEDLOADER', 16000001);
INSERT INTO Gadgets (id, name, brawlerId) VALUES (23000319, 'SILVER BULLET', 16000001);
*/