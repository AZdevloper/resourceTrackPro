package com.example.resourceTrackPro.repositories;
import com.example.resourceTrackPro.entities.User;
import java.util.Optional;

public interface UserRepositoryInterface {
     User save(User user);
     Optional<User> findByUsername(String user);

}
