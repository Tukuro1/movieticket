package com.example.movieticket.controller;

import com.example.movieticket.dto.MovieDTO;
import com.example.movieticket.model.Director_Actor;
import com.example.movieticket.model.Human_Movie;
import com.example.movieticket.model.Movie;
import com.example.movieticket.model.Room;
import com.example.movieticket.model.RoomSchedu_Time;
import com.example.movieticket.model.Type;
import com.example.movieticket.model.Type_Movie;
import com.example.movieticket.repository.HumanMovieRepository;
import com.example.movieticket.repository.TypeMovieRepository;
import com.example.movieticket.service.DirectorActorService;
import com.example.movieticket.service.HumanMovieService;
import com.example.movieticket.service.MovieService;
import com.example.movieticket.service.TypeMovieService;
import com.example.movieticket.service.TypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
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
@RequestMapping("/admin/movie")
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

    @Autowired
    private DirectorActorService directorActorService;

    @Autowired
    private TypeService typeService;

    // Display a list of all products
    @GetMapping
    public String showMovieList(@RequestParam(defaultValue = "") String searchTerm, // Điều kiện tìm kiếm (nếu có)
            @RequestParam(defaultValue = "0") int page, // Trang hiện tại (mặc định trang đầu tiên)
            @RequestParam(defaultValue = "10") int size, // Số lượng bản ghi trên mỗi trang
            @RequestParam(defaultValue = "id") String sort, // Sắp xếp theo (nếu có)
            Model model, 
            @AuthenticationPrincipal UserDetails userDetails,
            @AuthenticationPrincipal OAuth2User oauth2User) {

                if (userDetails != null) {
                    model.addAttribute("username", userDetails.getUsername());
                }
                if (oauth2User != null) {
                    model.addAttribute("username", oauth2User.getName());
                }
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
        List<Director_Actor> human_movies = directorActorService.getAllDirectorActor();
        List<Type> type_movies = typeService.getAllTypes();
        model.addAttribute("human_movies", human_movies);
        model.addAttribute("type_movies", type_movies);
        return "admin/movie/edit"; // View để tạo phim mới
    }

    // Sửa phim (hiển thị form chỉnh sửa)
    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model) {
        Movie movie = movieService.getMovieById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        model.addAttribute("movie", movie);
        List<Human_Movie> human_movies = human_movieService.getHuman_MovieByIds(List.of(id));
        model.addAttribute("human_movies", human_movies.isEmpty() ? List.of()
                : human_movies.stream().map((item) -> item.getDirector_actor().getId()).toList());
        List<Type_Movie> type_movies = type_movieService.getTypeMovieByIds(List.of(id));
        model.addAttribute("type_movies", type_movies.isEmpty() ? List.of()
                : type_movies.stream().map((item) -> item.getType().getId()).toList());
        System.out.println(type_movies.stream().map((item) -> item.getType().getId()).toList());
        List<Director_Actor> directors = directorActorService.getAllDirectorActor();
        List<Type> types = typeService.getAllTypes();
        model.addAttribute("directors", directors);
        model.addAttribute("types", types);
        return "admin/movie/edit"; // View để chỉnh sửa phim
    }

    // Lưu phim (thêm hoặc cập nhật)
    @PostMapping("/save")
    public String saveMovie(@ModelAttribute Movie movie, @RequestParam(required = false) List<Long> human_movieIds,
            @RequestParam(required = false) List<Long> type_movieIds) {
        Movie savedMovie = movieService.saveOrUpdate(movie);
        List<Director_Actor> human_movies = directorActorService.getAllDirectorActor();
        List<Type> type_movies = typeService.getAllTypes();
        List<Human_Movie> humanMovies = this.human_movieService.getHuman_MovieByIds(List.of(savedMovie.getId()));
        List<Type_Movie> typeMovies = this.type_movieService.getTypeMovieByIds(List.of(savedMovie.getId()));
        humanMovies.forEach(humanMovie -> this.human_movieService.deleteHuman_Movie(humanMovie));
        typeMovies.forEach(typeMovie -> this.type_movieService.deleteTypeMovie(typeMovie));
        if (Optional.ofNullable(human_movieIds).isPresent()) {
            human_movieIds.forEach(human_movieId -> {
                Optional<Director_Actor> human_movie = human_movies.stream()
                        .filter(human_movie1 -> human_movie1.getId() == human_movieId).findFirst();
                human_movie.ifPresent(human_movie1 -> {
                    Human_Movie hm = new Human_Movie();
                    hm.setMovie(savedMovie);
                    hm.setDirector_actor(human_movie1);
                    this.human_movieService.add(hm);
                });
            });
        }
        if (Optional.ofNullable(type_movieIds).isPresent()) {
            type_movieIds.forEach(type_movieId -> {
                Optional<Type> type_movie = type_movies.stream()
                        .filter(type_movie1 -> type_movie1.getId() == type_movieId)
                        .findFirst();
                type_movie.ifPresent(type_movie1 -> {
                    Type_Movie tm = new Type_Movie();
                    tm.setMovie(savedMovie);
                    tm.setType(type_movie1);
                    this.type_movieService.addTypeMovie(tm);
                });
            });
        }
        return "redirect:/admin/movie"; // Redirect back to the movie list page after saving
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
