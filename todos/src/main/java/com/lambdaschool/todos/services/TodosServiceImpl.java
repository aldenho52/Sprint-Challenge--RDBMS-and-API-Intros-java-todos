package com.lambdaschool.todos.services;


import com.lambdaschool.todos.models.Todo;
import com.lambdaschool.todos.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todoService")
public class TodosServiceImpl implements TodosService
{

    @Autowired
    private TodoRepository todorepos;

    @Autowired
    private TodosService todosService;

    @Override
    public void markComplete(long todoid)
    {
        Todo currentTodo = findTodoById(todoid);

        currentTodo.setCompleted(true);

        todorepos.save(currentTodo);
    }

    @Transactional
    @Override
    public Todo findTodoById(long id)
    {
        return todorepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Todo ID " + id + " Not Found"));
    }

}
