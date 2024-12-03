package com.example.movieticket.service;

import com.example.movieticket.model.Movie;
import com.example.movieticket.model.Type_Movie;
import com.example.movieticket.repository.MovieRepository;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    // Retrieve all products from the database
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    //Retrieve a product by its id
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    // Add a new product to the database
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Update an existing product
    public Movie updateMovie(@NonNull Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId())
                .orElseThrow(() -> new IllegalArgumentException("Movie with id " + movie.getId() + " does not exist"));
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setDetail(movie.getDetail());
        existingMovie.setImage(movie.getImage());
        existingMovie.setDatestart(movie.getDatestart());
        existingMovie.setTimeMovie(movie.getTimeMovie());
        return movieRepository.save(existingMovie);
    }

    // Delete a product by its id
    public void deleteMovie(Long id) {
        if (movieRepository.existsById(id)) {
            throw new IllegalStateException("Movie with id " + id + " does not exist");
        }
        movieRepository.deleteById(id);
    }

    // Lọc phim theo tiêu đề
    public List<Movie> getMoviesByTitle(String title) {
        return movieRepository.findByTitleContaining(title);
    }

    // Lọc phim nổi bật
    public List<Movie> getHighlightedMovies() {
        return movieRepository.findByHighlightTrue();
    }

    public List<Movie> getMoviesByType(Long typeId) {
        return movieRepository.findByTypeId(typeId); // Lấy phim theo loại
    }

    public Page<Movie> getMovies(int page, int size, String keyword, Long type, Boolean isHighlight) {
        Pageable pageable = PageRequest.of(page, size); // Tạo Pageable cho phân trang

        // Tạo Specification mặc định là null
        Specification<Movie> spec = Specification.where(null);

        // Kiểm tra và áp dụng điều kiện tìm kiếm nếu tham số không phải null
        if (keyword != null && !keyword.isEmpty()) {
            spec = spec.and(titleContains(keyword)); // Tìm theo tiêu đề phim
        }

        if (type != null) {
            spec = spec.and(genreContains(List.of(type))); // Tìm theo loại phim
        }

        if (isHighlight != null) {
            spec = spec.and(highlightContains(isHighlight)); // Tìm theo tiêu chí "highlight"
        }

        // Truy vấn và trả về kết quả trang theo các điều kiện
        return movieRepository.findAll(spec, pageable);
    }

    // Specification cho title (keyword)
    private Specification<Movie> titleContains(String keyword) {
        return (root, query, criteriaBuilder) -> {
            if (keyword != null && !keyword.isEmpty()) {
                return criteriaBuilder.like(root.get("title"), "%" + keyword + "%");
            }
            return null;
        };
    }

    // Specification cho genre (type)
    private Specification<Movie> genreContains(List<Long> typeIds) {
        return (root, query, criteriaBuilder) -> {
            if (typeIds != null && !typeIds.isEmpty()) {
                // Join với Type_Movie và tìm kiếm theo typeIds
                Join<Movie, Type_Movie> typeMovieJoin = root.join("listtype_movies", JoinType.LEFT);
                return typeMovieJoin.get("type").get("id").in(typeIds);
            }
            return null;
        };
    }

    // Specification cho highlight (nổi bật)
    private Specification<Movie> highlightContains(Boolean isHighlight) {
        return (root, query, criteriaBuilder) -> {
            if (isHighlight != null) {
                return criteriaBuilder.equal(root.get("highlight"), isHighlight);
            }
            return null;
        };
    }

    public List<Movie> getMoviesByTypes(List<Long> typeIds) {
        return movieRepository.findByTypeIds(typeIds); // Lấy phim theo loại
    }
}
