package com.stackroute.track.repository;

import com.stackroute.track.domain.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TrackRepository extends JpaRepository<Track,Integer> {

}
