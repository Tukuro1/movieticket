package com.example.movieticket.controller;

import com.example.movieticket.dto.DateDTO;
import com.example.movieticket.dto.ScheduleResponse;
import com.example.movieticket.model.*;
import com.example.movieticket.service.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

        @Autowired
        private MovieService movieService;

        @Autowired
        private TypeService typeService;

        @Autowired
        private RoomScheduTimeService roomScheduTimeService;

        @Autowired
        private BranchService branchService;

        @Autowired
        private AreaService areaService;

        @Autowired
        private RowChairService rowChairService;

        @Autowired
        private ChairTypeService chairTypeService;

        @Autowired
        private StatusChairService status_chairService;

        @Autowired
        private ChairService chairService;

        @Autowired
        private BillService billService;

        @Autowired
        private TicketService ticketService;

        @Autowired
        private UserService userService;

        @Autowired
        private VoucherService voucherService;

        @Autowired
        private VoucherCustomerService voucherCustomerService;

        @GetMapping("/") // Xử lý tất cả yêu cầu đến trang chủ
        public String home(
                        Model model,
                        @AuthenticationPrincipal UserDetails userDetails) {

                // Hiển thị tên người dùng đăng nhập
                if (userDetails != null) {
                        model.addAttribute("username", userDetails.getUsername());
                }

                // Lọc danh sách phim theo tham số
                List<Movie> highlightedMovies = movieService.getHighlightedMovies();

                // Lấy danh sách loại phim
                List<Type> types = typeService.getAllTypes();
                model.addAttribute("types", types);
                model.addAttribute("highlightedMovies", highlightedMovies);
                return "home/home"; // Tên view hiển thị
        }

        @GetMapping("/get-movies")
        @ResponseBody
        public Page<Movie> getMovies(
                        @RequestParam(defaultValue = "0") int page, // Trang hiện tại
                        @RequestParam(defaultValue = "10") int size, // Kích thước trang
                        @RequestParam(defaultValue = "") String keyword, // Từ khóa tìm kiếm
                        @RequestParam(defaultValue = "") String type, // Loại phim (theo loại)
                        @RequestParam(defaultValue = "") Boolean isHighlight // Phim nổi bật (nếu có)
        ) {
                return movieService.getMovies(page, size, keyword, !type.isEmpty() ? Long.parseLong(type) : null,
                                isHighlight);
        }

        @GetMapping("/book-ticket/{movieId}")
        public String bookTicket(@PathVariable("movieId") Long movieId, Model model,
                        @AuthenticationPrincipal UserDetails userDetails) {
                // Hiển thị tên người dùng đăng nhập
                if (userDetails != null) {
                        model.addAttribute("username", userDetails.getUsername());
                }
                Movie movie = movieService.getMovieById(movieId)
                                .orElseThrow(() -> new IllegalStateException("Movie not found"));
                model.addAttribute("movie", movie);

                List<String> categories = movie.getListtype_movies().stream()
                                .map(type_movie -> type_movie.getType().getName())
                                .toList();
                model.addAttribute("categories", categories);
                System.out.println(movieId);
                List<RoomSchedu_Time> schedules = roomScheduTimeService.getSchedulesFromToday().stream()
                                .map(item -> {
                                        System.out.println(item.getMovie().getId());
                                        return item;
                                })
                                .filter(schedule -> schedule.getMovie().getId().equals(movieId)
                                                && (schedule.getDate().isAfter(LocalDate.now())
                                                                || schedule.getDate().equals(LocalDate.now())))
                                .toList();

                DateFormat dateFormat = new SimpleDateFormat("EEE dd M yyyy");
                List<DateDTO> dates = schedules.stream().map(RoomSchedu_Time::getDate).distinct()
                                .map(date -> new DateDTO(dateFormat.format(java.sql.Date.valueOf(date)), date))
                                .toList();

                model.addAttribute("formattedDates", dates); // Pass schedules to the view
                if (!dates.isEmpty()) {
                        model.addAttribute("dayActive", dates.get(0).getDate());
                }
                List<Area> areas = areaService.getAllAreas();
                model.addAttribute("areas", areas);
                if (!areas.isEmpty()) {
                        model.addAttribute("areaActive", areas.get(0));
                        List<Branch> branches = branchService.getAllBranch().stream()
                                        .filter(branch -> branch.getArea().getId().equals(areas.get(0).getId()))
                                        .toList();
                        model.addAttribute("branches", branches);
                        if (!branches.isEmpty()) {
                                Branch branchActive = branches.get(0);
                                model.addAttribute("branchActive", branchActive);
                                List<ScheduleResponse> scheduleResponses = schedules.stream()
                                                .filter(schedule -> schedule.getDate().equals(LocalDate.now())
                                                                && schedule.getRoom().getBranch().getId()
                                                                                .equals(branchActive.getId()))
                                                .map(schedule -> new ScheduleResponse(schedule.getRoom(),
                                                                schedule.getStartTime(),
                                                                schedule.getEndTime(), schedule.getId(),
                                                                schedule.getDate()))
                                                .collect(Collectors.toList());
                                Map<Room, List<ScheduleResponse>> scheduleResponseMap = scheduleResponses.stream()
                                                .collect(Collectors.groupingBy(ScheduleResponse::getRoom)); // Group by
                                                                                                            // room>
                                model.addAttribute("scheduleResponseMap", scheduleResponseMap);

                        }
                } else {
                        model.addAttribute("areaActive", new Area());
                        model.addAttribute("branches", new ArrayList<>());
                        model.addAttribute("branchActive", new Branch());
                        model.addAttribute("scheduleResponseMap", new HashMap<>());
                }
                return "home/book-ticket";
        }

        @GetMapping("book-ticket/movie/{movieId}/schedule/getByBranch/{branchId}")
        @ResponseBody
        public Map<String, List<ScheduleResponse>> getByBranch(@PathVariable("branchId") Long branchId,
                        @PathVariable("movieId") Long movieId,
                        @RequestParam String date) {
                LocalDate parsedDate = LocalDate.parse(date);
                Branch branchActive = branchService.getBranchById(branchId)
                                .orElseThrow(() -> new IllegalStateException("Branch not found"));
                List<RoomSchedu_Time> schedules = roomScheduTimeService.getAll().stream()
                                .filter(schedule -> schedule.getDate().equals(parsedDate)
                                                && schedule.getMovie().getId().equals(movieId))
                                .toList();
                System.out.println(schedules.size() + " " + parsedDate.toString());
                List<ScheduleResponse> scheduleResponses = schedules.stream()
                                .filter(schedule -> {
                                        System.out.println(schedule.getRoom().getBranch().getId() + " "
                                                        + branchActive.getId());
                                        return schedule.getRoom().getBranch().getId().equals(branchActive.getId());
                                })
                                .map(schedule -> new ScheduleResponse(schedule.getRoom(), schedule.getStartTime(),
                                                schedule.getEndTime(), schedule.getId(), schedule.getDate()))
                                .toList();
                return scheduleResponses.stream()
                                .collect(Collectors.groupingBy(response -> response.getRoom().getRoom_number())); // Assuming
                                                                                                                  // `getRoom().getName()`
                                                                                                                  // returns
                                                                                                                  // the
                                                                                                                  // room's
                                                                                                                  // name
        }

        @GetMapping("/movie-details/{movieId}")
        public String movieDetails(@PathVariable("movieId") Long movieId, Model model,
                        @AuthenticationPrincipal UserDetails userDetails) {
                // Hiển thị tên người dùng đăng nhập
                if (userDetails != null) {
                        model.addAttribute("username", userDetails.getUsername());
                }
                Movie movie = movieService.getMovieById(movieId)
                                .orElseThrow(() -> new IllegalStateException("Movie not found"));
                model.addAttribute("movie", movie);
                List<String> categories = movie.getListtype_movies().stream()
                                .map(type_movie -> type_movie.getType().getName())
                                .toList();
                model.addAttribute("categories", categories);
                List<Long> typeIds = movie.getListtype_movies().stream().map(type_movie -> type_movie.getType().getId())
                                .toList();
                List<Movie> similarMovies = movieService.getMoviesByTypes(typeIds);
                model.addAttribute("similarMovies", similarMovies);
                return "home/movie-detail";
        }

        @GetMapping("/payments/{movieId}/schedule/{scheduleId}")
        public String payment(@PathVariable("movieId") Long movieId, @PathVariable("scheduleId") Long scheduleId,
                        Model model, @AuthenticationPrincipal UserDetails userDetails) {
                // Hiển thị tên người dùng đăng nhập
                if (userDetails != null) {
                        model.addAttribute("username", userDetails.getUsername());
                }

                Movie movie = movieService.getMovieById(movieId)
                                .orElseThrow(() -> new IllegalStateException("Movie not found"));
                model.addAttribute("movie", movie);
                RoomSchedu_Time schedu_Time = roomScheduTimeService.findById(scheduleId)
                                .orElseThrow(() -> new IllegalStateException("Schedule not found"));
                model.addAttribute("schedu_Time", schedu_Time);

                List<RowChair> rows = rowChairService.getAllRowChair().stream()
                                .filter(row -> row.getRoom().getId().equals(schedu_Time.getRoom().getId())).sorted(
                                                Comparator.comparing(RowChair::getPriority).reversed())
                                .toList();

                List<Status_Chair> status_chairs = status_chairService.getAllStatusChair().stream()
                                .filter(status_chair -> status_chair.getRoomschedu_time().getId()
                                                .equals(schedu_Time.getId()))
                                .toList();
                rows.forEach(row -> {
                        row.getChairs().forEach(chair -> {
                                chair.setStatus_chair(status_chairs.stream()
                                                .filter(status_chair -> status_chair.getChair().getId()
                                                                .equals(chair.getId()))
                                                .findFirst()
                                                .orElse(null));
                                if (!Objects.isNull(chair.getStatus_chair())) {
                                        System.out.println(chair.getChair_name() + " " + chair.getStatus() + " "
                                                        + chair.getStatus_chair().getId());

                                        if (chair.getStatus_chair().getStatus().equals("booked")) {
                                                chair.setStatus(true);
                                        } else {
                                                chair.setStatus(false);
                                        }
                                } else {
                                        chair.setStatus(false);
                                }
                                // print chari name and status
                                chair.setTempPrice(chair.getChair_type().getPriceMore());
                        });
                });
                model.addAttribute("rows", rows);

                List<Chair_Type> chairTypes = chairTypeService.getAllChairType();
                model.addAttribute("chairTypes", chairTypes);

                return "home/payment";
        }

        @GetMapping("/payments-confirm/{movieId}/schedule/{scheduleId}")
        public String confirm(@PathVariable("movieId") Long movieId, @PathVariable("scheduleId") Long scheduleId,
                        @RequestParam List<Long> seatIds,
                        Model model, @AuthenticationPrincipal UserDetails userDetails) {
                if (userDetails != null) {
                        model.addAttribute("username", userDetails.getUsername());
                }
                Movie movie = movieService.getMovieById(movieId)
                                .orElseThrow(() -> new IllegalStateException("Movie not found"));
                model.addAttribute("movie", movie);
                RoomSchedu_Time schedu_Time = roomScheduTimeService.findById(scheduleId)
                                .orElseThrow(() -> new IllegalStateException("Schedule not found"));
                model.addAttribute("schedu_Time", schedu_Time);
                List<Chair> chairs = chairService.getChairByIds(seatIds);
                model.addAttribute("chairs", chairs);
                float totalPrice = schedu_Time.getTicketPrice() * chairs.size();
                for (Chair chair : chairs) {
                        if (chair.getChair_type() != null && chair.getChair_type().getPriceMore() != 0) {
                                totalPrice += chair.getChair_type().getPriceMore();
                        }
                }
                model.addAttribute("totalPrice", totalPrice);
                User user = userService.findByUsername(userDetails.getUsername())
                                .orElseThrow(() -> new IllegalStateException("User not found"));
                if (Optional.ofNullable(user).isPresent()) {
                        model.addAttribute("user", user);
                        List<Voucher_Customer> voucherCustomers = voucherCustomerService.getAllByUserId(user.getId());
                        List<Voucher> vouchers = voucherCustomers.stream().map(vc -> vc.getVoucher())
                                        .map(item -> {
                                                return item;
                                        })
                                        .filter(item -> item.getEndday().isAfter(LocalDate.now())
                                                        && item.getStartday().isBefore(LocalDate.now()))
                                        .toList();
                        model.addAttribute("vouchers", vouchers);
                }
                return "home/confirm";
        }
}
