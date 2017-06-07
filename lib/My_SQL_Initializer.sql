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

INSERT INTO `employee`(`employee_id`, `employee_role`, `employee_email`,
  `employee_is_active`, `employee_name`, `employee_password`, `employee_shift`,
  `employee_username`)
VALUES (0,'Owner','acmecourierservicesowner@gmail.com',1,'David North','owner',
  1,'owner');
INSERT INTO `employee`(`employee_id`, `employee_role`, `employee_email`,
  `employee_is_active`, `employee_name`, `employee_password`, `employee_shift`,
  `employee_username`)
VALUES (1,'Courier','jacksprat@gmail.com',1,'Jack Sprat','courier1',
  1,'courier1');
INSERT INTO `employee`(`employee_id`, `employee_role`, `employee_email`,
  `employee_is_active`, `employee_name`, `employee_password`, `employee_shift`,
  `employee_username`)
VALUES (2,'Courier','peterpumpkin@gmail.com',1,'Peter Pumpkin','courier2',
  3,'courier2');
INSERT INTO `employee`(`employee_id`, `employee_role`, `employee_email`,
  `employee_is_active`, `employee_name`, `employee_password`, `employee_shift`,
  `employee_username`)
VALUES (3,'Courier','jackcandle@gmail.com',1,'Jack Candle','courier3',
  2,'courier3');
INSERT INTO `employee`(`employee_id`, `employee_role`, `employee_email`,
  `employee_is_active`, `employee_name`, `employee_password`, `employee_shift`,
  `employee_username`)
VALUES (4,'Courier','marymuffet@gmail.com',0,'Mary Muffet','courier4',
  1,'courier4');
INSERT INTO `employee`(`employee_id`, `employee_role`, `employee_email`,
  `employee_is_active`, `employee_name`, `employee_password`, `employee_shift`,
  `employee_username`)
VALUES (5,'Courier','kencole@gmail.com',1,'Ken Cole','courier5',
  2,'courier5');
INSERT INTO `employee`(`employee_id`, `employee_role`, `employee_email`,
  `employee_is_active`, `employee_name`, `employee_password`, `employee_shift`,
  `employee_username`)
VALUES (6,'OrderTaker','karenpowers@gmail.com',1,'Karen Powers','ordertaker1',
  1,'ordertaker1');

INSERT INTO `delivery_ticket`(`delivery_ticket_id`, `delivery_time`,
  `departure_time`, `pick_up_time`, `return_time`, `estimated_blocks`,
  `estimated_delivery_time`, `estimated_price`, `estimated_departure_time`,
  `is_bill_pick_up`, `bonus_earned`, `order_date`, `requested_pickup_time`,
  `special_instructions`, `ticket_status`, `dt_courier_id`, `dt_delivery_client_id`,
  `dt_order_taker_id`, `dt_pick_up_client_id`)
VALUES (0,'2017-06-07 10:45:00','2017-06-07 10:15:00','2017-06-07 10:30:00',
  '2017-06-07 11:00:00',6,'2017-06-07 10:50:00',22.00,'2017-06-07 10:15:00',
  1,1,'2017-06-07','2017-06-07 10:30:00','Go to the 2nd floor.',
  'Closed',3,1004,6,1005)
SET FOREIGN_KEY_CHECKS=1;
