package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.projections.MovieCardProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query(value = "SELECT m FROM Movie m JOIN FETCH m.genre WHERE (m.id IN :movies) ORDER BY m.title")
    List<Movie> searchMoviesWithCategories(List<Long> movies);

    @Query(value = "SELECT r FROM Review r JOIN FETCH r.movie WHERE r.movie.id = :id")
    List<Review> searchReviews(Long id);

    @Query(nativeQuery = true, value = """
            SELECT * FROM (
            SELECT DISTINCT m.id, m.title FROM tb_movie m
            INNER JOIN tb_genre g ON m.genre_id=g.id
            WHERE (:genres IS NULL OR g.id IN :genres)
            AND LOWER(m.title) LIKE LOWER(CONCAT('%', :title ,'%')))
            AS tb_result
            """, countQuery = """
            SELECT COUNT(*) FROM(
            SELECT DISTINCT m.id, m.title FROM tb_movie m
            INNER JOIN tb_genre g ON m.genre_id=g.id
            WHERE (:genres IS NULL OR g.id IN :genres)
            AND LOWER(m.title) LIKE LOWER(CONCAT('%', :title ,'%'))) AS tb_result
            """)
    Page<MovieCardProjection> searchMovies(Pageable pageable, String title, List<Long> genres);
}
