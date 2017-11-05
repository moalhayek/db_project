CREATE TABLE `bars` (
  `id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  `license` VARCHAR(20) NULL,
  `state` VARCHAR(3) NULL,
  `city` VARCHAR(255) NULL,
  `street_address` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));


LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/db_project/code/table_generation/bar_table.csv' 
INTO TABLE bars
FIELDS TERMINATED BY ','
IGNORE 1 LINES;  
