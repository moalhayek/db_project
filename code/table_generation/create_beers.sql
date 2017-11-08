CREATE TABLE IF NOT EXISTS `beers` (
  `id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  `manf` VARCHAR(255) NULL,
  `abv` REAL NULL,
  `manf_price` REAL NULL,
  PRIMARY KEY (`id`));

LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/db_project/code/table_generation/beers_table.csv' 
INTO TABLE beers
FIELDS TERMINATED BY ','
IGNORE 1 LINES;  