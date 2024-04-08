package com.tracer.welcomesystem.repositories;

import com.tracer.welcomesystem.models.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
}
