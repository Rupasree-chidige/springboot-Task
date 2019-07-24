package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.Track;
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
    public boolean saveTrack(Track track) {
        Track saveduser=trackRepository.save(track);
        return  true;

    }

    @Override
    public boolean deleteTrack(int id) {
       if(trackRepository.existsById(id))
       {
           Track track=getTrackById(id);
           trackRepository.delete(track);
           return true;
       }
       else
       {
           return false;
       }
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id) {
        return getTrackById(id);
    }

    @Override
    public boolean UpdateTrack(Track track) {
        return true;
    }

    @Override
    public List<Track> getTrackByName(String name) {
        return getTrackByName(name);
    }

}
