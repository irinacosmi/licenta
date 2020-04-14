BEGIN;

-- Create table intrebari
CREATE TABLE intrebare
(
  id   SERIAL PRIMARY KEY NOT NULL,
  nume VARCHAR(255)        NOT NULL
);


-- Create table raspuns
CREATE TABLE raspuns
(
  id            SERIAL PRIMARY KEY NOT NULL,
  intrebare_id  INTEGER,
  varianta_id   INTEGER,
  sesiune_id    VARCHAR(255),
  FOREIGN KEY (intrebare_id) REFERENCES intrebare (id)
  MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE
);

-- Create table rezultat
CREATE TABLE rezultat
(
  id            SERIAL PRIMARY KEY NOT NULL,
  rezultat_id   INTEGER,
  scor          INTEGER,
  sesiune_id    VARCHAR(255)
);

COMMIT;