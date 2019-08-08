package com.stackroute.track.service;

import com.stackroute.track.domain.Track;
import com.stackroute.track.exceptions.TrackAlreadyExistsException;
import com.stackroute.track.exceptions.TrackNotFoundException;
import com.stackroute.track.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService{
    @Autowired
    TrackRepository trackRepository;

    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {

        Track saveduser=null;
        if(trackRepository.existsById(track.getId()))
        {
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        else{
            saveduser = trackRepository.save(track);

        }
return saveduser;
    }

    @Override
    public Track deleteTrack(int id) throws TrackNotFoundException {
     Track track=null;
        if(trackRepository.existsById(id))
       {
           track=getTrackById(id);
           trackRepository.delete(track);

       }
       else
       {
           throw new TrackNotFoundException("Track not Found");

       }
       return track;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) throws TrackNotFoundException {
        Track track;
        if(getTrackById(id)!=null){

           track= getTrackById(id);
        }
        else {
            throw new TrackNotFoundException("No track found");
        }
        return track;
    }
    @Override
    public Track updateTrack(Track track) throws TrackNotFoundException {
        if (!trackRepository.existsById(track.getId())){
            throw new TrackNotFoundException("Track Not Found!");
        }
        return trackRepository.save(track);
    }

    @Override
    public List<Track> getTrackByName(String name) throws TrackNotFoundException {

        List<Track> trackList = null;
        if(getTrackByName(name)!=null){

            trackList=getTrackByName(name);
        }
        else {
            throw new TrackNotFoundException("No track found");
        }
        return trackList;
    }

}
