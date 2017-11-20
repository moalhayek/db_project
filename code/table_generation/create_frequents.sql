CREATE TABLE `frequents` (
  `drinker_id` INT NOT NULL,
  `bar_id` INT NOT NULL,
  FOREIGN KEY (`drinker_id`) REFERENCES drinkers(`id`),
  FOREIGN KEY (`bar_id`) REFERENCES bars(`id`)
);

LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/db_project/code/table_generation/frequents_table.csv' 
INTO TABLE frequents
FIELDS TERMINATED BY ','
IGNORE 1 LINES;
