package com.tracer.welcomesystem.services;

import com.tracer.welcomesystem.models.UserTasks;
import com.tracer.welcomesystem.repositories.UtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtService {
    private final UtRepository utRepository;

    @Autowired
    public UtService(UtRepository utRepository) {
        this.utRepository = utRepository;
    }

    public UserTasks saveUT(UserTasks ut) {
        UserTasks save = null;
        Optional<UserTasks> utOptional = utRepository.findById(ut.getId());
        if (utOptional.isPresent()) {
            UserTasks ut1 = utOptional.get();
            ut1.setTask(ut.getTask());
            ut1.setUser(ut.getUser());
            save = utRepository.save(ut1);
        } else {
            save = utRepository.save(ut);
        }
        return save;

    }

    public List<UserTasks> getAllUTs() {
        return utRepository.findAll();
    }

    public void deleteUT(Long id) {
        utRepository.deleteById(id);
    }

    public List<UserTasks> getUTByUserId(Long id) {
        return utRepository.findByUserId(id);
    }

}
