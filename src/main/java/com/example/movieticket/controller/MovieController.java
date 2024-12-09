package com.example.movieticket.controller;

import com.example.movieticket.dto.MovieDTO;
import com.example.movieticket.model.Human_Movie;
import com.example.movieticket.model.Movie;
import com.example.movieticket.model.Room;
import com.example.movieticket.model.RoomSchedu_Time;
import com.example.movieticket.model.Type_Movie;
import com.example.movieticket.repository.HumanMovieRepository;
import com.example.movieticket.repository.TypeMovieRepository;
import com.example.movieticket.service.HumanMovieService;
import com.example.movieticket.service.MovieService;
import com.example.movieticket.service.TypeMovieService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;
    @Autowired
    private HumanMovieService human_movieService;
    @Autowired
    private TypeMovieService type_movieService;

    @Autowired
    private HumanMovieRepository humanMovieRepository;

    @Autowired
    private TypeMovieRepository typeMovieRepository;

    // Display a list of all products
    @GetMapping
    public String showMovieList(@RequestParam(defaultValue = "") String searchTerm, // Điều kiện tìm kiếm (nếu có)
            @RequestParam(defaultValue = "0") int page, // Trang hiện tại (mặc định trang đầu tiên)
            @RequestParam(defaultValue = "10") int size, // Số lượng bản ghi trên mỗi trang
            @RequestParam(defaultValue = "id") String sort, // Sắp xếp theo (nếu có)
            Model model) {
        if (page < 0) {
            page = 0;
        }
        // Lấy danh sách lịch chiếu từ service (có hỗ trợ phân trang và tìm kiếm)
        Page<Movie> movies = movieService.findBySearch(searchTerm, page, size, sort);

        // Thêm dữ liệu vào model để truyền cho view
        model.addAttribute("movies", movies);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", movies.getTotalPages());
        model.addAttribute("totalItems", movies.getTotalElements());
        return "admin/movie/list";
    }

    // Tạo phim mới (hiển thị form thêm mới)
    @GetMapping("/create")
    public String createMovie(Model model) {
        List<Human_Movie> human_movies = human_movieService.getAllHuman_Movie();
        List<Type_Movie> type_movies = type_movieService.getAllTypeMovie();
        model.addAttribute("human_movies", human_movies);
        model.addAttribute("type_movies", type_movies);
        return "admin/movie/edit";  // View để tạo phim mới
    }

    // Sửa phim (hiển thị form chỉnh sửa)
    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        model.addAttribute("movie", movie);
        List<Human_Movie> human_movies = human_movieService.getAllHuman_Movie();
        List<Type_Movie> type_movies = type_movieService.getAllTypeMovie();
        model.addAttribute("human_movies", human_movies);
        model.addAttribute("type_movies", type_movies);
        return "admin/movie/edit";  // View để chỉnh sửa phim
    }

    // Lưu phim (thêm hoặc cập nhật)
    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie, @RequestParam List<Long> human_movieIds, @RequestParam List<Long> type_movieIds) {
        Movie savedMovie = movieService.saveOrUpdate(movie);
         // Xử lý Human_Movie
         if (human_movieIds != null && !human_movieIds.isEmpty()) {
            for (Long humanMovieId : human_movieIds) {
                Optional<Human_Movie> humanMovieOpt = humanMovieRepository.findById(humanMovieId);
                if (humanMovieOpt.isPresent()) {
                    Human_Movie humanMovie = humanMovieOpt.get();
                    humanMovie.setMovie(savedMovie); // Gán movie cho Human_Movie
                    humanMovieRepository.save(humanMovie); // Lưu lại Human_Movie
                }
            }
        }

        // Xử lý Type_Movie
        if (type_movieIds != null && !type_movieIds.isEmpty()) {
            for (Long typeMovieId : type_movieIds) {
                Optional<Type_Movie> typeMovieOpt = typeMovieRepository.findById(typeMovieId);
                if (typeMovieOpt.isPresent()) {
                    Type_Movie typeMovie = typeMovieOpt.get();
                    typeMovie.setMovie(savedMovie); // Gán movie cho Type_Movie
                    typeMovieRepository.save(typeMovie); // Lưu lại Type_Movie
                }
            }
        }
        return "redirect:/admin/movie";  // Redirect back to the movie list page after saving
    }
    @GetMapping("/{id}")
    public String showMovieDetail(@PathVariable Long id, Movie movie, Model model) {
        model.addAttribute("movie", movieService.getMovieById(id)
                .orElseThrow(() -> new IllegalStateException("movie not found")));
        return "movie/detail";
    }

    @GetMapping("/get-movies")
    @ResponseBody
    public Page<MovieDTO> getMovies(@RequestParam(defaultValue = "0") int page, // Trang hiện tại
            @RequestParam(defaultValue = "10") int size, // Kích thước trang
            @RequestParam(defaultValue = "") String keyword, // Từ khóa tìm kiếm (title)
            @RequestParam(defaultValue = "") Long type, // Loại phim (genre)
            @RequestParam(defaultValue = "") Boolean isHighlight) {
        return movieService.getMovies(page, size, keyword, type, isHighlight).map(MovieDTO::new);

    }

    @GetMapping("/all")
    @ResponseBody
    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies().stream().map(MovieDTO::new).toList();
    }

}
