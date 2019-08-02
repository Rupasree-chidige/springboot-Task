package com.stackroute.muzix.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.muzix.domain.Track;
import com.stackroute.muzix.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzix.exceptions.TrackNotFoundException;
import com.stackroute.muzix.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
  TrackRepository trackRepository;

  @Autowired
  public TrackServiceImpl(TrackRepository trackRepository)
  {
    this.trackRepository=trackRepository;
  }

  //To save the tracks in the database
  @Override
  public Track saveTrack(Track track) throws TrackAlreadyExistsException {
    if(trackRepository.existsById(track.getId()))
    {
      throw new TrackAlreadyExistsException("Track already exists");
    }
    else {
      Track savedTrack = trackRepository.save(track);
      return savedTrack;
    }
  }

  //To update the tracks in the database
  @Override
  public Track updateTrack(Track track) {
    Track savedTrack=trackRepository.save(track);
    return savedTrack;
  }

  //To get all the list of tracks
  @Override
  public List<Track> getAllTracks() {
    return trackRepository.findAll();
  }

  //To delete a track
  @Override
  public boolean deleteTrack(int id) throws TrackNotFoundException {
    //trackRepository.deleteById(id);
    Optional<Track> track = trackRepository.findById(id);

    if(track.isPresent()) {
      trackRepository.deleteById(id);
      return true;
    } else {
      throw new TrackNotFoundException("No employee record exist for given id");
    }
  }

  //To retrieve the track by ID
  @Override
  public Track getTrackById(int id) throws TrackNotFoundException {
    Optional<Track> track = trackRepository.findById(id);

    if(track.isPresent()) {
      return track.get();

    }
    else
    {
      throw new TrackNotFoundException("track does not exist");
    }
    //return trackRepository.findById(id).orElse(null);
  }

  //To retrieve the tracks by name


  @Override
  public void getTopTrack()
  {
    RestTemplate restTemplate=new RestTemplate();
    String ResourceUrl
            = "http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=09d5dac8d10ef21c515f042c164fd32a&format=json";
    ResponseEntity<String> response
            = restTemplate.getForEntity(ResourceUrl, String.class);
    //To use object mapper
    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode root=null;
    try
    {
      //To read the response body
      root = objectMapper.readTree(response.getBody());
      //To store the JSON array
      ArrayNode arrayNode=(ArrayNode) root.path("tracks").path("track");

      //Iterating through each JSON object
      for(int i=0;i<arrayNode.size();i++)
      {
        Track track=new Track();
        //To set the id of the tracks
        track.setId(i+1);
        //For the name of the track
        track.setName(arrayNode.get(i).path("name").asText());
        //For the artist name of the track
        track.setComment(arrayNode.get(i).path("artist").path("name").asText());

        trackRepository.save(track);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
}

