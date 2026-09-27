package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ReviewDTO {

    private Long id;

    @NotBlank(message = "Campo requerido")
    private String text;

    @NotNull(message = "Campo requerido")
	@Positive(message = "Deve ser maior que zero")
    private Long movieId;    
    
    private Long userId;
    private String userName;
    private String userEmail;

	public ReviewDTO(Review review) {
		id = review.getId();
		text = review.getText();
		movieId = review.getMovie().getId();
		userId = review.getId();
		userName = review.getUser().getName();
		userEmail = review.getUser().getEmail();
	}

	public ReviewDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
