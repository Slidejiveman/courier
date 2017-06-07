SET FOREIGN_KEY_CHECKS=0;
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1001,'bickersnbickers@gmail.com',1,'Bickers & Bickers','5555555555',3);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1002,'slaughternslaugher@gmail.com',1,'Slaughter & Slaughter','5555555556',8);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1003,'paynenfears@gmail.com',1,'Payne & Fears','5555555557',13);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1004,'dhnh@gmail.com',1,'Dewey, Cheatum, & Howe','5555555558',16);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1005,'lbnl@gmail.com',1,'Low, Ball, & Lynch','5555555559',18);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1006,'arguenphibbs@gmail.com',1,'Argue & Phibbs','5555555560',30);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1007,'hunternlyon@gmail.com',0,'Hunter & Lyon','5555555561',34);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1008,'ruffnready@gmail.com',0,'Ruff & Ready','5555555562',43);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1009,'wjnj@gmail.com',1,'Wright, Judge, & Jury','5555555563',49);
INSERT INTO `client`(`client_id`, `client_email`, `client_is_active`,
                     `client_name`, `client_phone_number`, `client_location_id`)
VALUES (1010,'sliddygates@gmail.com',1,'Susan Liddy-Gates','5555555564',39);


INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (3,null,null,1,'D St. & 1st',3,0,1001);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (8,null,null,1,'B St. & 2nd',1,1,1002);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (13,null,null,1,'F St. & 2nd',5,1,1003);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (16,null,null,1,'C St. & 3rd',2,2,1004);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (18,null,null,1,'E St. & 3rd',4,2,1005);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (30,null,null,1,'B St. & 5th',1,4,1006);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (34,null,null,1,'F St. & 5th',5,4,1007);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (43,null,null,1,'A St. & 7th',0,6,1008);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (49,null,null,0,'G St. & 7th',6,6,1009);
INSERT INTO `intersection`(`intersection_id`, `closed_end_date`,
  `closed_start_date`, `intersection_status`, `intersection_name`,
  `x_coordinate`, `y_coordinate`, `client_at_intersection`)
VALUES (39,null,null,1,'D St. & 6th',3,5,1010);
SET FOREIGN_KEY_CHECKS=1;
