-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema AGBEGBE
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `AGBEGBE` ;

-- -----------------------------------------------------
-- Schema AGBEGBE
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `AGBEGBE` ;
USE `AGBEGBE` ;

-- -----------------------------------------------------
-- Table `AGBEGBE`.`PROFILE_TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGBEGBE`.`PROFILE_TYPE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `type_UNIQUE` (`type` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGBEGBE`.`TITLE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGBEGBE`.`TITLE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGBEGBE`.`SUFFIX`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGBEGBE`.`SUFFIX` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGBEGBE`.`USER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGBEGBE`.`USER` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `TITLE_id` INT NOT NULL,
  `SUFFIX_id` INT NOT NULL,
  `PROFILE_TYPE_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_USER_PROFILE_TYPE_idx` (`PROFILE_TYPE_id` ASC) VISIBLE,
  INDEX `fk_USER_TITLE1_idx` (`TITLE_id` ASC) VISIBLE,
  INDEX `fk_USER_SUFFIX1_idx` (`SUFFIX_id` ASC) VISIBLE,
  CONSTRAINT `fk_USER_PROFILE_TYPE`
    FOREIGN KEY (`PROFILE_TYPE_id`)
    REFERENCES `AGBEGBE`.`PROFILE_TYPE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_TITLE1`
    FOREIGN KEY (`TITLE_id`)
    REFERENCES `AGBEGBE`.`TITLE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_USER_SUFFIX1`
    FOREIGN KEY (`SUFFIX_id`)
    REFERENCES `AGBEGBE`.`SUFFIX` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGBEGBE`.`SETTING`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGBEGBE`.`SETTING` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `value` VARCHAR(128) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `AGBEGBE`.`LOGIN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `AGBEGBE`.`LOGIN` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `registrationdate` DATETIME(6) NOT NULL,
  `enabled` TINYINT NOT NULL,
  `tokenvalue` VARCHAR(3000) NULL,
  `tokenrefresh` VARCHAR(3000) NULL,
  `tokendatetime` DATETIME(6) NULL,
  `USER_id` INT NOT NULL,
  INDEX `fk_LOGIN_USER1_idx` (`USER_id` ASC) VISIBLE,
  UNIQUE INDEX `USER_id_UNIQUE` (`USER_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  CONSTRAINT `fk_LOGIN_USER1`
    FOREIGN KEY (`USER_id`)
    REFERENCES `AGBEGBE`.`USER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
