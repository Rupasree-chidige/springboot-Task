package com.stackroute.muzix.service;

import com.stackroute.muzix.model.Track;
import com.stackroute.muzix.repository.TrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TrackServiceImplTest {

    Track track;

    //Create a mock for UserRepository
    @Mock
    TrackRepository trackRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    TrackServiceImpl trackService;
    List<Track> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        track = new Track();
        track.setName("Kadalalle");
        track.setId(1);
        track.setComment("Dear Comrade");
        list = new ArrayList<>();
        list.add(track);

    }

    @Test
    public void deleteTrack() throws Exception {
        when(trackService.deleteTrack(anyInt())).thenReturn(true);
        Boolean result = trackService.deleteTrack(1);
        Assert.assertEquals(true, result);
    }

    @Test
    public void saveTrackTestSuccess() {

        when(trackRepository.save((Track) any())).thenReturn(track);
        boolean savedUser = trackService.saveTrack(track);
        Assert.assertTrue(savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(trackRepository, times(1)).save(track);

    }

    @Test
    public void updateTrack() throws Exception {
        when(trackRepository.existsById(track.getId())).thenReturn(true);
        track.setName("ne neeli");
        when(trackService.updateTrack(anyInt(),any())).thenReturn(true);
        boolean savedTrack = trackService.updateTrack(track.getId(), track);
        Assert.assertTrue(savedTrack);
    }

    @Test
    public void getAllUser() {

        trackRepository.save(track);
        //stubbing the mock to return specific data
        when(trackRepository.findAll()).thenReturn(list);
        List<Track> userlist = trackService.getAllTracks();
        Assert.assertEquals(list, userlist);
    }
}

