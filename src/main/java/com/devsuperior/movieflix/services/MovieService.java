package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Transactional
    public MovieDetailsDTO findById(Long id) {
        return new MovieDetailsDTO(movieRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Filme não encontrado")));
    }

    @Transactional
    public Page<MovieCardDTO> findAll(Pageable pageable, String genres) {
        List<Long> genresLong = null;
        if (!"0".equals(genres)) {
            genresLong = Arrays.stream(genres.split(",")).map(Long::parseLong).toList();
        }
        return movieRepository.searchMoviesByGenre(pageable, genresLong).map(MovieCardDTO::new);
    }
}
