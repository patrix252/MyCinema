DROP PROCEDURE inserisciPosti;

DELIMITER $$
CREATE PROCEDURE inserisciPosti ()
BEGIN
DECLARE i INT;
declare j int;
declare z int;
set z = 1;
set i = 1;
set j = 1;
while z<5 do
while i<12 do
while j<13 do
 IF i != 5 then
    INSERT INTO myCinema.Posto (id_sala,riga,colonna,esiste) VALUES (z, i, j, 1);
 else
    INSERT INTO myCinema.Posto (id_sala,riga,colonna,esiste) VALUES (z, i, j, 0);
end if;
set j = j+1;
end while;
set j = 1;
set i = i+1;
end while;
set j=1;
set i=1;
set z = z+1;
end while;
END $$

DELIMITER ;

call inserisciPosti()