-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 09, 2022 at 07:52 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wakalni`
--

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `commandeid` int(11) NOT NULL AUTO_INCREMENT,
  `datecreation` datetime NOT NULL,
  `dateexpedition` datetime NOT NULL,
  `datearrivee` datetime NOT NULL,
  `clientid` int(11) NOT NULL,
  `livreurid` int(11) NOT NULL,
  `rcid` int(11) NOT NULL,
  `panierid` int(11) NOT NULL,
  `nomclient` varchar(11) NOT NULL,
  `nomlivreur` varchar(11) NOT NULL,
  `nomresto` varchar(11) NOT NULL,
  PRIMARY KEY (`commandeid`),
  KEY `fkclient` (`clientid`),
  KEY `fklivreur` (`livreurid`),
  KEY `fkrc` (`rcid`),
  KEY `fkpanier` (`panierid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `leftovers`
--

DROP TABLE IF EXISTS `leftovers`;
CREATE TABLE IF NOT EXISTS `leftovers` (
  `leftoverid` int(11) NOT NULL AUTO_INCREMENT,
  `sujet` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `quantite` int(11) NOT NULL,
  `dateexpiration` datetime NOT NULL,
  `chefrestoid` int(11) NOT NULL,
  PRIMARY KEY (`leftoverid`),
  KEY `fkleftover` (`chefrestoid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `offre`
--

DROP TABLE IF EXISTS `offre`;
CREATE TABLE IF NOT EXISTS `offre` (
  `offreid` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `prix` float NOT NULL,
  `crid` int(11) NOT NULL,
  `datefin` datetime NOT NULL,
  PRIMARY KEY (`offreid`),
  KEY `fkcro` (`crid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `organisation`
--

DROP TABLE IF EXISTS `organisation`;
CREATE TABLE IF NOT EXISTS `organisation` (
  `organisationid` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `adresse` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `numero` int(8) NOT NULL,
  `leftoverid` int(11) NOT NULL,
  PRIMARY KEY (`organisationid`),
  KEY `fkorganisation` (`leftoverid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `panierid` int(11) NOT NULL AUTO_INCREMENT,
  `produitid` int(11) NOT NULL,
  `offreid` int(11) NOT NULL,
  `clientid` int(11) NOT NULL,
  `quantite` int(11) NOT NULL,
  PRIMARY KEY (`panierid`),
  KEY `fkclientp` (`clientid`),
  KEY `fkoffrep` (`offreid`),
  KEY `fkproduit` (`produitid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `produitid` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `type` varchar(30) NOT NULL,
  `crid` int(11) NOT NULL,
  `prix` float NOT NULL,
  PRIMARY KEY (`produitid`),
  KEY `fkcrp` (`crid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `produitpanier`
--

DROP TABLE IF EXISTS `produitpanier`;
CREATE TABLE IF NOT EXISTS `produitpanier` (
  `ppanierid` int(11) NOT NULL,
  `pproduitid` int(11) NOT NULL,
  `quantite` int(4) NOT NULL,
  PRIMARY KEY (`ppanierid`,`pproduitid`),
  KEY `fkproduit` (`pproduitid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reclamation`
--

DROP TABLE IF EXISTS `reclamation`;
CREATE TABLE IF NOT EXISTS `reclamation` (
  `reclamationid` int(11) NOT NULL AUTO_INCREMENT,
  `sujet` varchar(30) NOT NULL,
  `contenu` text NOT NULL,
  `clientid` int(11) NOT NULL,
  `commandeid` int(11) NOT NULL,
  `nomclient` varchar(11) NOT NULL,
  `reponse` text NOT NULL,
  PRIMARY KEY (`reclamationid`),
  KEY `fkclientrec` (`clientid`),
  KEY `fkcommanderec` (`commandeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `reponse`
--

DROP TABLE IF EXISTS `reponse`;
CREATE TABLE IF NOT EXISTS `reponse` (
  `reponseid` int(11) NOT NULL AUTO_INCREMENT,
  `reclamationid` int(11) NOT NULL,
  `reponsenom` text NOT NULL,
  PRIMARY KEY (`reponseid`),
  KEY `fkreponse` (`reclamationid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
CREATE TABLE IF NOT EXISTS `review` (
  `reviewid` int(11) NOT NULL AUTO_INCREMENT,
  `note` int(5) NOT NULL,
  `commentaire` text NOT NULL,
  `utilisateurid` int(11) NOT NULL,
  `produitid` int(11) NOT NULL,
  `produitnom` varchar(11) NOT NULL,
  `clientnom` varchar(11) NOT NULL,
  PRIMARY KEY (`reviewid`),
  KEY `fkclientreview` (`utilisateurid`),
  KEY `fkproduitreview` (`produitid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `adresse` varchar(60) NOT NULL,
  `email` varchar(50) NOT NULL,
  `passwrd` varchar(64) NOT NULL,
  `numero` int(8) NOT NULL,
  `role` varchar(1) NOT NULL,
  `token` tinyint(1) NOT NULL DEFAULT '0',
  `Valide` int(1) DEFAULT '0',
  `role_name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `nom`, `prenom`, `adresse`, `email`, `passwrd`, `numero`, `role`, `token`, `Valide`, `role_name`) VALUES
(20, 'malek', 'maaa', 'ghazela', 'malek.guemri@esprit.tn', '2c7a5a6bfa4b5baee3b981b7803c3747', 23242526, '1', 0, 1, 'Client'),
(21, 'nouri', 'nouri', 'ghazl', 'nourhene.bakalti@esprit.tn', 'c26be8aaf53b15054896983b43eb6a65', 26281919, '1', 0, 1, 'Client'),
(28, 'aaaa', 'aaa', 'aaaa', 'norhbakalti@gmail.com', '74b87337454200d4d33f80c4663dc5e5', 23808586, '2', 0, 1, NULL);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `fkclient` FOREIGN KEY (`clientid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fklivreur` FOREIGN KEY (`livreurid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkpanier` FOREIGN KEY (`panierid`) REFERENCES `panier` (`panierid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkrc` FOREIGN KEY (`rcid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `leftovers`
--
ALTER TABLE `leftovers`
  ADD CONSTRAINT `fkleftover` FOREIGN KEY (`chefrestoid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `offre`
--
ALTER TABLE `offre`
  ADD CONSTRAINT `fkcro` FOREIGN KEY (`crid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `organisation`
--
ALTER TABLE `organisation`
  ADD CONSTRAINT `fkorganisation` FOREIGN KEY (`leftoverid`) REFERENCES `leftovers` (`leftoverid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `fkclientp` FOREIGN KEY (`clientid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkoffrep` FOREIGN KEY (`offreid`) REFERENCES `offre` (`offreid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkproduit` FOREIGN KEY (`produitid`) REFERENCES `produit` (`produitid`);

--
-- Constraints for table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `fkcrp` FOREIGN KEY (`crid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reclamation`
--
ALTER TABLE `reclamation`
  ADD CONSTRAINT `fkclientrec` FOREIGN KEY (`clientid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkcommanderec` FOREIGN KEY (`commandeid`) REFERENCES `commande` (`commandeid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reponse`
--
ALTER TABLE `reponse`
  ADD CONSTRAINT `fkreponse` FOREIGN KEY (`reclamationid`) REFERENCES `reclamation` (`reclamationid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `fkclientreview` FOREIGN KEY (`utilisateurid`) REFERENCES `utilisateur` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fkproduitreview` FOREIGN KEY (`produitid`) REFERENCES `produit` (`produitid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
