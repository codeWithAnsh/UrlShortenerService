package com.java.urlShortenerService.repository;

import com.java.urlShortenerService.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlConversionRepository extends JpaRepository<Url, Long> {

    @Query("SELECT url FROM Url url where url.long_url = :long_url AND url.client_id = :client_id")
    public Optional<Url> findByUrlAndClientId(@Param("long_url") String long_url,
                                                   @Param("client_id") String client_id);
}
