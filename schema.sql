-- MariaDB dump 10.17  Distrib 10.5.5-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: controleEstoque
-- ------------------------------------------------------
-- Server version	10.5.5-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Estoque`
--

DROP TABLE IF EXISTS `Estoque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Estoque` (
  `idEstoque` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idEstoque`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Fornecedor`
--

DROP TABLE IF EXISTS `Fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fornecedor` (
  `idFornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `telefone1` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `telefone2` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idFornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Ingrediente`
--

DROP TABLE IF EXISTS `Ingrediente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Ingrediente` (
  `idIngrediente` int(11) NOT NULL AUTO_INCREMENT,
  `idProduto` int(11) NOT NULL,
  `idProdutoFazParte` int(11) NOT NULL,
  `quantidadeRelativa` double NOT NULL,
  PRIMARY KEY (`idIngrediente`),
  KEY `FK_Ingrediente_Produto` (`idProduto`),
  KEY `FK_Ingrediente_ProdutoFazParte` (`idProdutoFazParte`),
  CONSTRAINT `FK_Ingrediente_Produto` FOREIGN KEY (`idProduto`) REFERENCES `Produto` (`idProduto`),
  CONSTRAINT `FK_Ingrediente_ProdutoFazParte` FOREIGN KEY (`idProdutoFazParte`) REFERENCES `Produto` (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `InstanciaProduto`
--

DROP TABLE IF EXISTS `InstanciaProduto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `InstanciaProduto` (
  `idInstanciaProduto` int(11) NOT NULL AUTO_INCREMENT,
  `idProduto` int(11) NOT NULL,
  `quantidade` int(11) NOT NULL,
  `valorUnitarioPago` double DEFAULT NULL,
  `valorUnitarioVenda` double DEFAULT NULL,
  `idMovimentacao` int(11) DEFAULT NULL,
  `idEstoque` int(11) DEFAULT NULL,
  `categoria` int(11) NOT NULL,
  PRIMARY KEY (`idInstanciaProduto`),
  KEY `FK_InstanciaProduto_Produto` (`idProduto`),
  KEY `FK_InstanciaProduto_Movimentacao` (`idMovimentacao`),
  KEY `FK_InstanciaProduto_Estoque` (`idEstoque`),
  CONSTRAINT `FK_InstanciaProduto_Estoque` FOREIGN KEY (`idEstoque`) REFERENCES `Estoque` (`idEstoque`),
  CONSTRAINT `FK_InstanciaProduto_Movimentacao` FOREIGN KEY (`idMovimentacao`) REFERENCES `Movimentacao` (`idMovimentacao`),
  CONSTRAINT `FK_InstanciaProduto_Produto` FOREIGN KEY (`idProduto`) REFERENCES `Produto` (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Movimentacao`
--

DROP TABLE IF EXISTS `Movimentacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Movimentacao` (
  `idMovimentacao` int(11) NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `tipoMovimentacao` int(11) NOT NULL,
  `idEstoqueOrigem` int(11) DEFAULT NULL,
  `idEstoqueDestino` int(11) DEFAULT NULL,
  `idFornecedor` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMovimentacao`),
  KEY `FK_Movimentacao_EstoqueOrigem` (`idEstoqueOrigem`),
  KEY `FK_Movimentacao_EstoqueDestino` (`idEstoqueDestino`),
  KEY `FK_Movimentacao_Fornecedor` (`idFornecedor`),
  CONSTRAINT `FK_Movimentacao_EstoqueDestino` FOREIGN KEY (`idEstoqueDestino`) REFERENCES `Estoque` (`idEstoque`),
  CONSTRAINT `FK_Movimentacao_EstoqueOrigem` FOREIGN KEY (`idEstoqueOrigem`) REFERENCES `Estoque` (`idEstoque`),
  CONSTRAINT `FK_Movimentacao_Fornecedor` FOREIGN KEY (`idFornecedor`) REFERENCES `Fornecedor` (`idFornecedor`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `Produto`
--

DROP TABLE IF EXISTS `Produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Produto` (
  `idProduto` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `valorUnitarioPago` double DEFAULT NULL,
  `valorUnitarioVenda` double DEFAULT NULL,
  `unidadeDeMedida` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `quantidadeNaEmbalagem` double NOT NULL,
  `produzidoNaPadaria` tinyint(1) NOT NULL,
  PRIMARY KEY (`idProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-29 15:11:57
