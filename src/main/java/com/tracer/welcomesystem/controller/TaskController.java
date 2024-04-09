package com.tracer.welcomesystem.controller;

import com.tracer.welcomesystem.models.Task;
import com.tracer.welcomesystem.services.TaskService;
import com.tracer.welcomesystem.utils.RespBean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/list")
    RespBean getAllTasks(){
        HashMap<Object,Object> map= new HashMap<>();
        List<Task> tasks = taskService.getAllTasks();
        map.put("total", tasks.size());
        map.put("items", tasks);
        return RespBean.ok("Task list", map);
    }

    @PostMapping("/create")
    RespBean createTask(@RequestBody Task task){

        Task save = taskService.saveTask(task);
        HashMap<Object,Object> map= new HashMap<>();
        map.put("task", save);
        return RespBean.ok("Task created", map);
    }



}
