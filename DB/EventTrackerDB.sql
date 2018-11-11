-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema EventTrackerDB
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `EventTrackerDB` ;

-- -----------------------------------------------------
-- Schema EventTrackerDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EventTrackerDB` DEFAULT CHARACTER SET utf8 ;
USE `EventTrackerDB` ;

-- -----------------------------------------------------
-- Table `pet_owner`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_owner` ;

CREATE TABLE IF NOT EXISTS `pet_owner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `date_created` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `date_of_birth` DATE NOT NULL,
  `apartment_number` VARCHAR(45) NOT NULL,
  `active` TINYINT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `pet_owned`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `pet_owned` ;

CREATE TABLE IF NOT EXISTS `pet_owned` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `owner_id` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `animal_type` VARCHAR(45) NOT NULL,
  `breed` VARCHAR(45) NOT NULL,
  `age` INT NULL DEFAULT NULL,
  `is_on_property` TINYINT(1) NOT NULL DEFAULT '1',
  `movein_date` DATE NOT NULL,
  `moveout_date` DATE NULL,
  `rent` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `pet_owned-pet_owner_FK_idx` (`owner_id` ASC),
  CONSTRAINT `pet_owned-pet_owner_FK`
    FOREIGN KEY (`owner_id`)
    REFERENCES `pet_owner` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS user@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'user'@'localhost' IDENTIFIED BY 'user';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'user'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `pet_owner`
-- -----------------------------------------------------
START TRANSACTION;
USE `EventTrackerDB`;
INSERT INTO `pet_owner` (`id`, `first_name`, `last_name`, `date_created`, `date_of_birth`, `apartment_number`, `active`) VALUES (1, 'Jason', 'Melody', DEFAULT, '1960-11-11', '23', 1);
INSERT INTO `pet_owner` (`id`, `first_name`, `last_name`, `date_created`, `date_of_birth`, `apartment_number`, `active`) VALUES (2, 'Missy', 'DeRoberto', DEFAULT, '1990-10-10', '101', 1);
INSERT INTO `pet_owner` (`id`, `first_name`, `last_name`, `date_created`, `date_of_birth`, `apartment_number`, `active`) VALUES (3, 'Jackson', 'Denver', DEFAULT, '1975-03-05', '102', 1);
INSERT INTO `pet_owner` (`id`, `first_name`, `last_name`, `date_created`, `date_of_birth`, `apartment_number`, `active`) VALUES (4, 'Anthony', 'Sanders', DEFAULT, '1988-03-03', '100', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `pet_owned`
-- -----------------------------------------------------
START TRANSACTION;
USE `EventTrackerDB`;
INSERT INTO `pet_owned` (`id`, `owner_id`, `name`, `animal_type`, `breed`, `age`, `is_on_property`, `movein_date`, `moveout_date`, `rent`) VALUES (1, 1, 'Leo', 'DOG', 'Shepherd Mix', 1, 1, '2016-11-01', NULL, 50);
INSERT INTO `pet_owned` (`id`, `owner_id`, `name`, `animal_type`, `breed`, `age`, `is_on_property`, `movein_date`, `moveout_date`, `rent`) VALUES (5, 1, 'Lora', 'CAT', 'Maine Coon', 2, 1, '2018-02-20', NULL, 30);
INSERT INTO `pet_owned` (`id`, `owner_id`, `name`, `animal_type`, `breed`, `age`, `is_on_property`, `movein_date`, `moveout_date`, `rent`) VALUES (4, 1, 'Ben', 'DOG', 'Bernese Mountain Dog', 4, 1, '2017-09-14', NULL, 50);
INSERT INTO `pet_owned` (`id`, `owner_id`, `name`, `animal_type`, `breed`, `age`, `is_on_property`, `movein_date`, `moveout_date`, `rent`) VALUES (3, 2, 'Barbie', 'CAT', 'Persian Mix', 13, 1, '2017-05-01', NULL, 30);
INSERT INTO `pet_owned` (`id`, `owner_id`, `name`, `animal_type`, `breed`, `age`, `is_on_property`, `movein_date`, `moveout_date`, `rent`) VALUES (2, 3, 'Ada', 'DOG', 'Labrador Mix', 8, 1, '2017-01-03', NULL, 50);
INSERT INTO `pet_owned` (`id`, `owner_id`, `name`, `animal_type`, `breed`, `age`, `is_on_property`, `movein_date`, `moveout_date`, `rent`) VALUES (6, 4, 'Bernie', 'DOG', 'Labrador Mix', 6, 1, '2018-08-31', NULL, 50);

COMMIT;

