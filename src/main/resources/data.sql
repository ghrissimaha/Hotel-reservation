INSERT INTO room_type (id,name,description,base_price,max_occupancy) VALUES
(1,'Single','Single bed room',79.00,1),(2,'Double','Double bed room',119.00,2),(3,'Suite','Luxury suite',249.00,4);
INSERT INTO employee (id,first_name,last_name,email,phone,role,hire_date,salary) VALUES
(1,'Jamie','Housekeeper','jamie@example.com','555-0101','HOUSEKEEPER','2023-01-10',32000),
(2,'Alex','Receptionist','alex@example.com','555-0102','RECEPTIONIST','2022-05-01',42000);
INSERT INTO guest (id,first_name,last_name,email,phone,nationality,created_at) VALUES
(1,'Taylor','Guest','taylor@example.com','555-0103','American','2025-01-01 10:00:00'),
(2,'Morgan','Guest','morgan@example.com','555-0104','Canadian','2025-01-02 10:00:00'),
(3,'Jordan','Guest','jordan@example.com','555-0105','British','2025-01-03 10:00:00');
INSERT INTO room (id,room_number,floor,status,room_type_id,employee_id) VALUES
(1,'101',1,'AVAILABLE',1,1),(2,'102',1,'AVAILABLE',1,NULL),(3,'201',2,'AVAILABLE',2,1),
(4,'202',2,'AVAILABLE',2,NULL),(5,'301',3,'AVAILABLE',3,2);
INSERT INTO reservation (id,check_in_date,check_out_date,status,total_price,created_at,guest_id,room_id) VALUES
(1,'2026-06-01','2026-06-03','CONFIRMED',158.00,'2026-01-01 10:00:00',1,1),
(2,'2026-07-10','2026-07-12','PENDING',498.00,'2026-01-02 10:00:00',2,5);
INSERT INTO payment (id,amount,payment_method,payment_date,status,reservation_id) VALUES
(1,158.00,'CARD','2026-01-01 11:00:00','PAID',1),(2,498.00,'ONLINE','2026-01-02 11:00:00','PENDING',2);
