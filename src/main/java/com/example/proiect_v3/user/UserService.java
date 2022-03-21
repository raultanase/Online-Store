package com.example.proiect_v3.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repo;

    public void saveUser(User user) {
        repo.save(user);
    }

    public void deleteUser(long id) {
        repo.deleteById(id);
    }
}
