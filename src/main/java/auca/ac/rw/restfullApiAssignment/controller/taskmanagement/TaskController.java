package auca.ac.rw.restfullApiAssignment.controller.taskmanagement;

import auca.ac.rw.restfullApiAssignment.modal.taskmanagement.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private List<Task> tasks = new ArrayList<>();
    private Long nextId = 6L;

    public TaskController() {
        tasks.add(new Task(1L, "Complete Spring Boot Assignment", "Finish all REST API questions", false, "HIGH", "2026-02-10"));
        tasks.add(new Task(2L, "Study for Midterm", "Review chapters 1-5 for the exam", false, "HIGH", "2026-02-15"));
        tasks.add(new Task(3L, "Buy Groceries", "Get fruits, vegetables, and milk", false, "LOW", "2026-02-09"));
        tasks.add(new Task(4L, "Team Meeting", "Prepare presentation for project meeting", true, "MEDIUM", "2026-02-08"));
        tasks.add(new Task(5L, "Exercise", "Go for a 30-minute jog", false, "MEDIUM", "2026-02-08"));
    }

    // GET /api/tasks - Get all tasks
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(tasks);
    }

    // GET /api/tasks/{taskId} - Get task by ID
    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        return tasks.stream()
                .filter(t -> t.getTaskId().equals(taskId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/tasks/status?completed={true/false} - Get tasks by completion status
    @GetMapping("/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@RequestParam boolean completed) {
        List<Task> result = tasks.stream()
                .filter(t -> t.isCompleted() == completed)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // GET /api/tasks/priority/{priority} - Get tasks by priority
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        List<Task> result = tasks.stream()
                .filter(t -> t.getPriority().equalsIgnoreCase(priority))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // POST /api/tasks - Create new task
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        task.setTaskId(nextId++);
        tasks.add(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);
    }

    // PUT /api/tasks/{taskId} - Update task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task updatedTask) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskId().equals(taskId)) {
                updatedTask.setTaskId(taskId);
                tasks.set(i, updatedTask);
                return ResponseEntity.ok(updatedTask);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH /api/tasks/{taskId}/complete - Mark task as completed
    @PatchMapping("/{taskId}/complete")
    public ResponseEntity<Task> completeTask(@PathVariable Long taskId) {
        for (Task task : tasks) {
            if (task.getTaskId().equals(taskId)) {
                task.setCompleted(true);
                return ResponseEntity.ok(task);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /api/tasks/{taskId} - Delete task
    @DeleteMapping("/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        boolean removed = tasks.removeIf(t -> t.getTaskId().equals(taskId));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
