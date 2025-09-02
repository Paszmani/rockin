package com.rockin.api.repositories;

import com.rockin.api.domain.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {



    @Query("SELECT e.id AS id, e.title AS title, e.description AS description, e.date AS date, e.imgUrl AS imgUrl, e.eventUrl AS eventUrl, a.city AS city, a.uf AS uf " +
            "FROM Event e LEFT JOIN Address a ON e.id = a.event.id " +
            "WHERE e.date >= :currentDate")
    public Page<Event> findUpcomingEvents(@Param("currentData") Date currentDate, Pageable pageable);

    @Query("SELECT e FROM Event e " +
           "LEFT JOIN e.address a " +
           "WHERE (:title = '' OR e.title LIKE %:title%) " +
           "AND (:city = '' OR a.city LIKE %:city%) " +
           "AND (:uf = '' OR a.uf LIKE %:uf%) " +
           "AND (e.date >= :startDate AND e.date <= :endDate)")
    Page<Event> findFilteredEvents(@Param("title") String title,
                                   @Param("city") String city,
                                   @Param("uf") String uf,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate,
                                   Pageable pageable);


}
