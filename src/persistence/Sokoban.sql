-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sokoban
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sokoban
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sokoban` DEFAULT CHARACTER SET utf8 ;
USE `sokoban` ;

-- -----------------------------------------------------
-- Table `sokoban`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokoban`.`game` (
  `idgame` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idgame`),
  UNIQUE INDEX `idgame_UNIQUE` (`idgame` ASC),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sokoban`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokoban`.`board` (
  `idboard` INT(11) NOT NULL,
  `boardString` VARCHAR(130) NOT NULL,
  `idgame` INT(11) NOT NULL,
  PRIMARY KEY (`idgame`, `idboard`),
  INDEX `idgame_idx` (`idgame` ASC),
  CONSTRAINT `idgame`
    FOREIGN KEY (`idgame`)
    REFERENCES `sokoban`.`game` (`idgame`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `sokoban`.`player`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sokoban`.`player` (
  `playerID` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `firstname` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `username` VARCHAR(15) NOT NULL,
  `adminrights` TINYINT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`playerID`),
  UNIQUE INDEX `spelerID_UNIQUE` (`playerID` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
