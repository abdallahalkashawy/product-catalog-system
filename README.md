## Steps to Run the Application

1. **Clone the Project**  
   Clone the project repository to your local machine if you haven't already done so.

2. **Update `application.yml`**  
   Open the `application.yml` or `application.properties` file and provide your PostgreSQL database credentials:
    - Replace `your_database_username` and `your_database_password` with the correct credentials.

3. **Ensure PostgreSQL is Running**  
   Start your PostgreSQL database server, and ensure that the database `productcatalog` exists.

   To create the database, you can use the following command in the PostgreSQL terminal:

   ```sql
   CREATE DATABASE productcatalog;
   ```

4. **Build the Project**  
   Run the following command in the project’s root directory to build the Spring Boot application using Maven:

   ```bash
   mvn clean install
   ```

5. **Run the Spring Boot Application**  
   Once the project has successfully built, start the Spring Boot application with the following command:

   ```bash
   mvn spring-boot:run
   ```

   This will start the application on port `8090` as specified in the configuration. You can access the API at:

   ```
   http://localhost:8090/api/v2/product
   ```

---
# Package: `org.backend.productcatalogsystem.Models`

This package contains the core model classes for the Product Catalog System. The main entities are `Product`, `Category`, and `SubCategory`, which represent the structure of products in the system.

---

## `Product` Entity

### Description:
The `Product` entity represents a product in the catalog. Each product is associated with a `SubCategory`.

### Annotations:
- `@Entity`: Marks this class as a JPA entity.
- `@Data`: A Lombok annotation to automatically generate getters, setters, `toString()`, `equals()`, and `hashCode()` methods.
- `@BatchSize(size = 20)`: Configures the batch size for fetching collections or lazy-loaded entities to optimize performance.
- `@Table(indexes = {@Index(name = "product_name_index", columnList = "name")})`: Creates an index on the `name` column for faster searches by product name.

### Fields:
- **`id: Long`**
    - The unique identifier for each product.
    - Annotated with `@Id` and `@GeneratedValue(strategy = GenerationType.AUTO)` for auto-generated IDs.

- **`name: String`**
    - The name of the product.

- **`description: String`**
    - A detailed description of the product.
    - Annotated with `@Lob` to store large amounts of text data.

- **`subCategory: SubCategory`**
    - A `ManyToOne` relationship to the `SubCategory` entity.
    - Mapped using `@JoinColumn(name = "sub_category_id")`.

---

## `Category` Entity

### Description:
The `Category` entity represents a high-level category to which multiple subcategories can belong. It contains basic information about each category.

### Annotations:
- `@Entity`: Marks this class as a JPA entity.
- `@Data`: A Lombok annotation to automatically generate getters, setters, `toString()`, `equals()`, and `hashCode()` methods.

### Fields:
- **`id: Long`**
    - The unique identifier for each category.
    - Annotated with `@Id` and `@GeneratedValue(strategy = GenerationType.AUTO)` for auto-generated IDs.

- **`name: String`**
    - The name of the category (e.g., Electronics, Books, etc.).

---

## `SubCategory` Entity

### Description:
The `SubCategory` entity represents a subcategory within a `Category`. Each subcategory can have multiple products associated with it.

### Annotations:
- `@Entity`: Marks this class as a JPA entity.
- `@Data`: A Lombok annotation to automatically generate getters, setters, `toString()`, `equals()`, and `hashCode()` methods.

### Fields:
- **`id: Long`**
    - The unique identifier for each subcategory.
    - Annotated with `@Id` and `@GeneratedValue(strategy = GenerationType.AUTO)` for auto-generated IDs.

- **`name: String`**
    - The name of the subcategory.

- **`category: Category`**
    - A `ManyToOne` relationship to the `Category` entity.
    - Mapped using `@JoinColumn(name = "category_id")`.

---

## Relationships Between Entities:
- **Product** and **SubCategory**:  
  A product belongs to a subcategory, and each subcategory can have multiple products (`@ManyToOne` relationship).

- **SubCategory** and **Category**:  
  A subcategory belongs to a category, and each category can have multiple subcategories (`@ManyToOne` relationship).

---


# Package: `org.backend.productcatalogsystem.Services`

This package contains the service layer for the Product Catalog System. The main service class is `ProductService`, which handles the business logic for managing products, including creating, retrieving, and listing products. It interacts with the repository layer and exposes methods that are consumed by the controllers.

---

## `ProductService` Class

### Description:
The `ProductService` class is responsible for managing product-related operations. It contains methods for creating new products, fetching product details by ID or name, and retrieving all products. This service acts as a bridge between the controller layer and the repository layer.

### Annotations:
- `@Service`: Marks this class as a Spring service, meaning it is part of the service layer and will be managed by the Spring container.
- `@AllArgsConstructor`: A Lombok annotation that automatically generates a constructor with all the class fields as parameters. It simplifies dependency injection for the `ProductRepository`.

### Fields:
- **`productRepository: ProductRepository`**
    - A reference to the `ProductRepository`, which is responsible for interacting with the database.

---

### Methods:

#### 1. `createProduct(Product product): ResponseEntity<?>`

- **Description**:  
  This method is responsible for creating a new product. It saves the product to the database using the repository and returns the newly created product.

- **Parameters**:
    - `product`: The product object to be created.

- **Return**:
    - A `ResponseEntity` containing the newly created `Product` object.


#### 2. `getProductById(Long id): ResponseEntity<?>`

- **Description**:  
  This method retrieves a product by its ID. If the ID is invalid or the product does not exist, it returns an error message.

- **Parameters**:
    - `id`: The ID of the product to retrieve.

- **Return**:
    - A `ResponseEntity` containing a `ProductDTO` if the product exists, or an error message if the product is not found.

- **Validation**:
    - If the `id` is null or less than or equal to 0, a bad request response is returned.

# Product Controller Documentation

This package defines the `ProductController` class, responsible for managing product-related operations. It handles HTTP requests related to products using RESTful endpoints.

## Annotations

- `@RestController`: Indicates that this class will handle HTTP requests and return JSON responses.
- `@RequestMapping("/api/v2/product")`: Specifies the base URL for all endpoints in this controller.

## Endpoints

### 1. Create Product

- **URL**: `/api/v2/product`
- **Method**: `POST`
- **Description**: Creates a new product.
- **Parameters**:
    - `@RequestBody Product product`: The product data to create a new product.
- **Response**:
    - `ResponseEntity<?>`: Returns the created product or an error message.

### 2. Get All Products

- **URL**: `/api/v2/product`
- **Method**: `GET`
- **Description**: Retrieves a list of all products.
- **Response**:
    - `ResponseEntity<?>`: Returns a list of all products.

### 3. Get Product by ID

- **URL**: `/api/v2/product/{productId}`
- **Method**: `GET`
- **Description**: Retrieves a product by its ID.
- **Parameters**:
    - `@PathVariable Long productId`: The ID of the product to retrieve.
- **Response**:
    - `ResponseEntity<?>`: Returns the product data or an error message if the product is not found.

### 4. Get Product by Name

- **URL**: `/api/v2/product/{ProductName}`
- **Method**: `GET`
- **Description**: Retrieves a product by its name.
- **Parameters**:
    - `@RequestBody String productName`: The name of the product to retrieve.
- **Response**:
    - `ResponseEntity<?>`: Returns the product data or an error message if the product is not found.

---

# Product DTO Documentation

The `ProductDTO` class is a Data Transfer Object used for transferring product data between different layers of the application.

## Annotations

- `@Data`: Lombok annotation to generate getters, setters, and other utility methods.
- `@AllArgsConstructor`: Generates a constructor with all fields as parameters.
- `@NoArgsConstructor`: Generates a no-argument constructor.
- `@Builder`: Enables the builder pattern for creating instances of this class.

## Fields

- **Long id**: The unique identifier for the product.
- **String name**: The name of the product.
- **String description**: A description of the product.
- **String subcategory_name**: The name of the subcategory the product belongs to.
- **String category_name**: The name of the category the product belongs to.