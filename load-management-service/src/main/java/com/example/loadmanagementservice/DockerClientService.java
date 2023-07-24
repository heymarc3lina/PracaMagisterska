package com.example.loadmanagementservice;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.*;
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

 public void killInstance(){
     Optional<Container> serviceAImage = client.listContainersCmd().exec().stream().filter(e->e.getImage().contains(IMAGE_A_NAME)).findFirst();
     serviceAImage.ifPresent(container -> client.killContainerCmd(container.getId()).exec());
 }

 public boolean canServiceAKill(){
     List<Container> serviceAImages = client.listContainersCmd().exec().stream().filter(e->e.getImage().contains(IMAGE_A_NAME)).toList();
    return serviceAImages.size() > 1;
 }
 public void createInstance(int newNumber){
     String newNames = SERVICE_B_NAME + "-" + newNumber;
     Optional<Container> serviceBImage=  client.listContainersCmd().exec().stream().filter(e->e.getImage().contains(IMAGE_B_NAME)).findFirst();
     if (serviceBImage.isPresent()) {
         String imageId = serviceBImage.get().getImage();
         ServiceSpec serviceSpec = new ServiceSpec()
                 .withName(newNames)
                 .withTaskTemplate(new TaskSpec()
                         .withContainerSpec(new ContainerSpec()
                                         .withImage(imageId)
                                         .withLabels(serviceBImage.get().getLabels())
                                         .withMounts(List.of(new Mount()
                                                 .withSource("/var/lib/docker/volumes/752475f87d2abd44a6967151a49debc778d16faa8054a668cef54747c4a510c3/_data")
                                                 .withTarget("/tmp")))
                                         .withHosts(List.of("rabbitmq-producer_default"))
                         )
                 )
                 .withMode(new ServiceModeConfig());
         client.createServiceCmd(serviceSpec).exec();

     }



 }

}
