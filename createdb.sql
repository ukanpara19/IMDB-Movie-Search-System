
CREATE TABLE MOVIES(
    MOVIEID VARCHAR2(50) PRIMARY KEY,
    TITLE VARCHAR2(200),
    MYEAR INTEGER,
    RTAVGALLCRICTICRATING VARCHAR2(200),
    RTTOPCRITICRATING VARCHAR2(200),
    RTAUDIENCERATING VARCHAR(200),

    average of Rotten Tomato all Critics number of reviews
    
    RTTOPCRICTICNUMREVIEWS VARCHAR2(200),
    RTAUDIENCENUMRATING VARCHAR(200)
);

CREATE TABLE TAGS(
    TAGID VARCHAR2(50) PRIMARY KEY,
    VALUE VARCHAR2(200)
);

CREATE TABLE MOVIE_ACTORS(
    MOVIEID VARCHAR2(50),
    ACTORID VARCHAR2(100),
    ACTORNAME VARCHAR2(150),
    RANKING INTEGER,
    FOREIGN KEY(MOVIEID) REFERENCES MOVIES(MOVIEID)
    ON DELETE CASCADE
);

CREATE TABLE MOVIE_COUNTRIES(
    MOVIEID VARCHAR2(50),
    COUNTRY VARCHAR2(100),
    FOREIGN KEY(MOVIEID) REFERENCES MOVIES(MOVIEID)
    ON DELETE CASCADE
);

CREATE TABLE MOVIE_DIRECTORS(
    MOVIEID VARCHAR2(50),
    DIRECTORID VARCHAR2(100),
    DIRECTORNAME VARCHAR2(150),
    FOREIGN KEY(MOVIEID) REFERENCES MOVIES(MOVIEID)
    ON DELETE CASCADE
);

CREATE TABLE MOVIE_GENRES(
    MOVIEID VARCHAR2(50),
    GENRE VARCHAR2(100),
    FOREIGN KEY(MOVIEID) REFERENCES MOVIES(MOVIEID)
    ON DELETE CASCADE
);

CREATE TABLE MOVIE_LOCATION(
    MOVIEID VARCHAR2(50),
    COUNTRY VARCHAR2(100),
    STATE   VARCHAR2(100),
    CITY    VARCHAR2(100),
    STREET  VARCHAR2(100),
    FOREIGN KEY(MOVIEID) REFERENCES MOVIES(MOVIEID)
    ON DELETE CASCADE
);

CREATE TABLE MOVIE_TAGS(
    MOVIEID VARCHAR2(50),
    TAGID VARCHAR2(100),
    TAGWEIGHT INTEGER,
    FOREIGN KEY(MOVIEID) REFERENCES MOVIES(MOVIEID)
    ON DELETE CASCADE,
    FOREIGN KEY(TAGID) REFERENCES TAGS(TAGID)
    ON DELETE CASCADE
);

CREATE TABLE USER_TAGGEDMOVIES(
    USERID VARCHAR2(50),
    MOVIEID VARCHAR2(50),
    TAGID VARCHAR2(100),
    DATE_DAY INTEGER,
    DATE_MONTH INTEGER,
    DATE_YEAR INTEGER,
    DATE_HOUR INTEGER,
    FOREIGN KEY(MOVIEID) REFERENCES MOVIES(MOVIEID)
    ON DELETE CASCADE,
    FOREIGN KEY(TAGID) REFERENCES TAGS(TAGID)
    ON DELETE CASCADE
);

CREATE INDEX INDMOVIEID ON MOVIES(MOVIEID);
CREATE INDEX INDGENRE ON MOVIE_GENRES(GENRE);
CREATE INDEX INDCOUNTRY ON MOVIE_COUNTRIES(COUNTRY);
CREATE INDEX INDACTOR ON MOVIE_ACTORS(ACTORNAME);
CREATE INDEX INDDIRECTOR ON MOVIE_DIRECTORS(DIRECTORNAME);
CREATE INDEX INDTAG ON TAGS(TAGID);
CREATE INDEX INDUSER ON USER_TAGGEDMOVIES(USERID);
