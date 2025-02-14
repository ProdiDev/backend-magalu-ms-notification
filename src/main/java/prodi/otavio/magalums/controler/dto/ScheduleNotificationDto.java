package prodi.otavio.magalums.controler.dto;

import prodi.otavio.magalums.entity.Channel;
import prodi.otavio.magalums.entity.Notification;
import prodi.otavio.magalums.entity.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ScheduleNotificationDto(LocalDateTime dateTime,
                                      String destination,
                                      String message,
                                      Channel.Values channel) {

    public Notification toNotification() {
        return new Notification(
            dateTime,
            destination,
            message,
            channel.toChannel(),
            Status.Values.PENDING.toStatus()
        );
    }

}
