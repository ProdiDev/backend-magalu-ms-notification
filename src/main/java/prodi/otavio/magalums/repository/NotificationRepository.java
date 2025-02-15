package prodi.otavio.magalums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import prodi.otavio.magalums.entity.Notification;
import prodi.otavio.magalums.entity.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatusInAndDateTimeBefore(List<Status> status, LocalDateTime dateTime);
}