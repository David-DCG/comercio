-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         8.0.37 - MySQL Community Server - GPL
-- SO del servidor:              Win64
-- HeidiSQL Versión:             12.7.0.6850
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para bd_comercio
CREATE DATABASE IF NOT EXISTS `bd_comercio` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bd_comercio`;

-- Volcando estructura para tabla bd_comercio.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `correo` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `celular` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_comercio.clientes: ~2 rows (aproximadamente)
INSERT INTO `clientes` (`id`, `nombres`, `apellidos`, `correo`, `celular`) VALUES
	(1, 'Andres', 'Mamani', 'andres@mail.com', 7984618),
	(2, 'Ana', 'Ramos', 'ana@mail.com', 7000146),
	(3, 'Juan', 'Casas', 'juan@mail.com', 74226365),
	(4, 'Carlos', 'Callisaya', 'carlos@mail.com', 72536484),
	(5, 'Altair', 'Carrillo', 'altair@mail.com', 7125483);

-- Volcando estructura para tabla bd_comercio.componentes
CREATE TABLE IF NOT EXISTS `componentes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `precio` double NOT NULL,
  `ruta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_comercio.componentes: ~2 rows (aproximadamente)
INSERT INTO `componentes` (`id`, `nombre`, `tipo`, `marca`, `descripcion`, `precio`, `ruta`) VALUES
	(1, 'i7 14700k', 'Procesador', 'Intel', 'Total de subprocesos 28', 419, 'uploads/i7 14700k.jpg'),
	(2, 'i5 14600k', 'Procesador', 'Intel', 'Total de subprocesos 20', 329, 'uploads/i5 14600k.jpg'),
	(3, 'i7 12700k', 'Procesador', 'Intel', 'Total de subprocesos 20', 460, 'uploads/i7 12700k.jpg');

-- Volcando estructura para tabla bd_comercio.compras
CREATE TABLE IF NOT EXISTS `compras` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_cli` int NOT NULL,
  `id_com` int NOT NULL,
  `id_per` int NOT NULL,
  `fecha` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__clientes` (`id_cli`),
  KEY `FK__componentes` (`id_com`),
  KEY `FK__perifericos` (`id_per`),
  CONSTRAINT `FK__clientes` FOREIGN KEY (`id_cli`) REFERENCES `clientes` (`id`),
  CONSTRAINT `FK__componentes` FOREIGN KEY (`id_com`) REFERENCES `componentes` (`id`),
  CONSTRAINT `FK__perifericos` FOREIGN KEY (`id_per`) REFERENCES `perifericos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_comercio.compras: ~1 rows (aproximadamente)
INSERT INTO `compras` (`id`, `id_cli`, `id_com`, `id_per`, `fecha`) VALUES
	(2, 5, 3, 1, '2024-06-23');

-- Volcando estructura para tabla bd_comercio.perifericos
CREATE TABLE IF NOT EXISTS `perifericos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `tipo` varchar(50) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `descripcion` varchar(50) NOT NULL,
  `precio` double NOT NULL,
  `ruta` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_comercio.perifericos: ~3 rows (aproximadamente)
INSERT INTO `perifericos` (`id`, `nombre`, `tipo`, `marca`, `descripcion`, `precio`, `ruta`) VALUES
	(1, 'HYPERX ALLOY ORIGINS RED', 'Teclado', 'HYPERX', 'Teclas mecanicas HyperX', 136.29, 'uploads/HyperX ALLOY ORIGINS RED.png'),
	(2, 'ROG STRIX IMPACT P303', 'Mouse', 'Asus', 'ROG Strix Impact', 62.69, 'uploads/ROG STRIX IMPACT P303.png'),
	(3, 'TUF GAMING HEADSETS H3', 'Audifono', 'Asus', 'Sonido envolvente virtual 7.1', 144.2, 'uploads/TUF GAMING HEADSETS H3 WIRELES.png');

-- Volcando estructura para tabla bd_comercio.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_comercio.usuarios: ~0 rows (aproximadamente)
INSERT INTO `usuarios` (`id`, `nombres`, `apellidos`, `correo`, `password`) VALUES
	(1, 'David', 'Gomez', 'david@mail.com', '123456'),
	(2, 'Daniel', 'Condori', 'daniel@mail.com', '123456'),
	(3, 'Julio', 'Condori', 'julio@mail.com', '123456');

-- Volcando estructura para tabla bd_comercio.ventas
CREATE TABLE IF NOT EXISTS `ventas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_usr` int NOT NULL,
  `id_com` int NOT NULL,
  `id_per` int NOT NULL,
  `fecha` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK__usr` (`id_usr`),
  KEY `FK__com` (`id_com`),
  KEY `FK__per` (`id_per`),
  CONSTRAINT `FK__com` FOREIGN KEY (`id_com`) REFERENCES `componentes` (`id`),
  CONSTRAINT `FK__per` FOREIGN KEY (`id_per`) REFERENCES `perifericos` (`id`),
  CONSTRAINT `FK__usr` FOREIGN KEY (`id_usr`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Volcando datos para la tabla bd_comercio.ventas: ~1 rows (aproximadamente)
INSERT INTO `ventas` (`id`, `id_usr`, `id_com`, `id_per`, `fecha`) VALUES
	(2, 1, 3, 3, '2024-06-23');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
