package prodi.otavio.magalums.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import prodi.otavio.magalums.controler.dto.ScheduleNotificationDto;
import prodi.otavio.magalums.entity.Notification;
import prodi.otavio.magalums.entity.Status;
import prodi.otavio.magalums.repository.NotificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void scheduleNotification(ScheduleNotificationDto dto) {
        notificationRepository.save(dto.toNotification());
    }

    public Optional<Notification> findById(Long notificationId) {
        return notificationRepository.findById(notificationId);
    }

    public void cancelNotification(Long notificationId) {
        notificationRepository.findById(notificationId).ifPresent(notification -> {
            notification.setStatus(Status.Values.CANCELLED.toStatus());
            notificationRepository.save(notification);
            logger.info("Notificação {} cancelada.", notificationId);
        });
    }

    public void checkAndSend(LocalDateTime dateTime) {
        List<Notification> notifications = notificationRepository.findByStatusInAndDateTimeBefore(
                List.of(Status.Values.PENDING.toStatus(), Status.Values.ERROR.toStatus()), dateTime);

        if (notifications.isEmpty()) {
            logger.info("Nenhuma notificação pendente para envio.");
        } else {
            logger.info("Enviando {} notificações...", notifications.size());
            notifications.forEach(this::sendNotification);
        }
    }

    private void sendNotification(Notification notification) {
        try {
            // TODO: Implementar lógica de envio de notificação
            logger.info("Enviando notificação ID: {}", notification.getNotificationId());

            notification.setStatus(Status.Values.SUCCESS.toStatus());
            notificationRepository.save(notification);
            logger.info("Notificação {} enviada com sucesso!", notification.getNotificationId());
        } catch (Exception e) {
            logger.error("Erro ao enviar notificação {}: {}", notification.getNotificationId(), e.getMessage());
            notification.setStatus(Status.Values.ERROR.toStatus());
            notificationRepository.save(notification);
        }
    }

    public void deleteAllNotifications() {
        notificationRepository.deleteAll();
    }

}

