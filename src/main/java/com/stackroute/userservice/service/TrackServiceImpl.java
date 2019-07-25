package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.Track;
import com.stackroute.userservice.exceptions.TrackAlreadyExistsException;
import com.stackroute.userservice.exceptions.TrackNotFoundException;
import com.stackroute.userservice.repository.TrackRepository;
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
    public boolean saveTrack(Track track) throws TrackAlreadyExistsException {
        boolean result=false;
        if(trackRepository.existsById(track.getId()))
        {
            throw new TrackAlreadyExistsException("Track Already Exists");
        }
        else{
            Track saveduser = trackRepository.save(track);
             result=true;
        }
return result;
    }

    @Override
    public boolean deleteTrack(int id) throws TrackNotFoundException {
       boolean result=false;
        if(trackRepository.existsById(id))
       {
           Track track=getTrackById(id);
           trackRepository.delete(track);
           result=true;
       }
       else
       {
           throw new TrackNotFoundException("Track not Found");

       }return  result;
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
