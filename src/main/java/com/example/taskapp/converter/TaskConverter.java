package com.example.taskapp.converter;

import com.example.taskapp.dto.TaskDto;
import com.example.taskapp.models.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    private final ModelMapper modelMapper;

    @Autowired
    public TaskConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TaskDto toDto(Task task) {
        return modelMapper.map(task, TaskDto.class);
    }

    public Task toEntity(TaskDto taskDto) {
        return modelMapper.map(taskDto, Task.class);
    }
}
