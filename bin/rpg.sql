-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generacion: 30-09-2024 a las 19:56:35
-- Version del servidor: 10.4.28-MariaDB
-- Version de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `rpg`
--
CREATE DATABASE IF NOT EXISTS rpg;
USE rpg;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enemigos`
--

CREATE TABLE `enemigos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `raza` varchar(100) NOT NULL,
  `fuerza` int(11) NOT NULL,
  `defensa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `enemigos`
--


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipamiento`
--

CREATE TABLE `equipamiento` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fuerza` int(11) NOT NULL,
  `defensa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personajes`
--

CREATE TABLE `personajes` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `raza` varchar(100) NOT NULL,
  `fuerza` int(11) NOT NULL,
  `defensa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personajes`
--

INSERT INTO `personajes` (`id`, `nombre`, `raza`, `fuerza`, `defensa`) VALUES
(74, 'Tharok el Temerario', 'Orco', 60, 40),
(75, 'Elowen la Sabia', 'Elfa', 35, 65),
(76, 'Karnak mano de Acero', 'Humano', 55, 45),
(77, 'Zyra Corazon de Hielo', 'Hada', 25, 75),
(78, 'Ragnar el Imparable', 'Gigante', 70, 30),
(79, 'Lira la Sombria', 'Drow', 40, 60),
(80, 'Darius el Vengador', 'Demonio', 50, 50),
(81, 'Nyx la Silenciosa', 'Vampiro', 45, 55),
(82, 'Grum el Destructor', 'Troll', 65, 35),
(83, 'Aria Cantaestrellas', 'Sirena', 30, 70);

--
-- indices para tablas volcadas
--

--
-- Indices de la tabla `enemigos`
--
ALTER TABLE `enemigos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `equipamiento`
--
ALTER TABLE `equipamiento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `personajes`
--
ALTER TABLE `personajes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `enemigos`
--
ALTER TABLE `enemigos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT de la tabla `equipamiento`
--
ALTER TABLE `equipamiento`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `personajes`
--
ALTER TABLE `personajes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=97;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
