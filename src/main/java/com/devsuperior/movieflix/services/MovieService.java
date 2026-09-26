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

    @Transactional
    public Page<MovieCardDTO> findAll(Pageable pageable, String title, String genres) {
        return null;
    }
}
