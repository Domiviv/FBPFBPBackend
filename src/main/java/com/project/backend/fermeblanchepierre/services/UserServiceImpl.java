package com.project.backend.fermeblanchepierre.services;
import com.project.backend.fermeblanchepierre.entities.User;
import com.project.backend.fermeblanchepierre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository uR;

    public Set<User> getUsers() {
        return new HashSet<>((Collection<User>) uR.findAll());
    }

    public Set<User> getAdministrators() {
        return uR.getAdministrators();
    }

    public Set<User> getCustomers() {
        return uR.getCustomers();
    }

    public User save(User user){
        try {
            user.setPwd(user.getPwd());
            return uR.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Email provided already exists");
        }
    }
    public Iterable<User> saveAll(Set<User> userList){
        try {
            return uR.saveAll(userList);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Email provided already exists");
        }
    }

    public User getUserById(Integer id){
        return uR.findById(id).orElse(null);
    }

    public String deleteUserById(Integer id){
        try {
            uR.deleteById(id);
            return "User removed";
        }catch (EmptyResultDataAccessException e){
            return "The user you want to delete does not exist";
        }
    }

    public String deleteAllUsers() {
        try {
            uR.deleteAll();
            return "User removed";
        }catch (EmptyResultDataAccessException e){
            return "The user you want to delete does not exist";
        }
    }

    public User updateUserById(Integer id, User newUser) {
        User oldUser = uR.findById(id).orElse(null);
        oldUser.setRole(newUser.getRole());
        oldUser.setPwd(newUser.getPwd());
        try{
            oldUser.setEmail(newUser.getEmail());
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Email provided already exists");
        }
        oldUser.setFirstName(newUser.getFirstName());
        oldUser.setLastName(newUser.getLastName());
        return uR.save(oldUser);
    }
}
