-- MySQL Script generated by MySQL Workbench
-- Thu 03 Aug 2017 04:16:40 PM EAT
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema harlerte_azimadb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema harlerte_azimadb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `harlerte_azimadb` DEFAULT CHARACTER SET utf8 ;
USE `harlerte_azimadb` ;

-- -----------------------------------------------------
-- Table `harlerte_azimadb`.`tblusers`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `harlerte_azimadb`.`tblusers` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `fullname` VARCHAR(200) NULL,
  `email` VARCHAR(200) NULL,
  `idno` VARCHAR(10) NULL,
  `telno` VARCHAR(15) NULL,
  `imei` VARCHAR(20) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
