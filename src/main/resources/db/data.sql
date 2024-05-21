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

INSERT INTO street (city_id, name, number, street_id)
VALUES
  ('e5c0fc6e-79cf-4e6b-a8a6-500b28e2ed59', 'Main Street', 13, 'b0e26fe7-25d5-42a6-82e9-743a5a4aa45b'),
  ('d2cb5f34-8fe7-4e45-ba5c-b9d5dcd3d378', 'Broadway', 33, 'fb3049a1-721b-42ee-9952-22f498c5c9b0'),
  ('b510d0e8-2c1b-4260-b85e-27d7e09e1662', 'High Street', 2, '8d99a6c7-60ee-4d2e-b2ff-88eb9d3e4340'),
  ('8b69b6e4-6d39-4c2d-9e6b-4d2a7f11b18e', 'George Street', 64, '03c76f2d-59fc-49d3-93b1-7276e0d1de6c'),
  ('0f1d1f9e-98f2-46ed-a26e-014960fb81ac', 'Alexanderplatz', 344, '6e88f743-47ed-403e-b586-19f0ecdf5b86'),
  ('0f1d1f9e-98f2-46ed-a26e-014960fb81ac', 'Berliner Straße', 44, '03d5df57-5d1d-4177-a64c-c372702d6a32'),
  ('0f1d1f9e-98f2-46ed-a26e-014960fb81ac', 'Hauptstraße', 323, 'd6e7a758-6e29-4b62-8fa1-eef9cc344b85'),
  ('0f1d1f9e-98f2-46ed-a26e-014960fb81ac', 'Königsallee', 3, '37dcc8d8-5bfd-487a-a55b-6fb519434c13'),
  ('b510d0e8-2c1b-4260-b85e-27d7e09e1662', 'Collins Street', 298, 'ff7080ee-ba43-44ec-9d76-16600bf1226a'),
  ('b510d0e8-2c1b-4260-b85e-27d7e09e1662', 'Mapple Street', 222, 'eec53308-940f-4b43-9e05-52d3b792b370');

INSERT INTO home_or_workshop (home_or_workshop_id, name, street_id)
VALUES
-- homes
  ('d4edcd84-c44a-4749-b76e-0b99b3965fc2','John and Jane', 'b0e26fe7-25d5-42a6-82e9-743a5a4aa45b'),
  ('716f50b9-6a3e-4f0c-b564-40ff81d3fe1a','Johnsons', 'fb3049a1-721b-42ee-9952-22f498c5c9b0'),
  ('3f4b12ef-95a6-40dc-8489-82e4bcb8c197','Brown-Williams', '8d99a6c7-60ee-4d2e-b2ff-88eb9d3e4340'),
-- workshops
  ('7f5e93d8-ec35-4aa0-bbc7-b72987b36870','All In One Workshop', '03c76f2d-59fc-49d3-93b1-7276e0d1de6c'),
  ('b1ae55b3-6074-4ad2-9b69-5b1ae55b33c8','Fast Workshop', '6e88f743-47ed-403e-b586-19f0ecdf5b86'),
  ('a3c12f34-56d7-4a2b-9e4b-1df3e9b1a8c9','Craftsman Workshop', '03d5df57-5d1d-4177-a64c-c372702d6a32'),
  ('c1a4d6b8-8b4a-4d0b-939f-8e4b4b0c8c9e','Tech Repair Workshop', 'd6e7a758-6e29-4b62-8fa1-eef9cc344b85'),
  ('b2d5e7f8-9a4c-4b3d-9f0b-1e2b3c4d5f6a','Handyman Services', '37dcc8d8-5bfd-487a-a55b-6fb519434c13'),
  ('d4e7f9b0-0a5b-4d3b-9f1a-2b3c4d5e6f7a','Auto Repair Hub', 'ff7080ee-ba43-44ec-9d76-16600bf1226a'),
  ('e5f8b1a2-1b6c-4d4b-9f2b-3c4d5e6f7a8b','Woodworking Studio', 'eec53308-940f-4b43-9e05-52d3b792b370'),
  ('f6a8b2c3-2c7d-4e5b-9f3c-4d5e6f7a8b9c','Metalworks Shop', '03c76f2d-59fc-49d3-93b1-7276e0d1de6c'),
  ('07b9c3d4-3d8e-4f6b-9f4d-5e6f7a8b9c0d','Electronics Repair', '6e88f743-47ed-403e-b586-19f0ecdf5b86'),
  ('18c0d4e5-4e9f-5f7b-9f5e-6f7a8b9c0d1e','Custom Carpentry', '03d5df57-5d1d-4177-a64c-c372702d6a32'),
  ('29d1e5f6-5f0a-6f8b-9f6f-7a8b9c0d1e2f','Bike Repair Station', 'd6e7a758-6e29-4b62-8fa1-eef9cc344b85');


INSERT INTO category (category_id, description, name)
VALUES
  ('e5f57f20-89a7-4a95-9a10-9db4a4c27925', 'Electrical services', 'Electrical'),
  ('36dc6141-8ad1-47f5-b1f0-8c70bbcf65b1', 'Plumbing services', 'Plumbing'),
  ('6f069ab3-f20d-41b7-9cfd-cb108f8d21aa', 'Carpentry services', 'Carpentry'),
  ('94889e82-82c0-459d-93c4-57d4f221f251', 'Cleaning services', 'Cleaning'),
  ('48b6a52a-7b67-4428-9f63-0e4d4ec1693c', 'Painting services', 'Painting'),
  ('d1f01f57-4af0-48d8-92a6-5f74c1b6dbe1', 'HVAC services', 'HVAC'),
  ('b9e07d25-5c1a-42f5-94d3-1c0a7b4f71da', 'Landscaping services', 'Landscaping'),
  ('c4bfa26a-d8d7-4b9d-81b8-1b7e7a71a1ec', 'Roofing services', 'Roofing'),
  ('a0f7d68b-4e95-4815-9358-6b4d4e4e67e4', 'Pest control services', 'Pest Control'),
  ('f2b8f1e4-3d9f-4a7d-8a7d-9d7e7a8d7f8d', 'Moving services', 'Moving');

INSERT INTO handyman (is_suspended, rating, type, email, first_name, handyman_id, home_or_workshop_id, last_name, password)
VALUES
  (false, 4.5,1, 'adam.wilson@example.com', 'Adam', '3e5d0e76-b5b3-43f5-b88d-5d17f92be31d', '7f5e93d8-ec35-4aa0-bbc7-b72987b36870', 'Wilson', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (true, 3.2,1, 'emily.jones@example.com', 'Emily', '9e8f51f7-066d-4d4e-ba08-12e53580e72b', '7f5e93d8-ec35-4aa0-bbc7-b72987b36870', 'Jones', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 4.8,1, 'ryan.davis@example.com', 'Ryan', '7450e8ed-1d36-4e88-9e69-228baf4f1201', '7f5e93d8-ec35-4aa0-bbc7-b72987b36870', 'Davis', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (true, 2.9,1, 'olivia.miller@example.com', 'Olivia', '5bc095f3-eb14-4a49-92e8-2299e024db37', 'b1ae55b3-6074-4ad2-9b69-5b1ae55b33c8', 'Miller', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 4.3,1, 'nathan.wilson@example.com', 'Nathan', 'b285a953-6074-4ad2-9b69-5b1ae55b33c8', 'b1ae55b3-6074-4ad2-9b69-5b1ae55b33c8', 'Wilson', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 4.7,1, 'sophia.smith@example.com', 'Sophia', '3f4b0e77-b6b4-4f5b-b89d-5d28f93ce41e', 'a3c12f34-56d7-4a2b-9e4b-1df3e9b1a8c9', 'Smith', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 3.9,1, 'michael.brown@example.com', 'Michael', '4g6c1f88-c7c5-4g6c-b9ad-6e39f04de52f', 'c1a4d6b8-8b4a-4d0b-939f-8e4b4b0c8c9e', 'Brown', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 4.6,1, 'isabella.johnson@example.com', 'Isabella', '5h7d2g99-d8d6-4h7d-b0bd-7f40g15ef63g', 'b2d5e7f8-9a4c-4b3d-9f0b-1e2b3c4d5f6a', 'Johnson', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 4.1,1, 'david.lee@example.com', 'David', '6i8e3h00-e9e7-4i8e-c1ce-8g51h26fg74h', 'd4e7f9b0-0a5b-4d3b-9f1a-2b3c4d5e6f7a', 'Lee', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 4.2,1, 'mia.walker@example.com', 'Mia', '7j9f4i11-fa08-4j9f-d2df-9h62i37gh85i', 'e5f8b1a2-1b6c-4d4b-9f2b-3c4d5e6f7a8b', 'Walker', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 4.0,1, 'emma.robinson@example.com', 'Emma', '8k0g5j22-gb19-4k0g-e3ef-0i73j48hi96j', 'f6a8b2c3-2c7d-4e5b-9f3c-4d5e6f7a8b9c', 'Robinson', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 4.4,1, 'daniel.martin@example.com', 'Daniel', '9l1h6k33-hc20-4l1h-f4gf-1j84k59ij07k', '07b9c3d4-3d8e-4f6b-9f4d-5e6f7a8b9c0d', 'Martin', '$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2');

INSERT INTO service (duration, price, category_id, name, description, service_id, handyman_id)
VALUES
  (60, 100, 'e5f57f20-89a7-4a95-9a10-9db4a4c27925', 'Electrical repair', 'Fixing all kinds of electrical damage', 'f80c8a06-5bc9-4149-82cd-d4e45002c3a8', '3e5d0e76-b5b3-43f5-b88d-5d17f92be31d'),
  (120, 150, '36dc6141-8ad1-47f5-b1f0-8c70bbcf65b1', 'Plumbing repair', 'Expert plumbing services for any issue', 'a234c8a7-5bc9-4149-82cd-d4e45002c3a9', '9e8f51f7-066d-4d4e-ba08-12e53580e72b'),
  (45, 80, '6f069ab3-f20d-41b7-9cfd-cb108f8d21aa', 'Custom carpentry', 'Handcrafted furniture and fittings', 'b567d9b8-6cd0-5151-93ed-e5f56013f4b0', '7450e8ed-1d36-4e88-9e69-228baf4f1201'),
  (90, 120, '94889e82-82c0-459d-93c4-57d4f221f251', 'Home cleaning', 'Thorough cleaning for homes and offices', 'c678e0e9-7de1-6262-94fe-f6g67124g5c1', '5bc095f3-eb14-4a49-92e8-2299e024db37'),
  (60, 95, '48b6a52a-7b67-4428-9f63-0e4d4ec1693c', 'Interior painting', 'Professional interior painting services', 'd789f1f0-8ef2-7373-a5hf-g7h78235h6d2', 'b285a953-6074-4ad2-9b69-5b1ae55b33c8'),
  (120, 180, 'd1f01f57-4af0-48d8-92a6-5f74c1b6dbe1', 'HVAC installation', 'Installation of HVAC systems', 'e890g2g1-9fg3-8484-b6ig-h8i89346i7e3', '3e5d0e76-b5b3-43f5-b88d-5d17f92be31d'),
  (60, 110, 'b9e07d25-5c1a-42f5-94d3-1c0a7b4f71da', 'Lawn care', 'Complete lawn care services', 'f901h3h2-afg4-9595-c7jh-i9j90457j8f4', '9e8f51f7-066d-4d4e-ba08-12e53580e72b'),
  (90, 140, 'c4bfa26a-d8d7-4b9d-81b8-1b7e7a71a1ec', 'Roof repair', 'Expert roof repair services', '0a12i4i3-bg56-0606-d8ki-j0k01568k9g5', '7450e8ed-1d36-4e88-9e69-228baf4f1201'),
  (60, 90, 'a0f7d68b-4e95-4815-9358-6b4d4e4e67e4', 'Pest control', 'Effective pest control solutions', '1b23j5j4-ch67-1717-e9lj-k1l12679l0h6', '5bc095f3-eb14-4a49-92e8-2299e024db37'),
  (120, 160, 'f2b8f1e4-3d9f-4a7d-8a7d-9d7e7a8d7f8d', 'Moving services', 'Reliable moving services', '2c34k6k5-di78-2828-f0mk-l2m23780m1i7', 'b285a953-6074-4ad2-9b69-5b1ae55b33c8'),
  (60, 130, 'e5f57f20-89a7-4a95-9a10-9db4a4c27925', 'Wiring installation', 'Complete electrical wiring services', '3d45l7l6-ej89-3939-g1nl-m3n34891n2j8', '3f4b0e77-b6b4-4f5b-b89d-5d28f93ce41e'),
  (90, 145, '36dc6141-8ad1-47f5-b1f0-8c70bbcf65b1', 'Drain cleaning', 'Professional drain cleaning services', '4e56m8m7-fk90-4040-h2om-n4o45902o3k9', '4g6c1f88-c7c5-4g6c-b9ad-6e39f04de52f'),
  (45, 75, '6f069ab3-f20d-41b7-9cfd-cb108f8d21aa', 'Furniture assembly', 'Quick and reliable furniture assembly', '5f67n9n8-gl01-5151-i3pn-o5p56013p4l0', '5h7d2g99-d8d6-4h7d-b0bd-7f40g15ef63g'),
  (120, 170, '94889e82-82c0-459d-93c4-57d4f221f251', 'Office cleaning', 'Thorough cleaning for office spaces', '6g78o0o9-hm12-6262-j4qo-p6q67124q5m1', '6i8e3h00-e9e7-4i8e-c1ce-8g51h26fg74h'),
  (90, 115, '48b6a52a-7b67-4428-9f63-0e4d4ec1693c', 'Exterior painting', 'Professional exterior painting services', '7h89p1p0-in23-7373-k5rp-q7r78235r6n2', '7j9f4i11-fa08-4j9f-d2df-9h62i37gh85i'),
  (60, 125, 'd1f01f57-4af0-48d8-92a6-5f74c1b6dbe1', 'AC repair', 'Reliable AC repair services', '8i90q2q1-jo34-8484-l6sq-r8s89346s7o3', '8k0g5j22-gb19-4k0g-e3ef-0i73j48hi96j'),
  (90, 135, 'b9e07d25-5c1a-42f5-94d3-1c0a7b4f71da', 'Garden maintenance', 'Comprehensive garden maintenance', '9j01r3r2-kp45-9595-m7tr-s9t90457t8p4', '3f4b0e77-b6b4-4f5b-b89d-5d28f93ce41e'),
  (120, 155, 'c4bfa26a-d8d7-4b9d-81b8-1b7e7a71a1ec', 'New roof installation', 'Expert new roof installation services', '0k12s4s3-lq56-0606-n8us-t0u01568u9q5', '4g6c1f88-c7c5-4g6c-b9ad-6e39f04de52f'),
  (60, 100, 'a0f7d68b-4e95-4815-9358-6b4d4e4e67e4', 'Rodent control', 'Effective rodent control solutions', '1l23t5t4-mr67-1717-o9vt-u1v12679v0r6', '5h7d2g99-d8d6-4h7d-b0bd-7f40g15ef63g'),
  (120, 165, 'f2b8f1e4-3d9f-4a7d-8a7d-9d7e7a8d7f8d', 'Long-distance moving', 'Reliable long-distance moving services', '2m34u6u5-ns78-2828-p0wu-v2w23780w1s7', '6i8e3h00-e9e7-4i8e-c1ce-8g51h26fg74h');

INSERT INTO customer (is_suspended, strikes, type,  customer_id, email, first_name, home_or_workshop_id, last_name, password)
VALUES
  (false, 0,0, '8ac6f8c0-df0d-4cb9-a004-6b0bf89e8e43', 'john.doe@example.com', 'John', 'd4edcd84-c44a-4749-b76e-0b99b3965fc2', 'Doe','$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (true, 2,0, 'fa4d4b89-d1a3-4b76-91fb-1a77d4c49f12', 'jane.smith@example.com', 'Jane', 'd4edcd84-c44a-4749-b76e-0b99b3965fc2', 'Smith','$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 1,0, '9ef6b3ac-2ad9-48e5-9b9b-19c9a1a2057c', 'michael.johnson@example.com', 'Michael', '716f50b9-6a3e-4f0c-b564-40ff81d3fe1a', 'Johnson','$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (true, 3,0, '4a92dc7d-ebc0-4e7f-8b05-98aa241f4488', 'sarah.williams@example.com', 'Sarah', '3f4b12ef-95a6-40dc-8489-82e4bcb8c197', 'Williams','$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2'),
  (false, 0,0, 'c5f04845-6b50-4391-8122-9f3305aa52cc', 'chris.brown@example.com', 'Chris', '3f4b12ef-95a6-40dc-8489-82e4bcb8c197', 'Brown','$2a$12$GTp8md1V8z5TgD1zSxirf.JN9O4SmOLNoLivGUjaVcCKU1BxuVIh2');

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
  ('b285a953-6074-4ad2-9b69-5b1ae55b33c8', '7db1f4d8-1504-42a2-9473-860c9e7ec1a0'),
  ('3f4b0e77-b6b4-4f5b-b89d-5d28f93ce41e', '1a2b3c4d-5e6f-7g8h-9i0j-1k2l3m4n5o6p'),
  ('4g6c1f88-c7c5-4g6c-b9ad-6e39f04de52f', '2b3c4d5e-6f7g-8h9i-0j1k-2l3m4n5o6p7q'),
  ('5h7d2g99-d8d6-4h7d-b0bd-7f40g15ef63g', '3c4d5e6f-7g8h-9i0j-1k2l-3m4n5o6p7q8r'),
  ('6i8e3h00-e9e7-4i8e-c1ce-8g51h26fg74h', '4d5e6f7g-8h9i-0j1k-2l3m-4n5o6p7q8r9s'),
  ('7j9f4i11-fa08-4j9f-d2df-9h62i37gh85i', '5e6f7g8h-9i0j-1k2l-3m4n-5o6p7q8r9s0t'),
  ('8k0g5j22-gb19-4k0g-e3ef-0i73j48hi96j', '6f7g8h9i-0j1k-2l3m-4n5o-6p7q8r9s0t1u'),
  ('9l1h6k33-hc20-4l1h-f4gf-1j84k59ij07k', '7g8h9i0j-1k2l-3m4n-5o6p-7q8r9s0t1u2v');

INSERT INTO reservation (date, id, status, customer_id, schedule_id, service_id)
VALUES
  ('2024-04-09 08:00:00+00', 'c678d41c-02b6-481e-8df6-2c95a8932df7', 0, '8ac6f8c0-df0d-4cb9-a004-6b0bf89e8e43', '9f5b1c05-83c9-4a52-90a3-6250ed3f409f', 'f80c8a06-5bc9-4149-82cd-d4e45002c3a8'),
  ('2024-04-10 10:30:00+00', '2d6e6db2-7f44-4094-94f3-140cfe6b2993', 0, 'fa4d4b89-d1a3-4b76-91fb-1a77d4c49f12', 'c2eb16cf-4469-4da7-9e26-d7ec4fd8cf7f', 'b567d9b8-6cd0-5151-93ed-e5f56013f4b0'),
  ('2024-04-11 09:15:00+00', 'e3b1c4b9-5fa4-42b7-a01b-d0e45fdd5f6c', 0, '9ef6b3ac-2ad9-48e5-9b9b-19c9a1a2057c', '59ec3f8f-43d4-4a6b-b3bb-05f2c7e9b15', 'd789f1f0-8ef2-7373-a5hf-g7h78235h6d2'),
  ('2024-04-12 14:00:00+00', 'b736c5d7-6b39-448f-b0aa-78915b2b98a2', 0, '4a92dc7d-ebc0-4e7f-8b05-98aa241f4488', '9a78f9ff-184d-4a76-819a-77cc80c76dd5', '2c34k6k5-di78-2828-f0mk-l2m23780m1i7'),
  ('2024-04-13 11:45:00+00', '7db1f4d8-1504-42a2-9473-860c9e7ec1a0', 0, 'c5f04845-6b50-4391-8122-9f3305aa52cc', '9f5b1c05-83c9-4a52-90a3-6250ed3f409f', '2m34u6u5-ns78-2828-p0wu-v2w23780w1s7');

