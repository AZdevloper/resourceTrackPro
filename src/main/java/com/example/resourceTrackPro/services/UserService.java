package com.example.resourceTrackPro.services;

import com.example.resourceTrackPro.entities.User;
import com.example.resourceTrackPro.repositories.UserRepositoryImpl;
import com.example.resourceTrackPro.repositories.UserRepositoryInterface;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserService {

    private UserRepositoryInterface userRepositoryInterface ;
    private  UserRepositoryImpl userRepositoryImpl = new UserRepositoryImpl();

  /*  public UserService(UserRepositoryInterface userRepositoryInterface) {
        this.userRepositoryInterface = userRepositoryInterface;
    }*/
   /* public UserService(UserRepositoryImpl userRepositoryImpl) {
        this.userRepositoryImpl = userRepositoryImpl;
    }*/

    public UserService() {
    }

    public User register(User user){
        userRepositoryImpl.save(user);
        return user;
    }

    public boolean isValidUser( User user) {
        System.out.println("Validating user: " + user);
        Optional<User> byUsername = userRepositoryImpl.findByUsername(user.getUsername());
        return byUsername.isPresent()
                && user.getPassword().equals(byUsername.get().getPassword());
    }

    public boolean validLoginDetails(User user) {
        System.out.println("Validating user: " + user);
      return true;
//        return userRepositoryImpl.findByUsername(user.getUsername()).isEmpty()
//                && user.getPassword() !=null && user.getPassword().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    public boolean login(String username, String password, HttpServletRequest request) {

        System.out.println("Logging in user: " + username + " with password: " + password);
        Optional<User> user = userRepositoryImpl.findByUsername(username);

        if (user.isPresent() && user.get().getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user.get());

            return true;
        }else return false;
    }

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
    }
/*    public List<User> getUsers(int[] userIds){
//        return null;
        Stream.of(userIds)
                .map(userRepositoryImpl::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
               .map(user ->Stream.of(user.getUsername()))
               .forEach(System.out::println);

//                .collect(Collectors.toList());
return null;

    }*/



}
