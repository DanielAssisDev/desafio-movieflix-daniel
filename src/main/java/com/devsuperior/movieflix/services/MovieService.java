package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieCardProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AuthService authService;

    @Transactional
    public MovieDetailsDTO findById(Long id) {
        return new MovieDetailsDTO(movieRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Filme não encontrado")));
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public Page<MovieCardDTO> findAll(Pageable pageable, String title, String genres) {
        List<Long> genresLong = List.of();
        if (!"0".equals(genres)) {
            genresLong = Arrays.stream(genres.split(",")).map(Long::parseLong).toList();
        }
        Page<MovieCardProjection> page = movieRepository.searchMovies(pageable, title.trim(), genresLong);
        List<Long> moviesLong = page.map(MovieCardProjection::getId).stream().toList();
        List<Movie> entities = movieRepository.searchMoviesWithCategories(moviesLong);
        return new PageImpl<>(
                entities.stream().map(MovieCardDTO::new).toList(),
                page.getPageable(),
                page.getTotalElements());
    }
}
