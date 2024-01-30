package com.example.taskapp.facade;

import com.example.taskapp.models.Task;
import com.example.taskapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskFacade {
    @Autowired
    private TaskService taskService;

    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    public List<Task> getCompletedTasks() {
        return taskService.getCompletedTasks();
    }

    public Task createTask(Task task) {
        return taskService.createTask(task);
    }

    public Task updateTaskStatus(Long taskId) {
//        Task existingTask = taskRepository.findById(taskId).get();
//        existingTask.setCompleted(true);
//        return taskRepository.save(existingTask);
        int rowsEffected = taskService.updateTaskStatus(taskId);
        if(rowsEffected>0) return taskService.getTaskById(taskId);
        return null;
    }

    public void deleteTask(Long taskId) { taskService.deleteTask(taskId); }

}
