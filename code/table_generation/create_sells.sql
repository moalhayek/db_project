CREATE TABLE IF NOT EXISTS `sells` (
  `bar_id` INT NOT NULL,
  `beer_id` INT NULL,
  `is_on_tap` BOOLEAN NULL,
  `price` INT NULL,
  PRIMARY KEY (`bar_id`,`beer_id`));
  
LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/sells_table.csv' 
INTO TABLE sells
FIELDS TERMINATED BY ','
IGNORE 1 LINES;