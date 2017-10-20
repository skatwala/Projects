package com.disney.studios.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Tour Rating Primary Key containing a Tour and a Customer Identifier
 
 */
@Embeddable
public class DogRatingPK implements Serializable {
    @ManyToOne
    private Dog dog;

    @Column(insertable = false, updatable = false,nullable = false)
    private Integer customerId;

    public DogRatingPK() {
    }

    /**
     * Fully initialize a Tour Rating Pk
     *
     * @param tour          the tour.
     * @param customerId    the customer identifier.
     */
    public DogRatingPK(Dog dog, Integer customerId) {
        this.dog = dog;
        this.customerId = customerId;
    }

    public Dog getDog() {
        return dog;
    }

    public Integer getCustomerId() {
        return customerId;
    }

     
}