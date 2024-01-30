package com.example.taskapp.services;

import com.example.taskapp.models.Task;
import com.example.taskapp.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElse(null);
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.findByCompleted(true);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public int updateTaskStatus(Long taskId){
        return taskRepository.updateTaskStatus(taskId, true);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
