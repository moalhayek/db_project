CREATE TABLE IF NOT EXISTS `ad_platforms` (
  `id` INT NOT NULL,
  `platform` VARCHAR(255) NULL,
  `user_count` INT NULL,
  'cost_per_day' INT NULL,
  PRIMARY KEY (`id`));
  
LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/ads_platforms.csv' 
INTO TABLE ad_platforms
FIELDS TERMINATED BY ','
IGNORE 1 LINES;