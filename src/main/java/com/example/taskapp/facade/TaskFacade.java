package com.example.taskapp.facade;

import com.example.taskapp.models.Task;
import com.example.taskapp.services.MailService;
import com.example.taskapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TaskFacade {
    @Autowired
    private TaskService taskService;
    @Autowired
    private MailService mailService;

    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    public List<Task> getCompletedTasks() {
        return taskService.getCompletedTasks();
    }

    public Task createTask(Task task, String emailAddress) throws IOException {
        Task createdTask = taskService.createTask(task);
        Map<String, String> emailInfo = new HashMap<>();
        emailInfo.put("contentType", "text/plain");
        emailInfo.put("content", "Task has been crated successfully please complete it by deadline");
        emailInfo.put("toMail", emailAddress);
        emailInfo.put("subject", "Task Created with id : " + createdTask.getId().toString());
        mailService.sendMail(emailInfo);
        return createdTask;
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
