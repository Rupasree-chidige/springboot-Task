package com.stackroute.track.service;

import com.stackroute.track.domain.Track;
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
    public Track saveTrack(Track track)  {
        Track saveduser = null;
        if(trackRepository.existsById(track.getId())) {
            saveduser = trackRepository.save(track);

        }

        return saveduser;
    }

    @Override
    public Track deleteTrack(int id)  {

        Track track=null;
        if(trackRepository.existsById(id))
        {
            track=getTrackById(id);
            trackRepository.delete(track);

        }
        return track;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public Track getTrackById(int id)  {
        Track track=null;
        if(getTrackById(id)!=null)
        {
            track= getTrackById(id);
        }
        return track;
    }

    @Override
    public Track UpdateTrack(Track track)  {
        Track updatedTrack=null;
        if(trackRepository.existsById(track.getId())) {
            updatedTrack = trackRepository.save(track);

        }
        return updatedTrack;
    }
}
