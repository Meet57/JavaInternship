package todo.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import todo.DAO.TodoDAO;
import todo.model.Todo;

import java.util.HashMap;

    public class TodoAction extends ActionSupport {
    private static final long serialVersionUID = 1L;

    private HashMap<String, Object> result = new HashMap<>();
    private int id;
    private String task;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public HashMap<String, Object> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Object> result) {
        this.result = result;
    }

    public String get() throws Exception {
        result.put("list", TodoDAO.getTodos());
        return SUCCESS;
    }

    public String delete() throws Exception {

        TodoDAO.removeTodo(id);

        return SUCCESS;
    }

    public String update() throws Exception {

        TodoDAO.updateTodo(id,task);

        return SUCCESS;
    }

    public String create() throws Exception {

        TodoDAO.addTodo(task);

        return SUCCESS;
    }
}
