INSERT INTO country (country_id, name)
VALUES
  ('cdf2a385-054f-4a43-8840-6f26019a6b1f', 'United States'),
  ('00bd4223-2a02-46e3-8427-f0a79b4c2d54', 'Canada'),
  ('b0af2b39-1477-4922-a78a-f17d17cf2d41', 'United Kingdom'),
  ('0f1dfda0-c781-49c6-91f1-79e2acfb3c6c', 'Australia'),
  ('e28f7bc7-1298-4cb2-a8b4-df8683e5c98e', 'Germany');

INSERT INTO city (country_id, name, town_id)
VALUES
  ('cdf2a385-054f-4a43-8840-6f26019a6b1f', 'New York', 'e5c0fc6e-79cf-4e6b-a8a6-500b28e2ed59'),
  ('cdf2a385-054f-4a43-8840-6f26019a6b1f', 'Los Angeles', 'd2cb5f34-8fe7-4e45-ba5c-b9d5dcd3d378'),
  ('b0af2b39-1477-4922-a78a-f17d17cf2d41', 'London', 'b510d0e8-2c1b-4260-b85e-27d7e09e1662'),
  ('0f1dfda0-c781-49c6-91f1-79e2acfb3c6c', 'Sydney', '8b69b6e4-6d39-4c2d-9e6b-4d2a7f11b18e'),
  ('e28f7bc7-1298-4cb2-a8b4-df8683e5c98e', 'Berlin', '0f1d1f9e-98f2-46ed-a26e-014960fb81ac');

INSERT INTO street (city_id, name, street_id)
VALUES
  ('e5c0fc6e-79cf-4e6b-a8a6-500b28e2ed59', 'Main Street', 'b0e26fe7-25d5-42a6-82e9-743a5a4aa45b'),
  ('d2cb5f34-8fe7-4e45-ba5c-b9d5dcd3d378', 'Broadway', 'fb3049a1-721b-42ee-9952-22f498c5c9b0'),
  ('b510d0e8-2c1b-4260-b85e-27d7e09e1662', 'High Street', '8d99a6c7-60ee-4d2e-b2ff-88eb9d3e4340'),
  ('8b69b6e4-6d39-4c2d-9e6b-4d2a7f11b18e', 'George Street', '03c76f2d-59fc-49d3-93b1-7276e0d1de6c'),
  ('0f1d1f9e-98f2-46ed-a26e-014960fb81ac', 'Alexanderplatz', '6e88f743-47ed-403e-b586-19f0ecdf5b86');

INSERT INTO home_or_workshop (home_or_workshop_id, street_id)
VALUES
-- homes
  ('d4edcd84-c44a-4749-b76e-0b99b3965fc2', 'b0e26fe7-25d5-42a6-82e9-743a5a4aa45b'),
  ('716f50b9-6a3e-4f0c-b564-40ff81d3fe1a', 'fb3049a1-721b-42ee-9952-22f498c5c9b0'),
  ('3f4b12ef-95a6-40dc-8489-82e4bcb8c197', '8d99a6c7-60ee-4d2e-b2ff-88eb9d3e4340'),
-- workshops
  ('7f5e93d8-ec35-4aa0-bbc7-b72987b36870', '03c76f2d-59fc-49d3-93b1-7276e0d1de6c'),
  ('b1ae55b3-6074-4ad2-9b69-5b1ae55b33c8', '6e88f743-47ed-403e-b586-19f0ecdf5b86');

INSERT INTO category (category_id, description, name)
VALUES
  ('e5f57f20-89a7-4a95-9a10-9db4a4c27925', 'Electrical services', 'Electrical'),
  ('36dc6141-8ad1-47f5-b1f0-8c70bbcf65b1', 'Plumbing services', 'Plumbing'),
  ('6f069ab3-f20d-41b7-9cfd-cb108f8d21aa', 'Carpentry services', 'Carpentry'),
  ('94889e82-82c0-459d-93c4-57d4f221f251', 'Cleaning services', 'Cleaning'),
  ('48b6a52a-7b67-4428-9f63-0e4d4ec1693c', 'Painting services', 'Painting');

INSERT INTO handyman (is_suspended, rating, email, first_name, handyman_id, home_or_workshop_id, last_name)
VALUES
  (false, 4.5, 'adam.wilson@example.com', 'Adam', '3e5d0e76-b5b3-43f5-b88d-5d17f92be31d', '7f5e93d8-ec35-4aa0-bbc7-b72987b36870', 'Wilson'),
  (true, 3.2, 'emily.jones@example.com', 'Emily', '9e8f51f7-066d-4d4e-ba08-12e53580e72b', '7f5e93d8-ec35-4aa0-bbc7-b72987b36870', 'Jones'),
  (false, 4.8, 'ryan.davis@example.com', 'Ryan', '7450e8ed-1d36-4e88-9e69-228baf4f1201', '7f5e93d8-ec35-4aa0-bbc7-b72987b36870', 'Davis'),
  (true, 2.9, 'olivia.miller@example.com', 'Olivia', '5bc095f3-eb14-4a49-92e8-2299e024db37', 'b1ae55b3-6074-4ad2-9b69-5b1ae55b33c8', 'Miller'),
  (false, 4.3, 'nathan.wilson@example.com', 'Nathan', 'b285a953-6074-4ad2-9b69-5b1ae55b33c8', 'b1ae55b3-6074-4ad2-9b69-5b1ae55b33c8', 'Wilson');


INSERT INTO service (duration, price, category_id, description, name, service_id, handyman_id)
VALUES
  (60, 100.00, 'e5f57f20-89a7-4a95-9a10-9db4a4c27925', 'Electrical repair', 'Electrical Repair', 'f80c8a06-5bc9-4149-82cd-d4e45002c3a8', '3e5d0e76-b5b3-43f5-b88d-5d17f92be31d'),
  (90, 120.00, '36dc6141-8ad1-47f5-b1f0-8c70bbcf65b1', 'Plumbing installation', 'Plumbing Installation', 'c8b7c692-5a2f-4595-ba89-ba0d60e96b10', '9e8f51f7-066d-4d4e-ba08-12e53580e72b'),
  (120, 150.00, '6f069ab3-f20d-41b7-9cfd-cb108f8d21aa', 'Carpentry work', 'Carpentry Work', '35e186e3-5de5-4b99-a9b9-2d8b75bfe57c', '7450e8ed-1d36-4e88-9e69-228baf4f1201'),
  (60, 80.00, '94889e82-82c0-459d-93c4-57d4f221f251', 'House cleaning', 'House Cleaning', '9a78f9ff-184d-4a76-819a-77cc80c76dd5', '5bc095f3-eb14-4a49-92e8-2299e024db37'),
  (120, 200.00, '48b6a52a-7b67-4428-9f63-0e4d4ec1693c', 'Interior painting', 'Interior Painting', '7db1f4d8-1504-42a2-9473-860c9e7ec1a0', 'b285a953-6074-4ad2-9b69-5b1ae55b33c8');

INSERT INTO customer (is_suspended, strikes, customer_id, email, first_name, home_or_workshop_id, last_name)
VALUES
  (false, 0, '8ac6f8c0-df0d-4cb9-a004-6b0bf89e8e43', 'john.doe@example.com', 'John', 'd4edcd84-c44a-4749-b76e-0b99b3965fc2', 'Doe'),
  (true, 2, 'fa4d4b89-d1a3-4b76-91fb-1a77d4c49f12', 'jane.smith@example.com', 'Jane', 'd4edcd84-c44a-4749-b76e-0b99b3965fc2', 'Smith'),
  (false, 1, '9ef6b3ac-2ad9-48e5-9b9b-19c9a1a2057c', 'michael.johnson@example.com', 'Michael', '716f50b9-6a3e-4f0c-b564-40ff81d3fe1a', 'Johnson'),
  (true, 3, '4a92dc7d-ebc0-4e7f-8b05-98aa241f4488', 'sarah.williams@example.com', 'Sarah', '3f4b12ef-95a6-40dc-8489-82e4bcb8c197', 'Williams'),
  (false, 0, 'c5f04845-6b50-4391-8122-9f3305aa52cc', 'chris.brown@example.com', 'Chris', '3f4b12ef-95a6-40dc-8489-82e4bcb8c197', 'Brown');

INSERT INTO notification (date, customer_id, handyman_id, message, notification_id, sender)
VALUES
  ('2024-04-09 08:00:00+00', '8ac6f8c0-df0d-4cb9-a004-6b0bf89e8e43', '3e5d0e76-b5b3-43f5-b88d-5d17f92be31d', 'Your appointment is confirmed.', '1f4f17d5-97a3-4ed8-8b1e-7c7aa68b2ff1', 'System'),
  ('2024-04-10 10:30:00+00', 'fa4d4b89-d1a3-4b76-91fb-1a77d4c49f12', '9e8f51f7-066d-4d4e-ba08-12e53580e72b', 'Your appointment has been rescheduled.', '2e31643e-6858-4520-b324-839727c2277d', 'System'),
  ('2024-04-11 09:15:00+00', '9ef6b3ac-2ad9-48e5-9b9b-19c9a1a2057c', '7450e8ed-1d36-4e88-9e69-228baf4f1201', 'Your appointment has been canceled.', '3bfc32e4-bbe8-4ed2-8334-8d1e14c7bbbf', 'System'),
  ('2024-04-12 14:00:00+00', '4a92dc7d-ebc0-4e7f-8b05-98aa241f4488', '5bc095f3-eb14-4a49-92e8-2299e024db37', 'Your appointment is confirmed.', '4d56ec5a-25f1-4cd1-82d4-d170fd0ed87b', 'System'),
  ('2024-04-13 11:45:00+00', 'c5f04845-6b50-4391-8122-9f3305aa52cc', 'b285a953-6074-4ad2-9b69-5b1ae55b33c8', 'Your appointment has been rescheduled.', '5e7c901f-3681-4392-ba09-3d64a616a52b', 'System');

INSERT INTO schedule (handyman_id, schedule_id)
VALUES
  ('3e5d0e76-b5b3-43f5-b88d-5d17f92be31d', '9f5b1c05-83c9-4a52-90a3-6250ed3f409f'),
  ('9e8f51f7-066d-4d4e-ba08-12e53580e72b', 'c2eb16cf-4469-4da7-9e26-d7ec4fd8cf7f'),
  ('7450e8ed-1d36-4e88-9e69-228baf4f1201', '59ec3f8f-43d4-4a6b-b3bb-05f2c7e9b15'),
  ('5bc095f3-eb14-4a49-92e8-2299e024db37', '9a78f9ff-184d-4a76-819a-77cc80c76dd5'),
  ('b285a953-6074-4ad2-9b69-5b1ae55b33c8', '7db1f4d8-1504-42a2-9473-860c9e7ec1a0');

INSERT INTO reservation (date, id, status, customer_id, schedule_id, service_id)
VALUES
  ('2024-04-09 08:00:00+00', 'c678d41c-02b6-481e-8df6-2c95a8932df7', 0, '8ac6f8c0-df0d-4cb9-a004-6b0bf89e8e43', '9f5b1c05-83c9-4a52-90a3-6250ed3f409f', 'f80c8a06-5bc9-4149-82cd-d4e45002c3a8'),
  ('2024-04-10 10:30:00+00', '2d6e6db2-7f44-4094-94f3-140cfe6b2993', 0, 'fa4d4b89-d1a3-4b76-91fb-1a77d4c49f12', 'c2eb16cf-4469-4da7-9e26-d7ec4fd8cf7f', 'c8b7c692-5a2f-4595-ba89-ba0d60e96b10'),
  ('2024-04-11 09:15:00+00', 'e3b1c4b9-5fa4-42b7-a01b-d0e45fdd5f6c', 0, '9ef6b3ac-2ad9-48e5-9b9b-19c9a1a2057c', '59ec3f8f-43d4-4a6b-b3bb-05f2c7e9b15', '35e186e3-5de5-4b99-a9b9-2d8b75bfe57c'),
  ('2024-04-12 14:00:00+00', 'b736c5d7-6b39-448f-b0aa-78915b2b98a2', 0, '4a92dc7d-ebc0-4e7f-8b05-98aa241f4488', '9a78f9ff-184d-4a76-819a-77cc80c76dd5', 'f80c8a06-5bc9-4149-82cd-d4e45002c3a8'),
  ('2024-04-13 11:45:00+00', '7db1f4d8-1504-42a2-9473-860c9e7ec1a0', 0, 'c5f04845-6b50-4391-8122-9f3305aa52cc', '9f5b1c05-83c9-4a52-90a3-6250ed3f409f', '35e186e3-5de5-4b99-a9b9-2d8b75bfe57c');

