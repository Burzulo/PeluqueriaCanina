-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-05-2025 a las 20:53:52
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `peluqueria_canina`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE `cita` (
  `idCita` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `motivo` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `idMascota` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`idCita`, `fecha`, `motivo`, `observaciones`, `idMascota`) VALUES
(1, '2025-05-14', 'CORTE DE PELO', 'fr', 10),
(2, '2025-05-01', 'CORTE DE PELO', 'pago en efectivo', 10),
(3, '2027-05-14', 'FULL SERVICIOS', 'viene muy sucio', 10),
(4, '2025-06-06', 'LIMPIEZA OIDOS', 'nueva visita', 10),
(5, '2026-06-26', 'BAÑO', 'esta muy sucio', 10),
(6, '2028-05-03', 'FULL SERVICIOS', 'ojo ! tiene reuma', 10),
(7, '2025-05-27', 'CORTE PELO', 'pago con tarjeta', 11),
(8, '2025-06-03', 'FULL SERVICIOS', '!', 12),
(9, '2025-06-05', 'FULL SERVICIOS', 'muy pequeña', 33),
(10, '2025-06-06', 'FULL SERVICIOS', 'paga en efectivo', 34),
(11, '2025-08-13', 'CORTE PELO', 'lo tiene muy largo', 34);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `duenio`
--

CREATE TABLE `duenio` (
  `id_duenio` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `direccion` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `duenio`
--

INSERT INTO `duenio` (`id_duenio`, `nombre`, `telefono`, `email`, `direccion`) VALUES
(1, 'GONZALO', '654395684', 'gonza@gmail.com', 'CALLE 5'),
(8, 'BENJAMIN', '351654896', 'BENJA@GMAIL.COM', 'VCP 96'),
(9, 'JUAN CARLOS', '1165894523', 'jc@hotmail.com', 'STREET VIEW 6'),
(10, 'MARIANO', '654983218', 'mariano@gmail.com', 'RIVIERAS 890'),
(11, 'MARIO', '159786356', 'maritoo@gmail.com', 'S/N'),
(12, 'GONZALO', '654395684', 'gonza@gmail.com', 'CALLE 55'),
(13, 'GON', '3564825198', 'goni@dergmail.com', 'CALLE 6'),
(14, 'Juan garay', '6846512131', 'Juan@gmail.com', 'Calle 45'),
(15, 'ESTEBAN', '685954693', 'ESTY@HOTMAIL.COM', 'PAMPLONA 23'),
(16, 'GERONIMO', '159159159', 'jero@firebase.com', 'AV. LIBERTAD 3465'),
(17, 'GERONIMO', '159159159', 'jero@firebase.com', 'AV. LIBERTAD 3465'),
(18, 'LAUTARO', '654893217', 'LAU@GMAIL.COM', 'TRIBILIN 65'),
(19, 'DAMIAN', '4599725432', 'DAMIANCC@HOTMAIL.COM', 'PUEYRREDON 14'),
(20, 'MARTA', '6897454554', 'MSC@HOTMIAL.COM', 'VALENCIAGA 452'),
(21, 'MACARENA', '9873241238', 'maca@hotmail.com', 'SABADELL 24'),
(22, 'HERNAN', '159789658', 'HGARCIA@GMAIL.COM', 'PALO ALTO 965'),
(23, 'OSVALDO', '44598721321', 'COCO@YAHOO.COM', 'INDEPENDENCIA 2'),
(24, 'OSVALDO', '15948621321', 'COCO@GMAIL.COM', 'INDEPENDENCIA 3'),
(25, 'OSVALDO', '6878414268', 'coco@yahoo.com', 'INDEPENDENCIA 3'),
(26, 'OSVALDO', '15948621321', 'COCO@GMAIL.COM', 'INDEPENDENCIA 3'),
(27, 'MANUEL', '456982135', 'manu@gmail.com', 'AV. ESPAÑA 6985'),
(28, 'LUNA', '7896512313', 'lunita@gmail.com', 'SALTA 14'),
(29, 'MIRTA', '12345679', 'AA', 'BOULEVAR 456'),
(30, 'MIRIAM', '46532159', 'miriamprt@gmail.com', 'SALTA 14'),
(31, 'ANTONELLA PEREZ', '69853214', 'ANTO@HOTMAIL.COM', 'VIAMONTE 45'),
(32, 'OLIVIA AL', '164598789', 'OLI@YAHOO.COM', 'CASA 123'),
(33, 'ASDA', '646846854', 'asda@asd.com', 'ASD'),
(34, 'DFGDF', 'dsfgsdfg', 'dfgdf@fvfv.com', 'SDFSDF'),
(35, 'BELEN', '159687456', 'belu@gmail.com', 'SALSIPUEDES 56'),
(36, 'OLIVIA', '15687753', 'olivita@gmail.com', 'VCP 568');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mascota`
--

CREATE TABLE `mascota` (
  `idMascota` int(11) NOT NULL,
  `nombreMascota` varchar(100) NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `ultima_visita` date DEFAULT NULL,
  `sexo` varchar(10) DEFAULT NULL,
  `raza` varchar(50) DEFAULT NULL,
  `color` varchar(50) DEFAULT NULL,
  `esterilizado` varchar(10) DEFAULT NULL,
  `alergia` varchar(100) DEFAULT NULL,
  `medicacion` varchar(100) DEFAULT NULL,
  `cirugia` varchar(100) DEFAULT NULL,
  `pesoActual` double DEFAULT NULL,
  `id_duenio` int(11) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mascota`
--

INSERT INTO `mascota` (`idMascota`, `nombreMascota`, `fecha_nacimiento`, `ultima_visita`, `sexo`, `raza`, `color`, `esterilizado`, `alergia`, `medicacion`, `cirugia`, `pesoActual`, `id_duenio`, `observaciones`) VALUES
(1, 'YONI', '1984-04-10', '2025-01-21', 'MACHO', 'CALLEJERO', 'NEGRO', 'NO', '', 'NO', '', 8.7, 1, 'NINGUNA'),
(8, 'CIRO', '2025-04-10', '2025-04-30', 'MACHO', 'LABRADOR', 'BEIGE', 'SI', 'POLEN', 'NO', 'TESTICULOS', 8.9, 8, 'NINGUNA'),
(9, 'CANELA', '1983-02-01', '2025-04-23', 'HEMBRA', 'DALMATA', 'BLANCO Y NEGRO', 'NO', 'NO', 'HEPATALGINA', '', 9.2, 9, ''),
(10, 'BOBI', '2009-08-21', '2025-05-21', 'MACHO', 'CANICHE TOY', 'MARRON Y BLANCO', 'NO', 'PELO DE GATO', 'NO', 'NO', 2.3, 10, 'CARACTER MALO'),
(11, 'KIKI', '2022-12-07', '2025-05-01', 'MACHO', 'CANICHE TOY', 'GRIS CENIZA', 'NO', 'NO', 'NO', 'NO', 5.32, 11, 'NINGUNA'),
(12, 'CHULETA', '2025-05-20', '2025-05-21', 'MACHO', 'BOXER', 'MARRON', 'NO', 'NO', 'NO', 'NO', 5, 12, 'NO'),
(13, 'FIRULAIS', '2025-01-06', '2025-05-03', 'HEMBRA', 'COCKER', 'NEGRO Y BLANCO', 'SI', 'NO', '', 'CADERA', 4.23, 13, ''),
(14, 'Firulo', '2022-05-06', '2025-05-04', 'Macho', 'Cocker', 'Negro y Gris', 'Si', NULL, NULL, NULL, 8.5, 14, NULL),
(15, 'CHULETA', '2020-10-14', '2025-05-05', 'MACHO', 'CALLE', 'NEGRO', 'SI', 'NO', 'NO', 'NO', 9.5, 15, 'NO PUEDE ESTAR EN GRUPO'),
(16, 'PIRUCHA', '2023-11-09', '2025-05-21', 'HEMBRA', 'CANICHE', 'BLANCO', 'SI', 'NO', 'NO', 'NO', 3, 16, ''),
(17, 'PIRATA', '2023-11-09', '2025-03-31', 'HEMBRA', 'CANICHE', 'BLANCO', 'SI', 'SI', 'NO', 'NO', 2.59, 17, 'GTG'),
(18, 'PATITA', '2024-05-07', '2025-05-19', 'MACHO', 'BULLDOG FRANCES', 'BEIGE Y NEGRO', 'SI', 'NO', 'NO', 'NO', 2.35, 18, 'NO'),
(19, 'MANCHITA', '2024-11-25', '2025-05-22', 'HEMBRA', 'PASTOR ALEMAN', 'NEGRO Y MARRON', 'SI', 'SI. POLEN', 'NO', 'NO', 6.875, 19, NULL),
(20, 'PEPONA', '2024-06-03', '2025-05-21', 'Hembra', 'PUG', 'GRIS', 'Si', 'no', 'no', 'NO', 2.5, 20, NULL),
(21, 'POCHITO', '2024-09-30', '2025-05-22', 'MACHO', 'CHIHUAHUA', 'NEGRO', 'NO', 'NO', 'NO', 'NO', 2.2, 21, ''),
(22, 'POCHO', '2020-09-11', NULL, 'MACHO', 'PUG', 'MESTIZO', 'SI', NULL, NULL, NULL, 9.32, 22, NULL),
(23, 'MALAMBO', '2003-04-30', NULL, 'MACHO', 'LAZZY', 'NEGRO', 'NO', 'PENICILINA', 'NO', 'NO', 5.35, 25, 'NO'),
(24, 'PUCHO', '2014-05-08', NULL, 'MACHO', 'LAZZY', 'NEGRO', 'NO', NULL, NULL, NULL, 8.6, 26, NULL),
(25, 'COLITA', '2025-05-03', NULL, 'MACHO', 'OVEJERO', 'MARRON', 'SI', 'NO', 'NO', 'NO', 3, 27, 'NO'),
(26, 'MAGO', '2024-11-04', NULL, 'MACHO', 'GRAN DANES', 'CHOCOLATE', 'SI', 'NO', 'NO', 'NO', 9.5, 28, 'TIENE ARTRITIS EN LAS PATAS TRASERAS'),
(27, 'PIPO', '2021-05-07', NULL, 'MACHO', 'DOGO', 'BLANCO Y NEGRO', 'SI', NULL, NULL, NULL, 6.5, 29, NULL),
(28, 'GALA', '2021-04-28', NULL, 'HEMBRA', 'PUG', 'MARRON', 'SI', 'NO', 'NO', 'CADERA', 2.3, 30, 'NADA'),
(29, 'PAQUITA', '2008-05-08', NULL, 'HEMBRA', 'SALCHICHA', 'NEGRA', 'SI', NULL, NULL, NULL, 3.25, 31, NULL),
(30, 'CANI', '2025-02-05', NULL, 'HEMBRA', 'COCKER', 'BLANCO', 'NO', NULL, NULL, NULL, 3.2, 32, NULL),
(31, 'ASD', '2025-05-03', NULL, 'MACHO', 'ASDASD', 'ASDASD', 'SI', 'ASD', 'ASD', 'ASD', 3, 33, 'ASD'),
(32, 'HFRTHS', '2024-09-02', NULL, 'MACHO', 'DSFHDSFH', 'DFHDFH', 'SI', 'NO', 'NO', 'NO', 5, 34, 'NO'),
(33, 'PERLITA', '2025-04-28', NULL, 'HEMBRA', 'BEAGLE', 'BLANCO Y NEGRO', 'SI', 'NO', 'NO', 'NO', 2.5, 35, 'MUY CACHORRITA'),
(34, 'FELIX', '2025-03-31', NULL, 'MACHO', 'GOLDEN', 'RUBIO', 'NO', 'NO', 'HEPATALGINA', 'NO', 5.5, 36, 'ES MUY CHIQUITO');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cita`
--
ALTER TABLE `cita`
  ADD PRIMARY KEY (`idCita`),
  ADD KEY `FKcruf41opm7lpj2k596kv8hhst` (`idMascota`);

--
-- Indices de la tabla `duenio`
--
ALTER TABLE `duenio`
  ADD PRIMARY KEY (`id_duenio`);

--
-- Indices de la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD PRIMARY KEY (`idMascota`),
  ADD KEY `id_duenio` (`id_duenio`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cita`
--
ALTER TABLE `cita`
  MODIFY `idCita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `duenio`
--
ALTER TABLE `duenio`
  MODIFY `id_duenio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `mascota`
--
ALTER TABLE `mascota`
  MODIFY `idMascota` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cita`
--
ALTER TABLE `cita`
  ADD CONSTRAINT `FKcruf41opm7lpj2k596kv8hhst` FOREIGN KEY (`idMascota`) REFERENCES `mascota` (`idMascota`);

--
-- Filtros para la tabla `mascota`
--
ALTER TABLE `mascota`
  ADD CONSTRAINT `mascota_ibfk_1` FOREIGN KEY (`id_duenio`) REFERENCES `duenio` (`id_duenio`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
