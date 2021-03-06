package tec.bd.todo;

import tec.bd.todo.repository.TodoRepository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Todo {

    private TodoRepository todoRepository;

    public Todo(TodoRepository todoRepo) {
        this.todoRepository = todoRepo;
    }

    public List<TodoRecord> getAll() {
        return this.todoRepository.findAll();
    }

    public List<TodoRecord> getAll(Status status) {
        return this.todoRepository.findAll(status);
    }

    public TodoRecord getById(String id) {
        // TODO: validar que el id no sea nulo y si es nulo lanzar una excepcion
        return this.todoRepository.findById(id);
    }

    public TodoRecord addTodoRecord(TodoRecord record) {
        System.out.println(record.getTitle());
        Objects.requireNonNull(record);
        // Si el titulo es nulo, devolver exception
        if(null == record.getStartDate()) {
            record.setStartDate(new Date());
        }
        record.setStatus(Status.NEW);
        return this.todoRepository.save(record);
    }

    public void deleteTodoRecord(String id) {
//        var elementToDelete = this.getById(record.getId());
//        if (null != elementToDelete) {
//            this.todoRepository.remove(elementToDelete);
//        }
        // TODO: buscar si el record existe, y si existe borrarlo
        this.todoRepository.remove(id);
    }

    public List<TodoRecord> getStartDateRange(Date startDate, Date endDate){
      return this.todoRepository.findByBetweenStartDates(startDate,endDate) ;
    }
    public List<TodoRecord> searchInTitle(String textToSearch){
        return this.todoRepository.findByPatternInTitle(textToSearch);
    }


    public TodoRecord updateTodoRecord(TodoRecord todoRecord){
        return this.todoRepository.update(todoRecord);
    }




}
