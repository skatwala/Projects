package com.disney.studios.domain;
 

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

/**
 * Rating of a Dog by a Customer
 *
 *  
 */
@Entity
public class DogRating {

    @EmbeddedId
    private DogRatingPK pk;

    @Column(nullable = false)
    private Integer score;

    @Column
    private String comment;

    public DogRatingPK getPk() {
		return pk;
	}

	public void setPk(DogRatingPK pk) {
		this.pk = pk;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
     * Create a fully initialized DogRating.
     *
     * @param pk         primiary key of a dog and customer id.
     * @param score      Integer score (1-5)
     * @param comment    Optional comment from the customer
     */
    public DogRating(DogRatingPK pk, Integer score, String comment) {
        this.pk = pk;
        this.score = score;
        this.comment = comment;
    }

    protected DogRating() {
    }
    
}