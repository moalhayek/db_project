CREATE TABLE `listens` (
  `drinker_id` INT NOT NULL,
  `music_id` INT NOT NULL,
  FOREIGN KEY (`drinker_id`) REFERENCES drinkers(`id`),
  FOREIGN KEY (`music_id`) REFERENCES music(`id`)
);

LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/listens_table.csv' 
INTO TABLE listens
FIELDS TERMINATED BY ','
IGNORE 1 LINES;