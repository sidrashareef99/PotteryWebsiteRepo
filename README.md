# Bespoke Pottery - Basic ecommerce application with CRUD functionality and User Authentication. 

Bespoke Pottery is a Spring Boot application that allows users to manage and update their pottery products,browse through different available products while adding them to their cart and generate a receipt at checkout. Bespoke Pottery provides essential features such as updating the quantity of certain products in your cart, filtering products by style and more. User roles such as Admin and  Customer have also been incorporated into the workflow of this application.

## Product Management

**Admin Functionality:** Admin users can create, edit, and delete pottery products.

**Product Details:** Products have key fields like name, type, length, width, height, image URL, price and style (e.g., Thrown, Sculpture).

**Filtering by Style:** Customers can filter products by style (Handbuilt, Thrown, Slab,Sculpture etc.).

### Shopping Cart

**Add to Cart:** Customers can add products to their cart with the desired quantity.

**Update Quantity:** Customers can update the quantity of items in their cart.

**Remove from Cart:** Customers can remove items from their cart.

**Checkout and Print Receipt:** Customers can checkout and print a receipt of their purchase, displaying all products, quantities, and totals.

## User Authentication & Authorization

**User Roles:** The application differentiates between Admin and Customer roles.

**Admin:** Can manage products.

**Customer:** Can browse products and use the cart functionality.

**Login and Registration:** New customers can register, and existing users can log in to access the application.

## Getting Started

### Prerequisites

**Before you start, ensure you have the following installed:**

- Java 17+
- Maven 3.6+
- MySQL or any preferred relational database
- A browser to view the front end

## Running the Application

1. Clone the repository:

```
git clone https://github.com/YourUsername/PotteryWebsiteRepo 

cd PotteryWebsiteRepo
```

2. Database Setup: Create a MySQL database named db1(or any name you prefer, just update the application.properties accordingly).
```
-- Drop and recreate the db1 database
DROP DATABASE IF EXISTS db1;
CREATE DATABASE db1;

-- Use the db1 database
USE db1;
```
3. Update the Database Configuration: Modify src/main/resources/application.properties with your database credentials:
```
spring.datasource.url=jdbc:mysql://localhost:3306/db1
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```
4. Build and Run the Application: Use Maven to build and run the Spring Boot application.
```
mvn clean install
mvn spring-boot:run
```
5. Access the Application: Open your browser and navigate to:
```
http://localhost:8080
```
## Technologies Used 

- Backend: Java, Spring Boot (Spring MVC, Spring Data JPA, Spring Security)
- Database: MySQL (JPA/Hibernate for ORM)
- Frontend: Thymeleaf, HTML5, CSS3
- Build Tool: Maven
- Authentication: Spring Security (BCrypt password encoding)

## Setup
### Project Structure
Here’s a brief overview of the project structure:
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.Shareef_Sidra_PotteryWebsite_CaseStudy
│   │   │       ├── controller
│   │   │       ├── model
│   │   │       ├── repository
│   │   │       ├── service
│   │   │       └── ShareefSidraPotteryWebsiteCaseStudyApplication
│   │   ├── resources
│   │   │   ├── templates
│   │   │   │   ├── cart.html
│   │   │   │   ├── checkout-receipt.html
│   │   │   │   └── products.html
│   │   │   └── application.properties
└───pom.xml
```
### Running the Application Locally

**Registration and Login:** New customers can register via /register, and Admin users can be pre-seeded using the SQL script.

**Product Management:** Admin users can manage products at /products.

**Cart Operations:** Customers can add products to their cart, update quantities, remove items, and checkout.

### SQL Setup 

The application has all models set up that will automatically create the tables for you when you run the application. Make sure to go in and add data into the tables to see results. 

```
-- Insert sample products
INSERT INTO product (name, product_type, length, width, height, sku, description, img_url, price, style) VALUES
('Sylvia Mug', 'Mug', '4','4', '6', ABC123, 'wheelthrown mug, made for household use or decor.' '../images/mug.jpg', ‘15.0’, ‘Thrown’),
('Sylvia Bowl, 'Bowl', '4','4', '3', DEF123, 'handbuilt bowl, made for household use or decor.' '../images/bowl.jpg', ‘5.0’, ‘Handbuilt’),
('Sylvia Plate', 'Plate', '9','9', '1', GHI123, 'wheelthrown plate, made for household use or decor.' '../images/plate.jpg', ‘15.0’, ‘Thrown’),
('Sylvia Teapot, 'Teapot', '6','6', '6', JKL123, 'wheelthrown teapot, made for household use or decor.' '../images/teapot.jpg', ‘15.0’, ‘Thrown’),

-- Create Customer and Admin users
INSERT INTO user (name, username, password, role) VALUES 
('John Doe', 'test@example.com', '$2b$12$encryptedPassword1', ‘CUSTOMER’),
('Admin User', 'admin@gmail.com', '$2b$12$encryptedPassword2', 'ADMIN');
```

## Working with the Application

### Admin Functionalities:
**Adding a Product:** Go to /products/new and fill in the product details (make, model, price, etc.). Click "Save" to add the product.

**Editing a Product:** Go to /products, click "Edit" on any product to update details.

**Deleting a Product:** On the product list, click "Delete" to remove a product.

### Customer Functionalities:
**Browse Products: **Visit /productspage to view the available products. Filter by style using query parameters (e.g., /productspage?style=Thrown).

**Add to Cart:** Add products to your cart by selecting the quantity and clicking "Add to Cart".

**Update or Remove Items:** Manage items in the cart, either updating quantities or removing them completely.

**Checkout and Print Receipt:** After reviewing your cart, click on "Checkout". The receipt page will allow you to print your receipt. A link back to the product page will be available for continued shopping.

## Security
The application uses Spring Security for authentication and role-based access control:

- Customers can view products, add them to the cart, and checkout.
- Admin Users can manage products (create, edit, delete) but cannot perform shopping-related actions.

### User Authentication Flow:

- Registration: New users (customers) can register via the /showRegister page.

- Login: Customers and admins log in via /login. After login, customers will have access to shopping functionalities, while admins can access product management.

## Future Enhancements

1. Custom Orders: Allow customers to create personalized and custom orders based on their preference. 
2. Contact Page: Allow customers to contact and send email to the shop via the form on the contact page. 
3. Order History: Allow customers to view their past orders and receipts.
4. Wishlist Feature: Implement a wishlist where customers can save products for later.
5. Product Ratings and Reviews: Add the ability for customers to leave reviews and rate products.
6. Improved Security: Add features such as email verification and password reset functionality.


