CREATE TABLE IF NOT EXISTS `music` (
  `id` INT NOT NULL,
  `genre` VARCHAR(200) NULL,
  PRIMARY KEY (`id`));

LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/music_table.csv' 
INTO TABLE music
FIELDS TERMINATED BY ','
IGNORE 1 LINES;  