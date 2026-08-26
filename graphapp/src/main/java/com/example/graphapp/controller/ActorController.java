package com.example.graphapp.controller;

import com.example.graphapp.model.Actor;
import com.example.graphapp.service.ActorService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActorController {

    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @PostMapping("/actors")
    public String createActor(@RequestBody Actor actor) {
        return actorService.createActor(actor);
    }

    @GetMapping("/actors")
    public String getAllActors() {
        return actorService.getAllActors();
    }

    @GetMapping("/actors/{id}")
    public String getActorById(@PathVariable String id) {
        return actorService.getActorById(id);
    }

    @PutMapping("/actors/{id}")
    public String updateActor(
            @PathVariable String id,
            @RequestBody Actor actor) {

        actor.setId(id);

        return actorService.updateActor(actor);
    }

    @DeleteMapping("/actors/{id}")
    public String deleteActor(@PathVariable String id) {
        return actorService.deleteActor(id);
    }
}