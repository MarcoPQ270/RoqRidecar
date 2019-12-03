-- phpMyAdmin SQL Dump
-- version 3.4.4
-- http://www.phpmyadmin.net
--
-- Servidor: mysql.webcindario.com
-- Tiempo de generación: 03-12-2019 a las 05:59:17
-- Versión del servidor: 5.6.39
-- Versión de PHP: 5.6.40-13+0~20191026.23+debian9~1.gbp37e45b

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `servicesroqride`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `peticion`
--

CREATE TABLE IF NOT EXISTS `peticion` (
  `nocontrol` char(8) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `carrera` varchar(80) DEFAULT NULL,
  `semestre` char(2) DEFAULT NULL,
  `controlchofer` char(16) DEFAULT NULL,
  PRIMARY KEY (`nocontrol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `nocontrol` char(8) NOT NULL,
  `nomest` varchar(50) DEFAULT NULL,
  `correo` varchar(50) DEFAULT NULL,
  `nip` char(4) DEFAULT NULL,
  `carrera` varchar(50) DEFAULT NULL,
  `semestre` char(2) DEFAULT NULL,
  PRIMARY KEY (`nocontrol`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`nocontrol`, `nomest`, `correo`, `nip`, `carrera`, `semestre`) VALUES
('12365478', 'Juan Nito', 'juannito@gmail.com', '1234', 'tics', '7');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `viajes`
--

CREATE TABLE IF NOT EXISTS `viajes` (
  `iddestino` int(11) NOT NULL AUTO_INCREMENT,
  `destino` varchar(50) DEFAULT NULL,
  `horas` char(5) DEFAULT NULL,
  `nota` varchar(300) DEFAULT NULL,
  `nocontrol` char(8) DEFAULT NULL,
  `nomest` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`iddestino`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Volcado de datos para la tabla `viajes`
--

INSERT INTO `viajes` (`iddestino`, `destino`, `horas`, `nota`, `nocontrol`, `nomest`) VALUES
(15, 'Celaya', '12:50', 'Te veo en la café para ponernos de acuerdo MR, Ruvalcaba ', '16980598', 'Marco Velazquez PQ');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
