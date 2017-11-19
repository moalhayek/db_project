DROP TRIGGER delete_bars;

CREATE TRIGGER delete_bars
BEFORE DELETE ON bars
FOR EACH ROW
  UPDATE frequents SET frequents.bar_id = -1
        WHERE OLD.id = frequents.bar_id;
