package com.example.musicCollection.repo;

import com.example.musicCollection.models.SingerImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepo extends JpaRepository<SingerImage, Long> {
}
