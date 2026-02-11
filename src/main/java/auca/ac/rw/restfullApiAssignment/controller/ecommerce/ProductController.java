package auca.ac.rw.restfullApiAssignment.controller.ecommerce;

import auca.ac.rw.restfullApiAssignment.modal.ecommerce.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private List<Product> products = new ArrayList<>();
    private Long nextId = 11L;

    public ProductController() {
        products.add(new Product(1L, "iPhone 15", "Latest Apple smartphone with A16 chip", 999.99, "Electronics", 50, "Apple"));
        products.add(new Product(2L, "Samsung Galaxy S24", "Flagship Android phone with AI features", 899.99, "Electronics", 35, "Samsung"));
        products.add(new Product(3L, "MacBook Pro", "14-inch laptop with M3 processor", 1999.99, "Electronics", 20, "Apple"));
        products.add(new Product(4L, "Nike Air Max", "Comfortable running shoes", 129.99, "Footwear", 100, "Nike"));
        products.add(new Product(5L, "Adidas Ultraboost", "Premium running shoes with boost technology", 179.99, "Footwear", 75, "Adidas"));
        products.add(new Product(6L, "Sony WH-1000XM5", "Noise cancelling wireless headphones", 349.99, "Electronics", 40, "Sony"));
        products.add(new Product(7L, "Levi's 501 Jeans", "Classic straight fit denim jeans", 69.99, "Clothing", 200, "Levi's"));
        products.add(new Product(8L, "The Alchemist", "Bestselling novel by Paulo Coelho", 14.99, "Books", 500, "HarperOne"));
        products.add(new Product(9L, "Samsung TV 55\"", "4K Ultra HD Smart TV", 599.99, "Electronics", 0, "Samsung"));
        products.add(new Product(10L, "Nike Dri-FIT T-Shirt", "Moisture-wicking athletic t-shirt", 34.99, "Clothing", 150, "Nike"));
    }

    // GET /api/products - Get all products with optional pagination
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit) {
        if (page != null && limit != null && page >= 0 && limit > 0) {
            int start = page * limit;
            int end = Math.min(start + limit, products.size());
            if (start >= products.size()) {
                return ResponseEntity.ok(new ArrayList<>());
            }
            return ResponseEntity.ok(products.subList(start, end));
        }
        return ResponseEntity.ok(products);
    }

    // GET /api/products/{productId} - Get product details
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Long productId) {
        return products.stream()
                .filter(p -> p.getProductId().equals(productId))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /api/products/category/{category} - Get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> result = products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // GET /api/products/brand/{brand} - Get products by brand
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<Product>> getProductsByBrand(@PathVariable String brand) {
        List<Product> result = products.stream()
                .filter(p -> p.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // GET /api/products/search?keyword={keyword} - Search by keyword in name or description
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword) {
        List<Product> result = products.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase())
                        || p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // GET /api/products/price-range?min={min}&max={max} - Get products within price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(@RequestParam Double min, @RequestParam Double max) {
        List<Product> result = products.stream()
                .filter(p -> p.getPrice() >= min && p.getPrice() <= max)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // GET /api/products/in-stock - Get products with stockQuantity > 0
    @GetMapping("/in-stock")
    public ResponseEntity<List<Product>> getInStockProducts() {
        List<Product> result = products.stream()
                .filter(p -> p.getStockQuantity() > 0)
                .collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    // POST /api/products - Add new product
    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        product.setProductId(nextId++);
        products.add(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // PUT /api/products/{productId} - Update product details
    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product updatedProduct) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProductId().equals(productId)) {
                updatedProduct.setProductId(productId);
                products.set(i, updatedProduct);
                return ResponseEntity.ok(updatedProduct);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // PATCH /api/products/{productId}/stock?quantity={quantity} - Update stock quantity
    @PatchMapping("/{productId}/stock")
    public ResponseEntity<Product> updateStock(@PathVariable Long productId, @RequestParam int quantity) {
        for (Product product : products) {
            if (product.getProductId().equals(productId)) {
                product.setStockQuantity(quantity);
                return ResponseEntity.ok(product);
            }
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /api/products/{productId} - Delete product
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId) {
        boolean removed = products.removeIf(p -> p.getProductId().equals(productId));
        if (removed) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
