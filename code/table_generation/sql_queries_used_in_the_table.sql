# SQL Queries Used in the Project:

# Creation Queries
# Ad Purchases Table
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

# Ad Platforms Table
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


# Bars Table
CREATE TABLE `bars` (
  `id` INT NOT NULL,
  `name` VARCHAR(200) NULL,
  `license` VARCHAR(20) NULL,
  `state` VARCHAR(3) NULL,
  `city` VARCHAR(255) NULL,
  `street_address` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

CREATE TRIGGER `delete_bars` BEFORE DELETE ON `bars`
	FOR EACH ROW
		UPDATE frequents SET frequents.bar_id = -1
        WHERE OLD.id = frequents.bar_id;   

LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/db_project/code/table_generation/bar_table.csv' 
INTO TABLE bars
FIELDS TERMINATED BY ','
IGNORE 1 LINES;  

# Beers Table
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

# Drinkers Table
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

# Below Query verifies pattern        
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

# Frequents Table
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

# Likes Table
CREATE TABLE `likes` (
  `drinker_id` INT NOT NULL,
  `beer_id` INT NOT NULL,
  FOREIGN KEY (`drinker_id`) REFERENCES drinkers(`id`),
  FOREIGN KEY (`beer_id`) REFERENCES beers(`id`)
);

LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/likes_table.csv' 
INTO TABLE likes
FIELDS TERMINATED BY ','
IGNORE 1 LINES;

# Listens Table
CREATE TABLE `listens` (
  `drinker_id` INT NOT NULL,
  `music_id` INT NOT NULL,
  FOREIGN KEY (`drinker_id`) REFERENCES drinkers(`id`),
  FOREIGN KEY (`music_id`) REFERENCES music(`id`)
);

LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/listens_table.csv' 
INTO TABLE listens
FIELDS TERMINATED BY ','
IGNORE 1 LINES;

ALTER TABLE listens DROP FOREIGN KEY listens_ibfk_1;
ALTER TABLE listens
ADD CONSTRAINT listens_ibfk_1
FOREIGN KEY (drinker_id) REFERENCES drinkers (id) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE listens DROP FOREIGN KEY listens_ibfk_2;
ALTER TABLE listens
ADD CONSTRAINT listens_ibfk_2
FOREIGN KEY (music_id) REFERENCES music (id) ON DELETE CASCADE ON UPDATE CASCADE;

# Music Table
CREATE TABLE IF NOT EXISTS `music` (
  `id` INT NOT NULL,
  `genre` VARCHAR(200) NULL,
  PRIMARY KEY (`id`));

LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/music_table.csv' 
INTO TABLE music
FIELDS TERMINATED BY ','
IGNORE 1 LINES;  

# Sells Table
CREATE TABLE IF NOT EXISTS sells (
  bar_id INT NOT NULL,
  beer_id INT NULL,
  is_on_tap BOOLEAN NULL,
  price INT NULL,
  PRIMARY KEY (bar_id,`beer_id`));
  
LOAD DATA LOCAL INFILE '/Users/Brian/Documents/School/Rutgers University/Senior Year/Fall 2017/Principles of Info & Data Management/Project/db_project/code/table_generation/sells_table.csv' 
INTO TABLE sells
FIELDS TERMINATED BY ','
IGNORE 1 LINES;

CREATE TRIGGER minPrice
BEFORE INSERT ON sells
FOR EACH ROW
  BEGIN
    IF NEW.price <= (SELECT manf_price
                     FROM beers
                     WHERE beers.id = NEW.beer_id) THEN
      SIGNAL SQLSTATE '45000'
      SET MESSAGE_TEXT = 'Cannot sell beer for less than you buy it for';
    END IF;
  END;

# Transactions Table
CREATE TABLE `transactions` (
  `bar_id` INT NOT NULL,
  `employee_id` INT NOT NULL,
  `date_of_sale` DATE NOT NULL,
  `day_of_week` VARCHAR(3),
  `shift_type` VARCHAR(5),
  `beer_id` INT NOT NULL,
  `sale_price` INT NOT NULL,
  FOREIGN KEY (`bar_id`) REFERENCES bars(`id`),
  FOREIGN KEY (`employee_id`) REFERENCES drinkers(`id`),
  FOREIGN KEY (`beer_id`) REFERENCES beers(`id`)
);

CREATE INDEX transIndex on transactions(bar_id, date_of_sale, shift_type);

LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/transactions_table.csv'
INTO TABLE transactions
FIELDS TERMINATED BY ','
IGNORE 1 LINES;

# -------------------------------------------------------------------------------------------------------------------
# Queries used by the UI:
SELECT p.bar_id, a.platform, p.start_date, p.end_date,(DATEDIFF(p.end_date, p.start_date)*a.cost_per_day) AS total_cost, p.total_clicks, (DATEDIFF(p.end_date, p.start_date)*a.cost_per_day)/p.total_clicks AS CPC 
FROM ad_purchases p INNER JOIN ad_platforms a ON (p.platform_id = a.id) 
WHERE p.bar_id = %d;

SELECT name, id FROM bars;

DELETE FROM bars WHERE id = %d;

INSERT INTO bartenders (bar_id, drinker_id, start_date) VALUES (%d, %d, '%s');

Select t2.name, AVG(t2.total_sales) as avg_sales, t2.shift_type, t2.day_of_week
                            From (Select d.name, SUM(t.sale_price) as total_sales, t.shift_type, DAYOFWEEK(t.date_of_sale) as day_of_week
                                  From transactions t INNER JOIN drinkers d ON t.employee_id = d.id
                                  Where t.bar_id = %d
                                  Group by day_of_week, MONTH(t.date_of_sale), YEAR(t.date_of_sale), shift_type) as t2
                            Group by t2.name, t2.shift_type, t2.day_of_week;

SELECT b.name, b.manf, s.is_on_tap, b.abv, s.price, b.manf_price,
                    ROUND((s.price - b.manf_price),2) AS profitPerBottle,
                    COUNT(t.beer_id) AS total_sold,
                    ROUND((COUNT(t.beer_id)*(s.price - b.manf_price)),2) AS total_profit
                    FROM beers b INNER JOIN sells s INNER JOIN transactions t ON (t.bar_id = s.bar_id AND s.beer_id = b.id AND t.beer_id = s.beer_id)
                    WHERE s.bar_id = %d
                    GROUP BY b.name;

SELECT d.name, d.spending_per_night, b.name AS beer_name
                    FROM drinkers d INNER JOIN frequents f INNER JOIN likes l INNER JOIN beers b INNER JOIN sells s ON (d.id = f.drinker_id AND l.drinker_id = d.id AND l.beer_id = b.id AND s.beer_id = b.id AND s.bar_id = f.bar_id)
                    WHERE f.bar_id = %d AND b.name = (SELECT b1.name
                                                       FROM sells s1 INNER JOIN likes l1 INNER JOIN beers b1 ON (l1.beer_id = b1.id AND s1.beer_id = l1.beer_id)
                                                       WHERE s1.beer_id = b1.id AND s1.bar_id = f.bar_id AND l1.drinker_id = f.drinker_id
                                                       ORDER BY s1.price DESC
                                                       LIMIT 1)
                    ORDER BY d.spending_per_night desc;

INSERT INTO drinkers (name, age, gender, street_address, city, state, salary, spending_per_night, crowding_pref, relationship_status)
                    VALUES ('%s', %d, '%s', '%s', '%s', '%s', %d, %d, '%s', '%s');

SELECT age, avg(salary) 
FROM drinkers 
GROUP BY age;

Select * From drinkers where id = %d;

SELECT m1.genre, COUNT(*) as number_of_people
FROM listens l1 INNER JOIN music m1 INNER JOIN frequents f1
    ON (l1.music_id = m1.id AND l1.drinker_id = f1.drinker_id)
WHERE f1.bar_id = 1
GROUP BY l1.music_id;

SELECT t1.day_of_week, AVG(t1.total_revenue) as early_daily_average, AVG(t2.total_revenue) as late_daily_average
                    From (SELECT DAYOFWEEK(date_of_sale) as day_of_week, MONTH(date_of_sale) as month, YEAR(date_of_sale) as year,  SUM(sale_price) as total_revenue
                          FROM transactions
                          WHERE bar_id = %d
                                AND date_of_sale >= '%s'
                                AND date_of_sale <= '%s'
                                AND shift_type = 'early'
                          GROUP BY day_of_week, month, year) as t1
                          INNER JOIN
                          (SELECT DAYOFWEEK(date_of_sale) as day_of_week, MONTH(date_of_sale) as month, YEAR(date_of_sale) as year,  SUM(sale_price) as total_revenue
                           FROM transactions
                           WHERE bar_id = %d
                                 AND date_of_sale >= '%s'
                                 AND date_of_sale <= '%s'
                                 AND shift_type = 'late'
                           GROUP BY day_of_week, month, year) as t2
                          ON t1.day_of_week = t2.day_of_week
                    Group by t1.day_of_week;
























