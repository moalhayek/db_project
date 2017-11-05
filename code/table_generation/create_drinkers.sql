CREATE TABLE `drinkers` (
  `id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  `age` INT NULL,
  `gender` VARCHAR(3) NULL,
  `street_address` VARCHAR(255) NULL,
  `city` VARCHAR(255) NULL,
  `state` VARCHAR(3) NULL,
  `salary` INT NULL,
  `spending_per_night` INT NULL CHECK(spending_per_night < salary/(365*2)),
  `crowding_pref` VARCHAR(200) NULL,
  `relationship_status` VARCHAR(200) NULL,
  PRIMARY KEY (`id`));


LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/db_project/code/table_generation/drinker_table.csv' 
INTO TABLE drinkers
FIELDS TERMINATED BY ','
IGNORE 1 LINES;


Select * 
From drinkers d
Where Exists(select d1.spending_per_night from drinkers d1 where d1.spending_per_night >= (d1.salary/(365*2)));
