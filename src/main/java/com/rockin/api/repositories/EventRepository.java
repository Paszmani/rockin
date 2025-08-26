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

    @Query()
    public Page<Event> findUpcomingEvents(@Param("currentData") Date currentDate, Pageable pageable);

    @Query("SELECT e FROM Event e " +
           "LEFT JOIN e.address a " +
           "WHERE e.date >= :currentDate AND " +
           "(:title IS NULL OR e.title LIKE %:title%) AND " +
           "(:city IS NULL OR e.city LIKE %:city%) AND " +
           "(:uf IS NULL OR e.uf LIKE %:uf%) AND " +
           "(:startDate IS NULL OR e.date >= :startDate) AND " +
           "(:endDate IS NULL OR e.date <= :endDate)")
    Page<Event> findFilteredEvents(@Param("currentDate") Date currentDate,
                                   @Param("title") String title,
                                   @Param("city") String city,
                                   @Param("uf") String uf,
                                   @Param("startDate") Date startDate,
                                   @Param("endDate") Date endDate,
                                   Pageable pageable);
}
