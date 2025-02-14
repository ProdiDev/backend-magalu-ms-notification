package prodi.otavio.magalums.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import prodi.otavio.magalums.entity.Channel;
import prodi.otavio.magalums.entity.Status;
import prodi.otavio.magalums.repository.ChannelRepository;
import prodi.otavio.magalums.repository.StatusRepository;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ChannelRepository channelRepository;
    private final StatusRepository statusRepository;

    public DataLoader(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Arrays.stream(Channel.Values.values())
                .map(Channel.Values::toChannel)
                .forEach(channelRepository::save);
        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(statusRepository::save);

    }
}
