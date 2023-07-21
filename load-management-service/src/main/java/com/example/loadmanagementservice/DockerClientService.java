package com.example.loadmanagementservice;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class DockerClientService {
    private DockerClient dockerClient;

//    @Bean
//    DockerClient dockerClient() {
//        return DockerClientBuilder.getInstance().build();
//    }

 public void killInstance(){

 }

 public boolean canServiceAKill(){
     dockerClient.listImagesCmd().exec();
     return true;
 }
 public void createInstance(){

    }

}
