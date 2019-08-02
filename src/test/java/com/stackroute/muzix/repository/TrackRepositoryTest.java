package com.stackroute.muzix.repository;

import com.stackroute.muzix.domain.Track;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    TrackRepository trackRepository;
    Track track;

    @Before
    public void setUp() {
        track = new Track();
        track.setName("Kadalle");
        track.setId(1);
        track.setComment("Dear Comrade");
    }

    @After
    public void tearDown() {

        trackRepository.deleteAll();
    }


    @Test
    public void testSaveTrack() {
        trackRepository.save(track);
        Optional<Track> fetchUser = trackRepository.findById(track.getId());
        Assert.assertEquals(1, fetchUser.get().getId());

    }

    @Test
    public void testGetAllTracks() {
        Track u = new Track(10, "Johny", "sthg");
        Track u1 = new Track(11, "Harry", "nthg");
        trackRepository.save(u);
        trackRepository.save(u1);
        List<Track> list = trackRepository.findAll();
        Assert.assertEquals("Johny", list.get(0).getName());


    }
}
