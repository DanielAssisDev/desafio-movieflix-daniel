package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m JOIN FETCH m.genre " +
            "WHERE (:genres IS NULL OR m.genre.id IN :genres) AND LOWER(m.title) LIKE LOWER(CONCAT('%', :title ,'%')) " +
            "ORDER BY CONCAT('m.', :orderBy) asc")
    Page<Movie> searchMoviesByGenre(Pageable pageable, String title, List<Long> genres, String orderBy);
}
