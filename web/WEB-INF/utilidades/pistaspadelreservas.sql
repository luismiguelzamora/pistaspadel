-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 11-03-2014 a las 16:01:12
-- Versión del servidor: 5.6.12-log
-- Versión de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `pistaspadelreservas`
--
CREATE DATABASE IF NOT EXISTS `pistaspadelreservas` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `pistaspadelreservas`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE IF NOT EXISTS `horario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dia` enum('Lunes','Martes','Miercoles','Jueves','Viernes','Sabado','Domingo') COLLATE utf8_spanish_ci DEFAULT NULL,
  `hora` enum('09:00-10:00','10:00-11:00','11:00-12:00','12:00-13:00','13:00-14:00','14:00-15:00','15:00-16:00','16:00-17:00','17:00-18:00','18:00-19:00','19:00-20:00','20:00-21:00','21:00-22:00','22:00-23:00') COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=99 ;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`id`, `dia`, `hora`) VALUES
(1, 'Lunes', '09:00-10:00'),
(2, 'Lunes', '10:00-11:00'),
(3, 'Lunes', '11:00-12:00'),
(4, 'Lunes', '12:00-13:00'),
(5, 'Lunes', '13:00-14:00'),
(6, 'Lunes', '14:00-15:00'),
(7, 'Lunes', '15:00-16:00'),
(8, 'Lunes', '16:00-17:00'),
(9, 'Lunes', '17:00-18:00'),
(10, 'Lunes', '18:00-19:00'),
(11, 'Lunes', '19:00-20:00'),
(12, 'Lunes', '20:00-21:00'),
(13, 'Lunes', '21:00-22:00'),
(14, 'Lunes', '22:00-23:00'),
(15, 'Martes', '09:00-10:00'),
(16, 'Martes', '10:00-11:00'),
(17, 'Martes', '11:00-12:00'),
(18, 'Martes', '12:00-13:00'),
(19, 'Martes', '13:00-14:00'),
(20, 'Martes', '14:00-15:00'),
(21, 'Martes', '15:00-16:00'),
(22, 'Martes', '16:00-17:00'),
(23, 'Martes', '17:00-18:00'),
(24, 'Martes', '18:00-19:00'),
(25, 'Martes', '19:00-20:00'),
(26, 'Martes', '20:00-21:00'),
(27, 'Martes', '21:00-22:00'),
(28, 'Martes', '22:00-23:00'),
(29, 'Miercoles', '09:00-10:00'),
(30, 'Miercoles', '10:00-11:00'),
(31, 'Miercoles', '11:00-12:00'),
(32, 'Miercoles', '12:00-13:00'),
(33, 'Miercoles', '13:00-14:00'),
(34, 'Miercoles', '14:00-15:00'),
(35, 'Miercoles', '15:00-16:00'),
(36, 'Miercoles', '16:00-17:00'),
(37, 'Miercoles', '17:00-18:00'),
(38, 'Miercoles', '18:00-19:00'),
(39, 'Miercoles', '19:00-20:00'),
(40, 'Miercoles', '20:00-21:00'),
(41, 'Miercoles', '21:00-22:00'),
(42, 'Miercoles', '22:00-23:00'),
(43, 'Jueves', '09:00-10:00'),
(44, 'Jueves', '10:00-11:00'),
(45, 'Jueves', '11:00-12:00'),
(46, 'Jueves', '12:00-13:00'),
(47, 'Jueves', '13:00-14:00'),
(48, 'Jueves', '14:00-15:00'),
(49, 'Jueves', '15:00-16:00'),
(50, 'Jueves', '16:00-17:00'),
(51, 'Jueves', '17:00-18:00'),
(52, 'Jueves', '18:00-19:00'),
(53, 'Jueves', '19:00-20:00'),
(54, 'Jueves', '20:00-21:00'),
(55, 'Jueves', '21:00-22:00'),
(56, 'Jueves', '22:00-23:00'),
(57, 'Viernes', '09:00-10:00'),
(58, 'Viernes', '10:00-11:00'),
(59, 'Viernes', '11:00-12:00'),
(60, 'Viernes', '12:00-13:00'),
(61, 'Viernes', '13:00-14:00'),
(62, 'Viernes', '14:00-15:00'),
(63, 'Viernes', '15:00-16:00'),
(64, 'Viernes', '16:00-17:00'),
(65, 'Viernes', '17:00-18:00'),
(66, 'Viernes', '18:00-19:00'),
(67, 'Viernes', '19:00-20:00'),
(68, 'Viernes', '20:00-21:00'),
(69, 'Viernes', '21:00-22:00'),
(70, 'Viernes', '22:00-23:00'),
(71, 'Sabado', '09:00-10:00'),
(72, 'Sabado', '10:00-11:00'),
(73, 'Sabado', '11:00-12:00'),
(74, 'Sabado', '12:00-13:00'),
(75, 'Sabado', '13:00-14:00'),
(76, 'Sabado', '14:00-15:00'),
(77, 'Sabado', '15:00-16:00'),
(78, 'Sabado', '16:00-17:00'),
(79, 'Sabado', '17:00-18:00'),
(80, 'Sabado', '18:00-19:00'),
(81, 'Sabado', '19:00-20:00'),
(82, 'Sabado', '20:00-21:00'),
(83, 'Sabado', '21:00-22:00'),
(84, 'Sabado', '22:00-23:00'),
(85, 'Domingo', '09:00-10:00'),
(86, 'Domingo', '10:00-11:00'),
(87, 'Domingo', '11:00-12:00'),
(88, 'Domingo', '12:00-13:00'),
(89, 'Domingo', '13:00-14:00'),
(90, 'Domingo', '14:00-15:00'),
(91, 'Domingo', '15:00-16:00'),
(92, 'Domingo', '16:00-17:00'),
(93, 'Domingo', '17:00-18:00'),
(94, 'Domingo', '18:00-19:00'),
(95, 'Domingo', '19:00-20:00'),
(96, 'Domingo', '20:00-21:00'),
(97, 'Domingo', '21:00-22:00'),
(98, 'Domingo', '22:00-23:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pistas`
--

CREATE TABLE IF NOT EXISTS `pistas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(55) COLLATE utf8_spanish_ci DEFAULT NULL,
  `descripcion` varchar(250) COLLATE utf8_spanish_ci DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `pistas`
--

INSERT INTO `pistas` (`id`, `nombre`, `descripcion`, `precio`) VALUES
(1, 'Pista grande', 'Con nuevas instalaciones', '0.00'),
(2, 'Pista mediana', 'Cerca del ayuntamiento', '0.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reservas`
--

CREATE TABLE IF NOT EXISTS `reservas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `horario_id` int(11) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `pista_id` int(11) DEFAULT NULL,
  `fecha_reserva` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_horario_id` (`horario_id`),
  KEY `idx_pista_id` (`pista_id`),
  KEY `idx_usuario_id` (`usuario_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `reservas`
--

INSERT INTO `reservas` (`id`, `horario_id`, `usuario_id`, `pista_id`, `fecha_reserva`) VALUES
(2, 38, 13, 1, '2014-03-12 18:00:00'),
(3, 45, 14, 1, '2014-03-13 11:00:00'),
(4, 59, 14, 2, '2014-03-14 11:00:00'),
(5, 12, 13, 2, '2014-03-16 20:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(200) COLLATE utf8_spanish_ci DEFAULT NULL,
  `clave` varchar(250) COLLATE utf8_spanish_ci DEFAULT NULL,
  `nombre` varchar(95) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=15 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `email`, `clave`, `nombre`) VALUES
(13, 'aaaa@aaaa.aaaa', '74b87337454200d4d33f80c4663dc5e5', 'aaaa'),
(14, 'bbbb@bbbb.bbbb', '65ba841e01d6db7733e90a5b7f9e6f80', 'bbbb');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `reservas`
--
ALTER TABLE `reservas`
  ADD CONSTRAINT `reservas_ibfk_1` FOREIGN KEY (`horario_id`) REFERENCES `horario` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `reservas_ibfk_2` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `reservas_ibfk_3` FOREIGN KEY (`pista_id`) REFERENCES `pistas` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

