package ua.dkovalov.socialnetwork.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ua.dkovalov.socialnetwork.request.*;

@Configuration
@ComponentScan(basePackages = {"ua.dkovalov.socialnetwork.service", "ua.dkovalov.socialnetwork.validator"})
public class AppConfig {
    @Bean
    public CreateUserRequest createUserRequest() {
        return new CreateUserRequest();
    }

    @Bean
    public DeleteUserRequest deleteUserRequest() {
        return new DeleteUserRequest();
    }

    @Bean
    public CreatePostRequest createPostRequest() {
        return new CreatePostRequest();
    }

    @Bean
    public DeletePostRequest deletePostRequest() {
        return new DeletePostRequest();
    }
}
