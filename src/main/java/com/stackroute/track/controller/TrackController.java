package com.stackroute.track.controller;


import com.stackroute.track.domain.Track;
import com.stackroute.track.exceptions.TrackAlreadyExistsException;
import com.stackroute.track.exceptions.TrackNotFoundException;
import com.stackroute.track.service.TrackService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
@Api(tags = {"Track Controller"})
public class TrackController {

    private TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @ApiOperation(value = "Insert a track", response = ResponseEntity.class)
    @PostMapping("track")
    public ResponseEntity<?> saveTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.saveTrack(track);
            responseEntity = new ResponseEntity<>("Successfully created", HttpStatus.CREATED);

        }catch (TrackAlreadyExistsException e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Get a list of all available tracks", response = ResponseEntity.class)
    @GetMapping("track")
    public ResponseEntity<?> getAllTracks(){
        return new ResponseEntity<>(trackService.getAllTracks(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get the track requested by id", response = ResponseEntity.class)
    @GetMapping("track/{id}")
    public ResponseEntity<?> getTrack(@PathVariable String id){
        try {
            return new ResponseEntity<>(trackService.getTrackById(Integer.parseInt(id)), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @ApiOperation(value = "Update a track", response = ResponseEntity.class)
    @PutMapping("track")
    public ResponseEntity<?> updateTrack(@RequestBody Track track){
        ResponseEntity responseEntity;
        try {
            trackService.UpdateTrack(track);
            responseEntity = new ResponseEntity<>("Successfully updated", HttpStatus.OK);

        }catch (TrackNotFoundException e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Delete the track whose id id given", response = ResponseEntity.class)
    @DeleteMapping("track/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable String id){
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(Integer.parseInt(id));
            responseEntity = new ResponseEntity<>("track deleted", HttpStatus.OK);

        }catch (Exception e){
            responseEntity = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "Search all tracks by name", response = ResponseEntity.class)
    @GetMapping("track/search/{name}")
    public ResponseEntity<?> searchTrack(@PathVariable String name) {
        try{
        return new ResponseEntity<>(trackService.getTrackByName(name), HttpStatus.OK);
    }catch (Exception e){
       return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    }
}