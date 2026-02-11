package auca.ac.rw.restfullApiAssignment.controller.restaurant;

import auca.ac.rw.restfullApiAssignment.modal.restaurant.MenuItem;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    private List<MenuItem> menuItems = new ArrayList<>();
    private Long nextId = 9L;

    public MenuController() {
        menuItems.add(new MenuItem(1L, "Spring Rolls", "Crispy vegetable spring rolls", 5.99, "Appetizer", true));
        menuItems.add(new MenuItem(2L, "Caesar Salad", "Fresh romaine lettuce with caesar dressing", 8.49, "Appetizer", true));
        menuItems.add(new MenuItem(3L, "Grilled Salmon", "Atlantic salmon with lemon butter sauce", 18.99, "Main Course", true));
        menuItems.add(new MenuItem(4L, "Beef Steak", "Premium ribeye steak with mashed potatoes", 24.99, "Main Course", true));
        menuItems.add(new MenuItem(5L, "Pasta Carbonara", "Classic Italian pasta with creamy sauce", 14.99, "Main Course", false));
        menuItems.add(new MenuItem(6L, "Chocolate Cake", "Rich dark chocolate layer cake", 7.99, "Dessert", true));
        menuItems.add(new MenuItem(7L, "Ice Cream Sundae", "Vanilla ice cream with toppings", 6.49, "Dessert", true));
        menuItems.add(new MenuItem(8L, "Fresh Juice", "Freshly squeezed orange juice", 3.99, "Beverage", true));
    }

    // GET /api/menu - Get all menu items
    @GetMapping
    public ResponseEntity<List<MenuItem>> getAllMenuItems() {
        return ResponseEntity.ok(menuItems);
    }

    // GET /api/menu/{id} - Get specific menu item
    @GetMapping("/{id}")
    public ResponseEntity<MenuItem> getMenuItemById(@PathVariable Long id) {
        return menuItems.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/menu/category/{category} - Get items by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<MenuItem>> getMenuItemsByCategory(@PathVariable String category) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // GET /api/menu/available - Get only available items
    @GetMapping("/available")
    public ResponseEntity<List<MenuItem>> getAvailableMenuItems(@RequestParam(defaultValue = "true") boolean available) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.isAvailable() == available)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // GET /api/menu/search?name={name} - Search menu items by name
    @GetMapping("/search")
    public ResponseEntity<List<MenuItem>> searchMenuItemsByName(@RequestParam String name) {
        List<MenuItem> result = menuItems.stream()
                .filter(item -> item.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // POST /api/menu - Add new menu item
    @PostMapping
    public ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem menuItem) {
        menuItem.setId(nextId++);
        menuItems.add(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    // PUT /api/menu/{id}/availability - Toggle item availability
    @PutMapping("/{id}/availability")
    public ResponseEntity<MenuItem> toggleAvailability(@PathVariable Long id) {
        for (MenuItem item : menuItems) {
            if (item.getId().equals(id)) {
                item.setAvailable(!item.isAvailable());
                return ResponseEntity.ok(item);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /api/menu/{id} - Remove menu item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        boolean removed = menuItems.removeIf(item -> item.getId().equals(id));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
