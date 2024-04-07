package com.tracer.welcomesystem.controller;

import com.tracer.welcomesystem.models.Task;
import com.tracer.welcomesystem.services.TaskService;
import com.tracer.welcomesystem.utils.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    TaskService taskService;

    @GetMapping("/list")
    RespBean getAllTasks(){
        HashMap<Object,Object> map= new HashMap<>();
        List<Task> tasks = taskService.getAllTasks();
        map.put("total", tasks.size());
        map.put("items", tasks);
        return RespBean.ok("Task list", map);
    }

}
