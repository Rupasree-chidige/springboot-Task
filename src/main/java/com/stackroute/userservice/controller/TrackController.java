package com.stackroute.userservice.controller;


import com.stackroute.userservice.domain.Track;
import com.stackroute.userservice.service.TrackService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController {

    TrackService trackService;


    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }


    @PostMapping("saveTrack")
    public ResponseEntity<?> saveUser(@RequestBody Track track)
    {
        ResponseEntity<?> responseEntity;
        try{
            trackService.saveTrack(track);
            responseEntity=new ResponseEntity<String>("Successfully saved", HttpStatus.CREATED);

        }
        catch (Exception ex)
        {
            responseEntity=new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }


    @GetMapping("getTracks")
    public ResponseEntity<?> getAllTracks()
    {
        return new ResponseEntity<List<Track>>(trackService.getAllTracks(), HttpStatus.OK);
    }

    // Implementing PUT method
    @PutMapping("updateTrack")
    public ResponseEntity<?> updateTrack(@RequestBody Track track)
    {
        ResponseEntity responseEntity;
        try{
            trackService.UpdateTrack(track);
            responseEntity=new ResponseEntity("Successfully updated", HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            responseEntity=new ResponseEntity<String>(e.getMessage(),HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    // Implementing DELETE method
    @DeleteMapping(value="deleteTrack/{id}")
    public ResponseEntity<?> deleteTrack(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {
            trackService.deleteTrack(id);
            responseEntity = new ResponseEntity("Successfully deleted", HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping(value="getTrack/{id}")
    public ResponseEntity<?> getTrackbyId(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        try {

            responseEntity = new ResponseEntity(trackService.getTrackById(id), HttpStatus.CREATED);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
    @GetMapping("track/search/{name}")
    public ResponseEntity<?> searchTrack(@PathVariable String name){
        return new ResponseEntity<>(trackService.getTrackByName(name), HttpStatus.OK);

    }
}
