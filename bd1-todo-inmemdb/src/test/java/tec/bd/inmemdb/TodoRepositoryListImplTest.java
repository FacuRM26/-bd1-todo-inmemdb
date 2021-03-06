package tec.bd.inmemdb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tec.bd.repository.inmemdb.TodoRepositoryListImpl;
import tec.bd.todo.TodoRecord;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;


public class TodoRepositoryListImplTest {
    TodoRepositoryListImpl repository;

    @BeforeEach
    public void setup(){
        var simpleTodoRecord= mock(TodoRecord.class);
        given(simpleTodoRecord.getId()).willReturn("simple-todo");
        List<TodoRecord> todoRecordList=new ArrayList<>();
        todoRecordList.add(simpleTodoRecord);
        this.repository= new TodoRepositoryListImpl(todoRecordList);
    }
    @Test
    public void findAll() throws Exception {
        var actual =repository.findAll();
        assertThat(actual).isNotEmpty();
    }

    @Test
    public void findById() throws Exception {
        var actual=repository.findById("simple-todo");
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo("simple-todo");
    }
    @Test
    public void whenNotFoundthenNull() throws Exception{
        var actual=repository.findById("non-exists");
        assertThat(actual).isNotNull();
    }
    @Test
    public void save() throws Exception {
        var todoRecord =new TodoRecord( "Desayuno");
       var newTodoRecord= repository.save(todoRecord);
        var actual =repository.findAll();
       assertThat(newTodoRecord).isNotNull();
       assertThat(newTodoRecord.getId()).isNotBlank();
       assertThat(actual).contains(todoRecord);
    }

    @Test
    public void remove() throws Exception {

        repository.remove("simple-todo");
        var actual =repository.findById("simple-todo");
        assertThat(actual).isNull();

    }
    @Test
    public void update()throws Exception{
        var todoRecord =new TodoRecord( "Desayuno");
        repository.update(todoRecord);
        var actual =repository.findById("simple-todo");
        assertThat(actual).isNull();

    }
    @Test
    public void findByPatternInTitle()throws Exception{
        var pattern = "todo";
        var lista=repository.findByPatternInTitle(pattern);
        assertThat(lista).isNotEmpty();

    }
    @Test
    public void findByBetweenStartDates()throws Exception{
        var startDate = new Date();
        var enddDate = new Date();
        var lista=repository.findByBetweenStartDates(startDate,enddDate);
        assertThat(lista).isNotNull();

    }

}
