CREATE TABLE IF NOT EXISTS `ad_purchases` (
  `bar_id` INT NOT NULL,
  `platform_id` INT NULL,
  `start_date` DATE NULL,
  `end_date` DATE NULL,
  `total_clicks` INT NULL,
  PRIMARY KEY (`bar_id`, `platform_id`));

LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/db_project/code/table_generation/ad_purchases_table.csv'
INTO TABLE ad_purchases
FIELDS TERMINATED BY ','
IGNORE 1 LINES;