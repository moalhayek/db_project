CREATE TABLE IF NOT EXISTS `bartenders` (
  `bar_id` INT NOT NULL,
  `drinker_id` INT NOT NULL,
  `start_date` DATE NULL,
  `mon_early_avg_sales` INT NULL,
  `mon_late_avg_sales` INT NULL,
  `tues_early_avg_sales` INT NULL,
  `tues_late_avg_sales` INT NULL,
  `wed_early_avg_sales` INT NULL,
  `wed_late_avg_sales` INT NULL,
  `thurs_early_avg_sales` INT NULL,
  `thurs_late_avg_sales` INT NULL,
  `fri_early_avg_sales` INT NULL,
  `fri_late_avg_sales` INT NULL,
  `sat_early_avg_sales` INT NULL,
  `sat_late_avg_sales` INT NULL,
  `sun_early_avg_sales` INT NULL,
  `sun_late_avg_sales` INT NULL,
  PRIMARY KEY (`drinker_id`),
  FOREIGN KEY (`drinker_id`) REFERENCES drinkers(`id`),
  FOREIGN KEY (`bar_id`) REFERENCES bars(`id`));

LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/db_project/code/table_generation/bartenders_table.csv'
INTO TABLE bartenders
FIELDS TERMINATED BY ','
IGNORE 1 LINES;