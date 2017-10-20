package com.disney.studios.service;
import com.disney.studios.domain.Breed;
import com.disney.studios.repository.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BreedService {
    private BreedRepository breedRepository;

    @Autowired
    public BreedService(BreedRepository breedRepository) {
        this.breedRepository = breedRepository;
    }

    public Breed createBreed(String code, String name) {
        if (breedRepository.findOne(code) == null) {
            return breedRepository.save(new Breed(code, name));
        } else {
            return null;
        }
    }
    public Iterable<Breed> lookup(){
        return breedRepository.findAll();
    }
    public long total() {
        return breedRepository.count();
    }
}
