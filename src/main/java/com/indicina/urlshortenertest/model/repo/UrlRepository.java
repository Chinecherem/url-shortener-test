package com.indicina.urlshortenertest.model.repo;

import com.indicina.urlshortenertest.model.UrlDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlDetails, Long> {

    UrlDetails findByLongUrl(String longUrl);
}
