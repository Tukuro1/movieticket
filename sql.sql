INSERT INTO `area` (`name`) VALUES
('Khu vực Miền Bắc'),
('Khu vực Miền Nam'),
('Khu vực Miền Đông'),
('Khu vực Miền Tây'),
('Khu vực Miền Trung'),
('Khu vực Tây Bắc'),
('Khu vực Đông Nam Bộ'),
('Khu vực Tây Nam Bộ'),
('Khu vực Đông Bắc'),
('Khu vực Trung tâm');

INSERT INTO `branch` (`address`, hotlline , `id_area`) VALUES
('123 Đường Chính, Khu vực Miền Bắc', '0901234567', 1),
('456 Đường Sồi, Khu vực Miền Nam', '0902345678', 2),
('789 Đường Thông, Khu vực Miền Đông', '0903456789', 3),
('101 Đường Phong, Khu vực Miền Tây', '0904567890', 4),
('202 Đường Bạch Dương, Khu vực Miền Trung', '0905678901', 5),
('303 Đường Tuyết Tùng, Khu vực Tây Bắc', '0906789012', 6),
('404 Đường Liễu, Khu vực Đông Nam Bộ', '0907890123', 7),
('505 Đường Du, Khu vực Tây Nam Bộ', '0908901234', 8),
('606 Đường Tần Bì, Khu vực Đông Bắc', '0909012345', 9),
('707 Đường Dẻ Gai, Khu vực Trung tâm', '0901122334', 10);

INSERT INTO `room` (`room_number`, `row_count`, `id_branch`) VALUES
('Phòng 501', '10', 5),
('Phòng 502', '9', 5),
('Phòng 601', '4', 6),
('Phòng 602', '5', 6),
('Phòng 701', '3', 7),
('Phòng 702', '4', 7),
('Phòng 801', '6', 8),
('Phòng 802', '7', 8),
('Phòng 901', '5', 9),
('Phòng 902', '6', 9),
('Phòng 1001', '8', 10),
('Phòng 1002', '9', 10);

ALTER TABLE movie AUTO_INCREMENT = 1;
INSERT INTO `movie` (`datestart`, `detail`, `image`, `time_movie`, `title`, `highlight`) VALUES
('2024-12-01 00:30:00', 'Phim hành động với những pha chiến đấu căng thẳng.', 'https://flixtv.volkovdesign.com/main/img/home/1.jpg', 120, 'Phim Hành Động', 1),
('2024-12-01 01:00:00', 'Phim tình cảm lãng mạn về tình yêu và cuộc sống.', 'https://flixtv.volkovdesign.com/main/img/home/2.jpg', 90, 'Tình Yêu Lãng Mạn', 0),
('2024-12-01 01:30:00', 'Phim bí ẩn đầy hồi hộp với những khúc ngoặt bất ngờ.', 'https://flixtv.volkovdesign.com/main/img/home/3.jpg', 135, 'Bí Ẩn', 1),
('2024-12-01 02:00:00', 'Phim hài với các tình huống vui nhộn.', 'https://flixtv.volkovdesign.com/main/img/home/4.jpg', 100, 'Thời Gian Vui Nhộn', 0),
('2024-12-01 02:30:00', 'Phiêu lưu khoa học viễn tưởng xuyên không gian và thời gian.', 'https://flixtv.volkovdesign.com/main/img/home/5.jpg', 140, 'Cuộc Phiêu Lưu Không Gian', 1),
('2024-12-01 03:00:00', 'Phim tài liệu về bảo tồn động vật hoang dã.', 'https://flixtv.volkovdesign.com/main/img/home/6.jpg', 110, 'Bảo Vệ Trái Đất', 0),
('2024-12-01 03:30:00', 'Phim kinh dị với những khoảnh khắc đáng sợ.', 'https://flixtv.volkovdesign.com/main/img/home/7.jpg', 105, 'Ác Mộng', 1),
('2024-12-01 04:00:00', 'Phim hoạt hình dành cho cả gia đình.', 'https://flixtv.volkovdesign.com/main/img/home/8.jpg', 85, 'Cuộc Phiêu Lưu Gia Đình', 0);
('2024-12-02 00:00:00', 'Người Sắt Iron Man 2008 Full HD Vietsub Thuyết Minh Người Sắt, Người Sắt 2008 Trong bộ phim Người Sắt, Tony Stark vừa là chủ một tập đoàn công nghệ vừa là một tay chơi kỳ dị. Trong chuyến thăm Afghanistan, anh đã bị một nhóm khủng bố bắt cóc. Họ yêu cầu Tony chế tạo một loại vũ khí hủy diệt để tấn công nước Mỹ. Xem phim này bạn sẽ nhận ra một sự thật phũ phàng rằng những vũ khí do anh chế tạo đang quay lại tấn công anh, Tony bắt tay vào chế tạo những bộ giáp công nghệ cao. Thoát khỏi nơi giam cầm, Tony trở thành người đại diện cho công lý với biệt danh Iron Man. Trong khi đó, một đối tác trong tập đoàn Stark âm mưu lật đổ Tony. Liệu âm mưu này có thành công?', 'https://img.phimmoichilltv.net/images/film/phim-nguoi-sat-201605688.jpg', 122, 'Iron Man (2008)', 1);
('2024-12-02 00:30:00', 'Người Khổng Lồ Xanh Phi Thường The Incredible Hulk 2008 Full HD Vietsub Thuyết Minh The Incredible Hulk 2008 Sau nhiều năm bị biến thành nửa người nửa quái vật The Hulk, nhà khoa học Bruce Banner vẫn phải ẩn náu trong khu rừng rậm Nam Mỹ, tìm cách chữa trị cho bản thân. . Đồng thời, anh vẫn đang bị săn đuổi bởi quân đội Hoa Kỳ, đứng đầu là Tướng Ross và tên Emil Blonsky, kẻ muốn sử dụng sức mạnh của The Hulk. Cũng chính anh chàng này sau đó đã lặp lại tai nạn của Bruce Banner, với hy vọng biến mình thành một siêu anh hùng. Nhưng Emil phát hiện ra rằng anh đã bị biến đổi thành một con quái vật - Abomination- và không bao giờ trở thành con người như Bruce nữa. Đổ lỗi cho Bruce về sự đột biến của anh ta, anh ta săn lùng anh ta để trả thù.', 'https://img.phimmoichilltv.net/images/film/incrediblehulk5-201805584.jpg', 115, 'The Incredible hulk (2008)', 1);
('2024-12-02 01:00:00', 'Tony Stark tự hé lộ thân phận Người sắt của mình cho công chúng và chìm trong hào quang danh vọng. Một kẻ lạ mặt xuất hiện tấn công anh trên đường đua Monte Carlo. Tony bị hiện tượng nhiễm trùng máu đe dọa mạng sống, còn tên sát thủ kia bắt tay với đối thủ của tập đoàn Stark hòng thôn tính Tony.  Phần 2 của Người sắt là lần đầu tiên trợ thủ Warmachine xuất hiện. Phim cũng tiết lộ những bí mật về cha của Tony, Howard Stark, trước khi dẫn dắt câu chuyện sang nhân vật Captain America, đồng đội tương lai của Người sắt tại tổ chức SHIELD.', 'https://yeuphim.cc/storage/images/nguoi-sat-2/7cfe6b549fe9f94928b8bdc0016ac82f.jpg', 125, 'Iron Man 2 (2010)', 1);
('2024-12-02 01:30:00', 'Thần Sấm Thor 2011 Full HD Vietsub Thuyết Minh Thần Sấm, Thor 2011 Phim Thần Sấm là bộ phim được chuyển thể từ bộ truyện tranh nổi tiếng cùng tên của Marvel. Nhân vật chính, Thor là một chiến binh dũng cảm và mạnh mẽ nhưng kiêu ngạo của vương quốc Asgard. Chính những hành động liều lĩnh của Thor đã gây ra một cuộc chiến kéo dài nhiều năm, chính vì thế mà cha anh, Odin, vị vua của các vị thần đã tước bỏ mọi quyền lực của anh và đẩy anh về sống với giống loài của mình. Mọi người. Một nhà khoa học nữ trẻ tuổi, Jane Foster, có tình cảm đặc biệt với Thor khi cô phát hiện ra anh, và cô trở thành mối tình đầu của Thor. Cô ấy đã có ảnh hưởng sâu sắc đến con người Thor và trở thành người mà Thor yêu say đắm. Trong khi đó, thế lực đen tối của Asgard đang âm mưu xâm lược Trái đất. Để bảo vệ những người mình yêu thương, Thor buộc phải đứng lên đối mặt với đội quân đến từ Asgard, đằng sau là kẻ nguy hiểm và độc ác nhất thế giới của anh.', 'https://img.phimmoichilltv.net/images/film/thor-than-sam-2011-201710180.jpg', 115, 'Thor (2011)', 1);
('2024-12-02 02:00:00', 'Captain America: Kẻ Báo Thù Đầu Tiên Captain America: The First Avenger 2011 Full HD Vietsub Thuyết Minh Captain America: The First Avenger (2011) Trong phim Captain America: The First Avenger, là một chàng trai yếu ớt, chỉ nặng 50kg, mang trong mình nỗi uất hận tột cùng vì bị quân đội tấn công. Do sức khỏe không tốt, Steve Rogers ngay lập tức tình nguyện tham gia vào một chương trình thí điểm của chính phủ mang tên Super Soldier. Trong phim, với 1 liều "Siêu huyết thanh" cộng với 1 tia "Vita", Steve đã trở thành một siêu anh hùng với sức khỏe phi thường, nhưng sau đó hàng loạt sự kiện bất ngờ đã khiến Steve trở thành Anh hùng đầu tiên trong truyện. Biệt đội siêu anh hùng Avengers. Thuộc thể loại phim Siêu anh hùng, bộ phim Captain America: The First Avenger thể hiện khát vọng sức mạnh phi thường của con người. Một bộ phim giải trí trực tuyến.', 'https://img.phimmoichilltv.net/images/film/xemphimsoke-bao-thu-dau-tien-201604805.jpg', 124, 'Captain America: The first avenger (2011)', 1);
('2024-12-02 02:30:00', 'Marvel’s The Avengers là bộ phim giả tưởng kể về một nhóm siêu anh hùng với những khả năng đặc biệt, họ bao gồm: Người Sắt, Thor, Captain America, và Người Khổng Lồ được gọi chung với cái tên SHIELD. Mục đích của SHIELD là nhằm bảo vệ Trái đất khỏi âm mưu hủy hoại của những thế lực xấu từ ngoài địa cầu mà kẻ cầm đầu là Loki.', 'https://yeuphim.cc/storage/images/the-avengers-biet-doi-sieu-anh-hung/2cf562341f2e672443198a79367fe774.jpg', 143, 'The avengers (2012)', 1);

INSERT INTO `type` (`name`) VALUES
('Hành Động'),
('Hài'),
('Chính Kịch'),
('Kinh Dị'),
('Khoa Học Viễn Tưởng'),
('Lãng Mạn'),
('Tài Liệu'),
('Hoạt Hình');
('Phiêu Lưu');
('Viễn Tưởng');
('Khoa Học');

INSERT INTO `type_movie` (`id_movie`, `id_type`) VALUES
(1 , 1),  -- Phim 1 thuộc thể loại Hành Động
(2 , 6),  -- Phim 2 thuộc thể loại Lãng Mạn
(3 , 4),  -- Phim 3 thuộc thể loại Kinh Dị
(4 , 2),  -- Phim 4 thuộc thể loại Hài
(5 , 5),  -- Phim 5 thuộc thể loại Khoa Học Viễn Tưởng
(6 , 7),  -- Phim 6 thuộc thể loại Tài Liệu
(7 , 4),  -- Phim 7 thuộc thể loại Kinh Dị
(8 , 8);  -- Phim 8 thuộc thể loại Hoạt Hình
(9 , 1);  -- Phim 9 thuộc thể loại Hành Động
(10, 5);  -- Phim 10 thuộc thể loại Khoa Học Viễn Tưởng
(11, 1);  -- Phim 11 thuộc thể loại Hành Động
(11, 5);  -- Phim 11 thuộc thể loại Khoa Học Viễn Tưởng
(11, 9);  -- Phim 11 thuộc thể loại Phiêu Lưu
(12, 10);  -- Phim 12 thuộc thể loại Viễn Tưởng
(13, 10);  -- Phim 13 thuộc thể loại Viễn Tưởng
(14, 1);  -- Phim 14 thuộc thể loại Hành Động
(14, 11);  -- Phim 14 thuộc thể loại Khoa Học
(14, 10);  -- Phim 14 thuộc thể loại Viễn Tưởng

INSERT INTO roomschedu_time (date, start_time, end_time, ticket_price, id_room, id_movie) VALUES
('2024-12-01 10:00:00', '2024-12-01 10:00:00', '2024-12-01 12:00:00', 75.0, 1, 1),  -- Room 1 chiếu Movie 1
('2024-12-01 13:00:00', '2024-12-01 13:00:00', '2024-12-01 15:00:00', 80.0, 1, 2),  -- Room 1 chiếu Movie 2
('2024-12-01 16:00:00', '2024-12-01 16:00:00', '2024-12-01 18:30:00', 85.0, 2, 3),  -- Room 2 chiếu Movie 3
('2024-12-01 19:00:00', '2024-12-01 19:00:00', '2024-12-01 21:30:00', 90.0, 3, 4),  -- Room 3 chiếu Movie 4
('2024-12-02 09:30:00', '2024-12-02 09:30:00', '2024-12-02 11:45:00', 95.0, 2, 5),  -- Room 2 chiếu Movie 5
('2024-12-02 12:00:00', '2024-12-02 12:00:00', '2024-12-02 14:00:00', 100.0, 3, 1); -- Room 3 chiếu Movie 1

INSERT INTO chairtype  (name, color, price_more) VALUES
('Standard', 'Blue', 0),        -- Ghế thường, không tăng giá
('VIP', 'Red', 50000),          -- Ghế VIP, tăng giá 50,000 VND
('Couple', 'Pink', 100000),     -- Ghế đôi, tăng giá 100,000 VND
('Deluxe', 'Black', 150000);    -- Ghế Deluxe, tăng giá 150,000 VND

ALTER TABLE movie AUTO_INCREMENT = 1;
INSERT INTO rowchair (row_name, priority, chair_count, id_room) VALUES
('Row A', 1, '10', 1),
('Row B', 2, '12', 1),
('Row C', 1, '15', 2),
('Row C', 1, '15', 1);

-- Insert các ghế vào bảng chair
INSERT INTO chair (chair_name, id_rowchair, priority, chair_type_id) VALUES
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
INSERT INTO chair (chair_name, id_rowchair, priority, chair_type_id) VALUES
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

INSERT INTO voucher (discount, startday, endday, quantity, detail) VALUES
(10.5, '2025-01-01', '2025-01-31', 100, 'Giảm 10.5% cho tất cả các sản phẩm'),
(20.0, '2025-02-01', '2025-02-28', 50, 'Giảm 20% khi mua từ 500K'),
(15.0, '2025-03-01', '2025-03-31', 200, 'Giảm 15% cho thành viên VIP'),
(30.0, '2025-04-01', '2025-04-15', 30, 'Giảm 30% trong 2 tuần đầu tháng 4'),
(25.0, '2025-05-01', '2025-05-31', 80, 'Voucher mùa hè giảm 25%');


INSERT INTO voucher_customer (id_voucher, id_customer) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(1, 1);

