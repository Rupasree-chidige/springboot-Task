package com.stackroute.muzix.controller;

import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.GlobalExceptionHandler;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1")
public class TrackController extends GlobalExceptionHandler {
  TrackService trackService;

  @Autowired
  public TrackController(TrackService trackService) {
    this.trackService = trackService;
  }

  //For save track
  @PostMapping("track")
  public ResponseEntity<?> saveTrack (@RequestBody Track track) throws TrackAlreadyExistsException{
    ResponseEntity responseEntity;
    //try
        trackService.saveTrack(track);
        responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);

      //catch (TrackAlreadyExistsException e) {
      //responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);

    return responseEntity;
  }

  //For update track
  @PutMapping("track")
  public ResponseEntity<?> updateTrack(@RequestBody Track track) {
    ResponseEntity responseEntity;
    //try {
        trackService.updateTrack(track);
        responseEntity = new ResponseEntity<String>("successfully updated", HttpStatus.OK);

    //catch (Exception e) {
      //responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    return responseEntity;
  }


  //To retrieve all tracks
  @GetMapping("track")
  public ResponseEntity getAllTracks()
  {
    return new ResponseEntity<List<Track>>(trackService.getAllTracks(),HttpStatus.OK);
  }

  //To delete the track on basis of id
  @DeleteMapping("track/{id}")
  public ResponseEntity deleteTrack(@PathVariable int id)
  {
    try {
      boolean result=trackService.deleteTrack(id);
      return new ResponseEntity<>( result,HttpStatus.OK);
    } catch (TrackNotFoundException e) {
      return new ResponseEntity<>( e.getMessage(),HttpStatus.CONFLICT);
    }

  }

  //To retrieve the record on the basis of id
  @GetMapping("track/{id}")
  public ResponseEntity getTrackById (@PathVariable int id) throws TrackNotFoundException
  {
    ResponseEntity responseEntity;
    //try {
      responseEntity = new ResponseEntity<Track>(trackService.getTrackById(id),HttpStatus.OK);
    //catch (TrackNotFoundException e) {
      //responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
    //}
    return responseEntity;
  }


  //For the mapping of the top tracks from last fm
  @GetMapping("toptrack")
  public ResponseEntity<?> getTopTrack()
  {
    trackService.getTopTrack();
    return new ResponseEntity<String>("Fetched",HttpStatus.OK);
  }
}
