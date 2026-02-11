package auca.ac.rw.restfullApiAssignment.controller.userprofile;

import auca.ac.rw.restfullApiAssignment.modal.userprofile.ApiResponse;
import auca.ac.rw.restfullApiAssignment.modal.userprofile.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();
    private Long nextId = 6L;

    public UserProfileController() {
        users.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 25, "Rwanda", "Software developer", true));
        users.add(new UserProfile(2L, "jane_smith", "jane@example.com", "Jane Smith", 30, "Kenya", "Data scientist", true));
        users.add(new UserProfile(3L, "bob_wilson", "bob@example.com", "Bob Wilson", 22, "Rwanda", "Student at AUCA", true));
        users.add(new UserProfile(4L, "alice_jones", "alice@example.com", "Alice Jones", 28, "Uganda", "Project manager", false));
        users.add(new UserProfile(5L, "charlie_brown", "charlie@example.com", "Charlie Brown", 35, "Tanzania", "Business analyst", true));
    }

    // GET /api/users - Get all user profiles
    @GetMapping
    public ResponseEntity<ApiResponse<List<UserProfile>>> getAllUsers() {
        return ResponseEntity.ok(new ApiResponse<>(true, "Users retrieved successfully", users));
    }

    // GET /api/users/{userId} - Get user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> getUserById(@PathVariable Long userId) {
        Optional<UserProfile> user = users.stream()
                .filter(u -> u.getUserId().equals(userId))
                .findFirst();
        if (user.isPresent()) {
            return ResponseEntity.ok(new ApiResponse<>(true, "User found", user.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found with id: " + userId, null));
    }

    // GET /api/users/search/username?username={username} - Search by username
    @GetMapping("/search/username")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByUsername(@RequestParam String username) {
        List<UserProfile> result = users.stream()
                .filter(u -> u.getUsername().toLowerCase().contains(username.toLowerCase()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results for username: " + username, result));
    }

    // GET /api/users/search/country/{country} - Search by country
    @GetMapping("/search/country/{country}")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByCountry(@PathVariable String country) {
        List<UserProfile> result = users.stream()
                .filter(u -> u.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Users from " + country, result));
    }

    // GET /api/users/search/age?min={min}&max={max} - Search by age range
    @GetMapping("/search/age")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchByAgeRange(@RequestParam int min, @RequestParam int max) {
        List<UserProfile> result = users.stream()
                .filter(u -> u.getAge() >= min && u.getAge() <= max)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponse<>(true, "Users with age between " + min + " and " + max, result));
    }

    // POST /api/users - Create a new user profile
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile user) {
        user.setUserId(nextId++);
        users.add(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User profile created successfully", user));
    }

    // PUT /api/users/{userId} - Update user profile
    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserProfile>> updateUser(@PathVariable Long userId, @RequestBody UserProfile updatedUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(userId)) {
                updatedUser.setUserId(userId);
                users.set(i, updatedUser);
                return ResponseEntity.ok(new ApiResponse<>(true, "User profile updated successfully", updatedUser));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found with id: " + userId, null));
    }

    // PATCH /api/users/{userId}/activate - Activate user profile
    @PatchMapping("/{userId}/activate")
    public ResponseEntity<ApiResponse<UserProfile>> activateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                return ResponseEntity.ok(new ApiResponse<>(true, "User profile activated successfully", user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found with id: " + userId, null));
    }

    // PATCH /api/users/{userId}/deactivate - Deactivate user profile
    @PatchMapping("/{userId}/deactivate")
    public ResponseEntity<ApiResponse<UserProfile>> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                return ResponseEntity.ok(new ApiResponse<>(true, "User profile deactivated successfully", user));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found with id: " + userId, null));
    }

    // DELETE /api/users/{userId} - Delete user profile
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        boolean removed = users.removeIf(u -> u.getUserId().equals(userId));
        if (removed) {
            return ResponseEntity.ok(new ApiResponse<>(true, "User profile deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(false, "User not found with id: " + userId, null));
    }
}
