package com.stackroute.track.service;

import com.stackroute.track.domain.Track;
import com.stackroute.track.exceptions.TrackAlreadyExistsException;
import com.stackroute.track.exceptions.TrackNotFoundException;

import java.util.List;


public interface TrackService {


        public boolean saveTrack(Track track) throws TrackAlreadyExistsException;

        public boolean deleteTrack(int id) throws TrackNotFoundException;

        public List<Track> getAllTracks();

        public Track getTrackById(int id) throws TrackNotFoundException;

        public Track updateTrack(Track track) throws TrackNotFoundException;




        public List<Track> getTrackByName(String name) throws TrackNotFoundException;
}

