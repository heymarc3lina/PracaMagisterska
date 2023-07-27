package com.example.loadmanagementservice;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class DockerClientService {

    private final String SERVICE_B_NAME = "rabbitmq-producer-serviceB";
    private final String IMAGE_B_NAME = "pietrzyckagata/rabbitmq-serviceb:latest";
    private final String IMAGE_A_NAME = "pietrzyckagata/rabbitmq-servicea:latest";

    private final DefaultDockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder().build();
    DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
            .dockerHost(config.getDockerHost())
            .sslConfig(config.getSSLConfig())
            .maxConnections(100)
            .connectionTimeout(Duration.ofSeconds(30))
            .responseTimeout(Duration.ofSeconds(45))
            .build();
    private final DockerClient client = DockerClientImpl.getInstance(config, httpClient);

    public void killInstance(String imageName) {
        Optional<Container> serviceImage = client.listContainersCmd().exec().stream().filter(e -> e.getImage().contains(imageName)).findFirst();
        serviceImage.ifPresent(container -> client.killContainerCmd(container.getId()).exec());
    }

    public boolean canServiceKill(String imageName) {
        List<Container> serviceAImages = client.listContainersCmd().exec().stream().filter(e -> e.getImage().contains(imageName)).toList();
        return serviceAImages.size() > 1;
    }

    public void createInstance(int newNumber, String serviceName, String imageName) {
        String newNames = serviceName + "-" + newNumber;
        Optional<Container> serviceImage = client.listContainersCmd().exec().stream().filter(e -> e.getImage().contains(imageName)).findFirst();
        if (serviceImage.isPresent()) {
            InspectContainerResponse existingContainer = client.inspectContainerCmd(serviceImage.get().getId()).exec();

            String newContainerId = client.createContainerCmd(serviceImage.get().getImage())
                    .withHostConfig(existingContainer.getHostConfig())
                    .withName(newNames)
                    .withLabels(serviceImage.get().getLabels())
                    .withEnv(List.of("SPRING_RABBITMQ_HOST=rabbitmq"))
                    .exec().getId();

            client.startContainerCmd(newContainerId).exec();

        }
    }
}
