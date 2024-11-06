import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
class Todo {
    private static int counter = 1;
    private int id;
    private String name;
    private String details;
    private boolean completed;

    public Todo(String name, String details) {
        this.id = counter++;
        this.name = name;
        this.details = details;
    }

    public int getId() { return id; }
    public boolean isCompleted() { return completed; }
    public void markCompleted() { this.completed = true; }

    @Override
    public String toString() {
        return "ID: " + id + ", Task: " + name + ", Details: " + details +
               ", Status: " + (completed ? "Completed" : "Pending");
    }
}

class TodoView {
    public void display(List<Todo> todos) {
        if (todos.isEmpty()) {
            System.out.println("No tasks available.");
        } else {
            todos.forEach(System.out::println);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}

class TodoController {
    private List<Todo> todos = new ArrayList<>();
    private TodoView view;

    public TodoController(TodoView view) {
        this.view = view;
    }

    public void addTask(String name, String details) {
        todos.add(new Todo(name, details));
        view.showMessage("Task added!");
    }

    public void showTasks() {
        view.display(todos);
    }

    public void completeTask(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                todo.markCompleted();
                view.showMessage("Task marked as completed.");
                return;
            }
        }
        view.showMessage("Task ID not found.");
    }
}

public class Main {
    public static void main(String[] args) {
        TodoView view = new TodoView();
        TodoController controller = new TodoController(view);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n----------- To-Do List Manager ---------------");
            System.out.println("1. Add Task\n2. View Tasks\n3. Complete Task\n4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    System.out.print("Task name: ");
                    String name = scanner.nextLine();
                    System.out.print("Task details: ");
                    String details = scanner.nextLine();
                    controller.addTask(name, details);
                    break;
                case 2:
                    controller.showTasks();
                    break;
                case 3:
                    System.out.print("Enter Task ID to mark as completed: ");
                    int id = scanner.nextInt();
                    controller.completeTask(id);
                    break;
                case 4:
                    System.out.println("Exiting. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
