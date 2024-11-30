INSERT INTO `area` (`name`) VALUES
('North Area'),
('South Area'),
('East Area'),
('West Area'),
('Central Area'),
('Northwest Area'),
('Southeast Area'),
('Southwest Area'),
('Northeast Area'),
('Midtown Area');


INSERT INTO `branch` (`address`, hotlline , `id_area`) VALUES
('123 Main St, North Area', '0901234567', 1),
('456 Oak St, South Area', '0902345678', 2),
('789 Pine St, East Area', '0903456789', 3),
('101 Maple St, West Area', '0904567890', 4),
('202 Birch St, Central Area', '0905678901', 5),
('303 Cedar St, Northwest Area', '0906789012', 6),
('404 Willow St, Southeast Area', '0907890123', 7),
('505 Elm St, Southwest Area', '0908901234', 8),
('606 Ash St, Northeast Area', '0909012345', 9),
('707 Chestnut St, Midtown Area', '0901122334', 10);

INSERT INTO `room` (`room_number`, `row_count`, `id_branch`) VALUES
('Room 501', '10', 5),
('Room 502', '9', 5),
('Room 601', '4', 6),
('Room 602', '5', 6),
('Room 701', '3', 7),
('Room 702', '4', 7),
('Room 801', '6', 8),
('Room 802', '7', 8),
('Room 901', '5', 9),
('Room 902', '6', 9),
('Room 1001', '8', 10),
('Room 1002', '9', 10);

INSERT INTO `movie` (`datestart`, `detail`, `image`, `time_movie`, `title`, `highlight`) VALUES
('2024-11-29 14:30:00', 'Action movie with intense fighting scenes.', 'https://flixtv.volkovdesign.com/main/img/home/1.jpg', 120, 'Action Movie', 1),
('2024-11-29 17:00:00', 'Romantic drama about love and life.', 'https://flixtv.volkovdesign.com/main/img/home/2.jpg', 90, 'Romantic Love', 0),
('2024-11-29 19:30:00', 'A thrilling mystery with unexpected twists.', 'https://flixtv.volkovdesign.com/main/img/home/3.jpg', 135, 'The Mystery', 1),
('2024-11-30 13:00:00', 'Comedy film with hilarious situations.', 'https://flixtv.volkovdesign.com/main/img/home/4.jpg', 100, 'Funny Times', 0),
('2024-11-30 16:00:00', 'Sci-fi adventure through space and time.', 'https://flixtv.volkovdesign.com/main/img/home/5.jpg', 140, 'Space Odyssey', 1),
('2024-11-30 19:00:00', 'Documentary about wildlife conservation.', 'https://flixtv.volkovdesign.com/main/img/home/6.jpg', 110, 'Saving Earth', 0),
('2024-12-01 14:30:00', 'Horror film with terrifying moments.', 'https://flixtv.volkovdesign.com/main/img/home/7.jpg', 105, 'Nightmare', 1),
('2024-12-01 17:00:00', 'Family-friendly animation movie.', 'https://flixtv.volkovdesign.com/main/img/home/8.jpg', 85, 'Family Adventures', 0);

