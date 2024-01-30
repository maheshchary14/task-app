package com.example.taskapp.controllers;

import com.example.taskapp.converter.TaskConverter;
import com.example.taskapp.dto.TaskDto;
import com.example.taskapp.exceptions.TaskNotFoundException;
import com.example.taskapp.facade.TaskFacade;
import com.example.taskapp.models.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private TaskFacade taskFacade;

    @Autowired
    private TaskConverter taskConverter;

    @GetMapping("/")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        List<Task> tasks = taskFacade.getAllTasks();
        return new ResponseEntity<>(tasks.stream()
                .map(taskConverter::toDto)
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @GetMapping("/completed")
    public ResponseEntity<List<TaskDto>> getCompletedTasks() {
        List<Task> tasks = taskFacade.getCompletedTasks();
        return new ResponseEntity<>(tasks.stream()
                .map(taskConverter::toDto)
                .collect(Collectors.toList()),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        Task task = taskConverter.toEntity(taskDto);
        Task createdTask = taskFacade.createTask(task);
        return new ResponseEntity<>(taskConverter.toDto(createdTask), HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public ResponseEntity<TaskDto> updateTaskStatus(@PathVariable Long taskId) {
        Task updatedTask = taskFacade.updateTaskStatus(taskId);
        if(updatedTask==null) throw new TaskNotFoundException("Task with given id does not exist");
        return new ResponseEntity<>(taskConverter.toDto(updatedTask), HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskFacade.deleteTask(taskId);
        return ResponseEntity.noContent().build();
    }

}

//-----------------------------------------------v1
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//    @Autowired
//    private TaskFacade taskFacade;
//
//    @GetMapping("/")
//    public List<Task> getAllTasks() {
//        return taskFacade.getAllTasks();
//    }
//
//    @GetMapping("/completed")
//    public List<Task> getCompletedTasks() {
//        return taskFacade.getCompletedTasks();
//    }
//
//    @PostMapping("/")
//    public Task createTask(@RequestBody Task task) {
//        return taskFacade.createTask(task);
//    }
//
//}

//-----------------------------------------------v2
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//    @Autowired
//    private TaskFacade taskFacade;
//
//    @Autowired
//    private ModelMapper modelMapper;
//
//    @GetMapping("/")
//    public List<TaskDto> getAllTasks() {
//        List<Task> tasks = taskFacade.getAllTasks();
//        return tasks.stream()
//                .map(task -> modelMapper.map(task, TaskDto.class))
//                .collect(Collectors.toList());
//    }
//
//    @GetMapping("/completed")
//    public List<TaskDto> getCompletedTasks() {
//        List<Task> tasks = taskFacade.getCompletedTasks();
//        return tasks.stream()
//                .map(task -> modelMapper.map(task, TaskDto.class))
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping("/")
//    public TaskDto createTask(@RequestBody TaskDto taskDTO) {
//        Task task = modelMapper.map(taskDTO, Task.class);
//        Task createdTask = taskFacade.createTask(task);
//        return modelMapper.map(createdTask, TaskDto.class);
//    }
//
//}