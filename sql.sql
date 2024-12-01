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


INSERT INTO `type` (`name`) VALUES
('Action'),
('Comedy'),
('Drama'),
('Horror'),
('Sci-Fi'),
('Romance'),
('Documentary'),
('Animation');

INSERT INTO `type_movie` (`id_movie`, `id_type`) VALUES
(1, 1),  -- Phim 1 thuộc thể loại Action
(2, 6),  -- Phim 2 thuộc thể loại Romance
(3, 4),  -- Phim 3 thuộc thể loại Horror
(4, 2),  -- Phim 4 thuộc thể loại Comedy
(5, 5),  -- Phim 5 thuộc thể loại Sci-Fi
(6, 7),  -- Phim 6 thuộc thể loại Documentary
(7, 4),  -- Phim 7 thuộc thể loại Horror
(8, 8);  -- Phim 8 thuộc thể loại Animation

INSERT INTO `roomschedu_time` (`date`, `start_time`, `end_time`, `ticket_price`, `id_room`, `id_movie`) VALUES
('2024-12-01 10:00:00', '2024-12-01 10:00:00', '2024-12-01 12:00:00', 75.0, 1, 1),  -- Room 1 chiếu Movie 1
('2024-12-01 13:00:00', '2024-12-01 13:00:00', '2024-12-01 15:00:00', 80.0, 1, 2),  -- Room 1 chiếu Movie 2
('2024-12-01 16:00:00', '2024-12-01 16:00:00', '2024-12-01 18:30:00', 85.0, 2, 3),  -- Room 2 chiếu Movie 3
('2024-12-01 19:00:00', '2024-12-01 19:00:00', '2024-12-01 21:30:00', 90.0, 3, 4),  -- Room 3 chiếu Movie 4
('2024-12-02 09:30:00', '2024-12-02 09:30:00', '2024-12-02 11:45:00', 95.0, 2, 5),  -- Room 2 chiếu Movie 5
('2024-12-02 12:00:00', '2024-12-02 12:00:00', '2024-12-02 14:00:00', 100.0, 3, 1); -- Room 3 chiếu Movie 1

INSERT INTO chairtype  (`name`, `color`, `price_more`) VALUES
('Standard', 'Blue', 0),        -- Ghế thường, không tăng giá
('VIP', 'Red', 50000),          -- Ghế VIP, tăng giá 50,000 VND
('Couple', 'Pink', 100000),     -- Ghế đôi, tăng giá 100,000 VND
('Deluxe', 'Black', 150000);    -- Ghế Deluxe, tăng giá 150,000 VND

INSERT INTO `rowchair` (`row_name`, `priority`, `chair_count`, `id_room`) VALUES
('Row A', 1, '10', 1),
('Row B', 2, '12', 1),
('Row C', 1, '15', 2);
('Row C', 1, '15', 1);

-- Insert các ghế vào bảng chair
INSERT INTO `chair` (`chair_name`, `id_rowchair`, `priority`, `chair_type_id`) VALUES
('A1', 1, 1, 1),  -- Ghế A1, thuộc hàng A, ưu tiên cao, loại ghế tiêu chuẩn
('A2', 1, 2, 1),  -- Ghế A2, thuộc hàng A, ưu tiên thấp, loại ghế tiêu chuẩn
('A3', 1, 1, 1),  -- Ghế A3, thuộc hàng A, ưu tiên cao, loại ghế tiêu chuẩn
('A4', 1, 2, 1),  -- Ghế A4, thuộc hàng A, ưu tiên thấp, loại ghế tiêu chuẩn
('A5', 1, 1, 1),  -- Ghế A5, thuộc hàng A, ưu tiên cao, loại ghế tiêu chuẩn
('A6', 1, 2, 1),  -- Ghế A6, thuộc hàng A, ưu tiên thấp, loại ghế tiêu chuẩn
('A7', 1, 1, 1),  -- Ghế A7, thuộc hàng A, ưu tiên cao, loại ghế tiêu chuẩn
('A8', 1, 2, 1),  -- Ghế A8, thuộc hàng A, ưu tiên thấp, loại ghế tiêu chuẩn
('A9', 1, 1, 1),  -- Ghế A9, thuộc hàng A, ưu tiên cao, loại ghế tiêu chuẩn
('A10', 1, 2, 1), -- Ghế A10, thuộc hàng A, ưu tiên thấp, loại ghế tiêu chuẩn
('B1', 2, 1, 2),  -- Ghế B1, thuộc hàng B, ưu tiên cao, loại ghế VIP
('B2', 2, 2, 2),  -- Ghế B2, thuộc hàng B, ưu tiên thấp, loại ghế VIP
('B3', 2, 1, 2),  -- Ghế B3, thuộc hàng B, ưu tiên cao, loại ghế VIP
('B4', 2, 2, 2),  -- Ghế B4, thuộc hàng B, ưu tiên thấp, loại ghế VIP
('B5', 2, 1, 2),  -- Ghế B5, thuộc hàng B, ưu tiên cao, loại ghế VIP
('B6', 2, 2, 2),  -- Ghế B6, thuộc hàng B, ưu tiên thấp, loại ghế VIP
('B7', 2, 1, 2),  -- Ghế B7, thuộc hàng B, ưu tiên cao, loại ghế VIP
('B8', 2, 2, 2),  -- Ghế B8, thuộc hàng B, ưu tiên thấp, loại ghế VIP
('B9', 2, 1, 2),  -- Ghế B9, thuộc hàng B, ưu tiên cao, loại ghế VIP
('B10', 2, 2, 2); -- Ghế B10, thuộc hàng B, ưu tiên thấp, loại ghế VIP

-- Thêm 15 ghế vào hàng C (ghế VIP)
INSERT INTO `chair` (`chair_name`, `id_rowchair`, `priority`, `chair_type_id`) VALUES
('C1', 4, 1, 4),  -- Ghế C1, thuộc hàng C, ưu tiên cao, loại ghế VIP
('C2', 4, 2, 4),  -- Ghế C2, thuộc hàng C, ưu tiên thấp, loại ghế VIP
('C3', 4, 1, 4),  -- Ghế C3, thuộc hàng C, ưu tiên cao, loại ghế VIP
('C4', 4, 2, 4),  -- Ghế C4, thuộc hàng C, ưu tiên thấp, loại ghế VIP
('C5', 4, 1, 4),  -- Ghế C5, thuộc hàng C, ưu tiên cao, loại ghế VIP
('C6', 4, 2, 4),  -- Ghế C6, thuộc hàng C, ưu tiên thấp, loại ghế VIP
('C7', 4, 1, 4),  -- Ghế C7, thuộc hàng C, ưu tiên cao, loại ghế VIP
('C8', 4, 2, 4),  -- Ghế C8, thuộc hàng C, ưu tiên thấp, loại ghế VIP
('C9', 4, 1, 4),  -- Ghế C9, thuộc hàng C, ưu tiên cao, loại ghế VIP
('C10', 4, 2, 4), -- Ghế C10, thuộc hàng C, ưu tiên thấp, loại ghế VIP
('C11', 4, 1, 4), -- Ghế C11, thuộc hàng C, ưu tiên cao, loại ghế VIP
('C12', 4, 2, 4), -- Ghế C12, thuộc hàng C, ưu tiên thấp, loại ghế VIP
('C13', 4, 1, 4), -- Ghế C13, thuộc hàng C, ưu tiên cao, loại ghế VIP
('C14', 4, 2, 4), -- Ghế C14, thuộc hàng C, ưu tiên thấp, loại ghế VIP
('C15', 4, 1, 4); -- Ghế C15, thuộc hàng C, ưu tiên cao, loại ghế VIP

INSERT INTO `voucher` (`discount`, `startday`, `endday`, `quantity`, `detail`) VALUES
(10.5, '2025-01-01', '2025-01-31', 100, 'Giảm 10.5% cho tất cả các sản phẩm'),
(20.0, '2025-02-01', '2025-02-28', 50, 'Giảm 20% khi mua từ 500K'),
(15.0, '2025-03-01', '2025-03-31', 200, 'Giảm 15% cho thành viên VIP'),
(30.0, '2025-04-01', '2025-04-15', 30, 'Giảm 30% trong 2 tuần đầu tháng 4'),
(25.0, '2025-05-01', '2025-05-31', 80, 'Voucher mùa hè giảm 25%');


INSERT INTO `voucher_customer` (`id_voucher`, `id_customer`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 1);

