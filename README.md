# REST API Testing Framework

A comprehensive API testing framework built using REST Assured and Cucumber BDD, designed for testing RESTful web services.

## Business Functionality

This framework provides automated testing capabilities for REST APIs with focus on:

### Core Features
1. **API Request Handling**
   - GET, POST, PUT, DELETE methods
   - Request body and header management
   - Query and path parameter handling

2. **Response Validation**
   - Status code verification
   - Response body validation
   - JSON/XML response parsing
   - Schema validation

3. **Authentication & Authorization**
   - Token-based authentication
   - Basic authentication
   - OAuth integration capabilities

### Data Management
- JSON test data handling
- Dynamic test data generation
- Test data externalization
- Response data extraction and reuse

## Project Structure

```
restapi_restassured/
├── RestAssuredAPI_BDDCucumber/    # BDD implementation with Cucumber
└── rest-demo/                     # Core REST Assured implementation
```

## Technology Stack

- Java
- REST Assured
- Cucumber (BDD)
- TestNG/JUnit
- Maven
- JSON/XML parsing libraries
- Gherkin (15.5% of codebase)

## Key Components

### Test Infrastructure
- **API Endpoints**: Centralized endpoint management
- **Request Specifications**: Reusable request configurations
- **Response Validations**: Common validation methods
- **Test Utilities**: Helper methods for testing

### BDD Implementation
- Feature files describing API test scenarios
- Step definitions implementing test steps
- Cucumber hooks for test setup and teardown

## Getting Started

### Prerequisites
1. Java JDK
2. Maven
3. IDE (IntelliJ IDEA/Eclipse)
4. Git

### Installation
```bash
# Clone the repository
git clone https://github.com/SangeethaPriya1991/restapi_restassured.git

# Navigate to project directory
cd restapi_restassured

# Install dependencies
mvn clean install
```

### Running Tests
```bash
# Run all tests
mvn test

# Run specific feature
mvn test -Dcucumber.features="path/to/feature"

# Run with specific tags
mvn test -Dcucumber.filter.tags="@smoke"
```

## Test Data Management

The framework supports multiple approaches for test data handling:
- External JSON/XML files
- In-line test data in feature files
- Dynamic data generation
- Database integration (if required)
