CREATE TABLE `drinkers` (
  `id` INT NOT NULL AUTO_INCREMENT,
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

INSERT INTO drinkers (id) values (-1);

Create TRIGGER `delete_drinker` BEFORE DELETE ON `drinkers`
	FOR EACH ROW
		UPDATE frequents SET frequents.drinker_id = -1
        WHERE OLD.id = frequents.drinker_id;   
        
Select * 
From drinkers d
Where Exists(select d1.spending_per_night from drinkers d1 where d1.spending_per_night >= (d1.salary/(365*2)));

ALTER TABLE drinkers MODIFY id INT(11) NOT NULL AUTO_INCREMENT;

CREATE TRIGGER AlcoholicTrig
  BEFORE INSERT ON drinkers
  FOR EACH ROW
    BEGIN
      IF NEW.spending_per_night >= (NEW.salary/(365*2)) THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cannot add drinker, he is an alcoholic';
      ELSEIF NEW.age < 21 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Cannot add drinker, he is under the legal drinking age';
      END IF;
    END;