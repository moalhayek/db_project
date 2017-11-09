CREATE TABLE `likes` (
  `drinker_id` INT NOT NULL,
  `beer_id` INT NOT NULL,
  FOREIGN KEY (`drinker_id`) REFERENCES drinkers(`id`),
  FOREIGN KEY (`beer_id`) REFERENCES beers(`id`)
);

LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/likes_table.csv' 
INTO TABLE likes
FIELDS TERMINATED BY ','
IGNORE 1 LINES;