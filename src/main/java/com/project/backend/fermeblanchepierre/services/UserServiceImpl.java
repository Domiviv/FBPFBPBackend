package com.project.backend.fermeblanchepierre.services;

import com.project.backend.fermeblanchepierre.entities.Role;
import com.project.backend.fermeblanchepierre.entities.User;
import com.project.backend.fermeblanchepierre.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository uR;


    // GET

    public List<User> getAllUsers() {
        return (List<User>) uR.findAll();
    }

    public List<User> getAllAdministrators() {
        return uR.getAllAdministrators();
    }

    public List<User> getAllCustomers() {
        return uR.getAllCustomers();
    }

    public User getUserById(Integer id) {
        return uR.findById(id).orElse(null);
    }

    public User getUserByEmail(String email) {
        return uR.findByEmail(email).orElse(null);
    }


    // POST

    public Boolean addUser(User user) {
        if (this.getUserByEmail(user.getEmail()) == null) {
            uR.save(user);
            return true;
        } else {
            return false;
        }
    }

    public Boolean addCustomer(User user) {
        Role r = new Role();
        r.setIdRole(2);
        user.setRole(r);
        if (this.getUserByEmail(user.getEmail()) == null) {
            uR.save(user);
            return true;
        } else {
            return false;
        }
    }

    public List<User> addUserList(List<User> userList) {
        try {
            return (List<User>) uR.saveAll(userList);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Email provided already exists");
        }
    }


    // DELETE

    public String deleteUserById(Integer id) {
        try {
            uR.deleteById(id);
            return "User removed";
        } catch (EmptyResultDataAccessException e) {
            return "The user you want to delete does not exist";
        }
    }

    public String deleteAllUsers() {
        try {
            uR.deleteAll();
            return "User removed";
        } catch (EmptyResultDataAccessException e) {
            return "The user you want to delete does not exist";
        }
    }


    // PUT

    public Boolean updateUserById(Integer id, User newUser) {
        User oldUser = uR.findById(id).orElse(null);
        if (oldUser != null) {
            oldUser.setRole(newUser.getRole());
            oldUser.setPwd(newUser.getPwd());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setFirstName(newUser.getFirstName());
            oldUser.setLastName(newUser.getLastName());
            uR.save(oldUser);
            return true;
        }
        return false;
    }
}
