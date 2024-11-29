package com.example.movieticket.controller;

import com.example.movieticket.model.Movie;
import com.example.movieticket.model.Type;
import com.example.movieticket.model.Type_Movie;
import com.example.movieticket.service.MovieService;
import com.example.movieticket.service.TypeService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private TypeService typeService;

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
        return movieService.getMovies(page, size, keyword, !type.isEmpty() ? Long.parseLong(type) : null, isHighlight);
    }

    @GetMapping("/book-ticket/{movieId}")
    public String bookTicket(@PathVariable("movieId") Long movieId, Model model) {
        Movie movie = movieService.getMovieById(movieId)
                .orElseThrow(() -> new IllegalStateException("Movie not found"));
        model.addAttribute("movie", movie);
        return "home/book-ticket";
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
        List<String> categories = movie.getListtype_movies().stream().map(type_movie -> type_movie.getType().getName())
                .toList();
        model.addAttribute("categories", categories);
        List<Long> typeIds = movie.getListtype_movies().stream().map(type_movie -> type_movie.getType().getId())
                .toList();
        List<Movie> similarMovies = movieService.getMoviesByTypes(typeIds);
        model.addAttribute("similarMovies", similarMovies);
        return "home/movie-detail";
    }

}