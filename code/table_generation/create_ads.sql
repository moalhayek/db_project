CREATE TABLE IF NOT EXISTS `ad_platforms` (
  `id` INT NOT NULL,
  `platform` VARCHAR(255) NULL,
  `user_count` INT NULL,
  PRIMARY KEY (`id`));
  
LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/db_project/code/table_generation/ads_table.csv' 
INTO TABLE ad_platforms
FIELDS TERMINATED BY ','
IGNORE 1 LINES;