-- Default data insertions
-- -----------------------------------------------------
-- Table `AGBEGBE`.`PROFILE_TYPE`
-- -----------------------------------------------------
INSERT INTO `AGBEGBE`.`PROFILE_TYPE` (type, description)
	VALUES
	('SYSTEM', 'System Profile'),
	('ADMINISTRATOR', 'Administrator Profile'),
	('MODERATOR', 'Moderator Profile'),
	('USER', 'User Profile');

-- -----------------------------------------------------
-- Table `AGBEGBE`.`TITLE`
-- -----------------------------------------------------
INSERT INTO `AGBEGBE`.`TITLE` (name)
	VALUES
	('None'),
	('Dr.'),
	('Mr.'),
	('Mrs.'),
	('Ms.');

-- -----------------------------------------------------
-- Table `AGBEGBE`.`SUFFIX`
-- -----------------------------------------------------
INSERT INTO `AGBEGBE`.`SUFFIX` (name)
	VALUES
	('None'),
	('Sr.'),
	('Jr.'),
	('I'),
	('II'),
	('III'),
	('IV');

-- -----------------------------------------------------
-- Table `AGBEGBE`.`USER`
-- -----------------------------------------------------
INSERT INTO `AGBEGBE`.`USER` (firstname, lastname, TITLE_id, SUFFIX_id, PROFILE_TYPE_id)
	VALUES
	('Dee', 'Sharpe', (SELECT id FROM `AGBEGBE`.`TITLE` WHERE name='Mr.'),
					   (SELECT id FROM `AGBEGBE`.`SUFFIX` WHERE name='Sr.'),
                       (SELECT id FROM `AGBEGBE`.`PROFILE_TYPE` WHERE type='ADMINISTRATOR')),
	('Liv', 'Freemont', (SELECT id FROM `AGBEGBE`.`TITLE` WHERE name='Ms.'),
						(SELECT id FROM `AGBEGBE`.`SUFFIX` WHERE name='III'),
						(SELECT id FROM `AGBEGBE`.`PROFILE_TYPE` WHERE type='MODERATOR')),
	('Fred', 'Clifton', (SELECT id FROM `AGBEGBE`.`TITLE` WHERE name='None'),
						(SELECT id FROM `AGBEGBE`.`SUFFIX` WHERE name='None'),
						(SELECT id FROM `AGBEGBE`.`PROFILE_TYPE` WHERE type='USER'));
    
-- -----------------------------------------------------
-- Table `AGBEGBE`.`LOGIN`
-- -----------------------------------------------------
INSERT INTO `AGBEGBE`.`LOGIN` (username, password, registrationdate, enabled, USER_id)
	VALUES
	('admin', 'admin', NOW(), true,
		(SELECT id FROM `AGBEGBE`.`USER` WHERE firstname='Dee' AND lastname='Sharpe')),
	('test1', 'test', NOW(), true,
		(SELECT id FROM `AGBEGBE`.`USER` WHERE firstname='Liv' AND lastname='Freemont')),
	('test2', 'test', NOW(), true,
		(SELECT id FROM `AGBEGBE`.`USER` WHERE firstname='Fred' AND lastname='Clifton'));
    
-- -----------------------------------------------------
-- Table `AGBEGBE`.`SETTINGS`
-- -----------------------------------------------------
INSERT INTO `AGBEGBE`.`SETTING` (name, value)
	VALUES
	('site-name', 'Kissed By The Sun');