package com.example.graphapp.service;

import com.example.graphapp.model.Actor;
import com.example.graphapp.repository.ActorRepository;

import org.springframework.stereotype.Service;

@Service
public class ActorService {

    private final ActorRepository actorRepository;

    public ActorService(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public String createActor(Actor actor) {
        return actorRepository.createActor(actor);
    }

    public String getAllActors() {
        return actorRepository.getAllActors();
    }

    public String getActorById(String id) {
        return actorRepository.getActorById(id);
    }

    public String updateActor(Actor actor) {
        return actorRepository.updateActor(actor);
    }

    public String deleteActor(String id) {
        return actorRepository.deleteActor(id);
    }
}