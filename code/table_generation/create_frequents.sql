CREATE TABLE 'frequents' (
  'drinker_id' INT NOT NULL,
  'bar_id' INT NOT NULL,
  FOREIGN KEY ('drinker_id') REFERENCES drinkers('id'),
  FOREIGN KEY ('bar_id') REFERENCES bars('id')
);