package com.example.mewagent.repositories;
import com.exmaple.meagent.model.Task;
import java.util.Task;
import java.util.Optional;


public class SQLiteTaskRepository implements ITaskRepository {

    @Override
    public void save(Task task){
        return "Task saved";
        // TODO: implement the logic after
    }

    @Override
    public Optional<Task> findById(String id){
        return Optional.empty();
        //TODO: implement the logic after
    }

    @Ovveride
    public List<Task> finAll():
        return List.of();
        //TODO: implement the logic after
    }
}
