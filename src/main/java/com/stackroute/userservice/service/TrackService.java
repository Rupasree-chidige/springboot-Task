package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.Track;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface TrackService {


        public boolean saveTrack(Track track);

        public boolean deleteTrack(int id);

        public List<Track> getAllTracks();

        public Track getTrackById(int id);

        public boolean UpdateTrack(Track track);




        public List<Track> getTrackByName(String name);
}

