package com.stackroute.track.service;

import com.stackroute.track.domain.Track;

import java.util.List;


public interface TrackService {


        public Track saveTrack(Track track);

        public Track deleteTrack(int id);

        public List<Track> getAllTracks();

        public Track getTrackById(int id);

        public Track UpdateTrack(Track track);




        public List<Track> getTrackByName(String name);
}

