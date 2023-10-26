package com.example.resourceTrackPro.repositories;
import com.example.resourceTrackPro.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface {
     User save(User user);
     Optional<User> findByUsername(String user);
    /* public User findById(long id);
     public void update(User category);
     public void delete(User category);
     public List<User> findAll();*/

}
