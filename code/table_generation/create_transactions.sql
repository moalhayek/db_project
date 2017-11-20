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

LOAD DATA LOCAL INFILE '/Users/moalhayek/Documents/Rutgers/Year 4/Databases/transactions_table.csv'
INTO TABLE transactions
FIELDS TERMINATED BY ','
IGNORE 1 LINES;