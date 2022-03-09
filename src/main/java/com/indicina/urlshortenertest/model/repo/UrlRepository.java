package com.indicina.urlshortenertest.model.repo;

import com.indicina.urlshortenertest.model.UrlDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<UrlDetails, Long> {

    Optional<UrlDetails> findByLongUrl(String longUrl);
    Optional<UrlDetails> findByShortUrl(String shortUrl);
    Optional<UrlDetails> findByUrlCode(String urlCode);
}
