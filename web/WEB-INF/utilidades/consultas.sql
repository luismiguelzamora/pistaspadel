-- SELECT horas disponibles relacionado con reservas
SELECT 
`horario`.`id` AS `iddiahora`, 
`horario`.`dia`, 
`horario`.`hora`, 
`pr`.`id` AS `idreserva`,
`pr`.`usuario_id` AS `usuarioid`,
`pr`.`fecha_reserva` AS `fechareserva`
FROM
`horario` 
LEFT OUTER JOIN
(SELECT * FROM `reservas` WHERE `reservas`.`pista_id` = 1 AND `reservas`.`fecha_reserva` > '2014-03-12 18:00:00.0') AS `pr`
ON `horario`.`id` = `pr`.`horario_id`
ORDER BY `horario`.`id` ASC
LIMIT 0,98;

SELECT `horario`.`id` AS `iddiahora`, `horario`.`dia`, `horario`.`hora`, 
`pr`.`id` AS `idreserva`, `pr`.`usuario_id` AS `usuarioid`, 
`pr`.`fecha_reserva` AS `fechareserva` 
FROM `horario` LEFT OUTER JOIN  
(SELECT * FROM `reservas` WHERE `reservas`.`pista_id` = 1 
AND `reservas`.`fecha_reserva` >  '2013-03-12 18:00:00.0') AS `pr` 
ON `horario`.`id` = `pr`.`horario_id` ORDER BY `horario`.`id` ASC  LIMIT 0,98;


SELECT * FROM `reservas`;
