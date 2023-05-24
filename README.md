## RegisterController

The `RegisterController` handles user registration requests.

### API Endpoints

- `POST /api/v1/register`: Register a new user by providing user details in the request body.

### Exception Handling

The following exceptions are handled:

- `IOException`: Internal server error. Returns a response with an error message and HTTP status code 500.
- `IllegalArgumentException`: Internal server error. Returns a response with an error message and HTTP status code 500.
- `InvalidKeyException`: Bad request. Returns a response with an error message and HTTP status code 400.

## LoginController

The `LoginController` handles user login requests.

### API Endpoints

- `POST /api/v1/login`: Log in a user by providing user credentials in the request body.

### Exception Handling

The following exceptions are handled:

- `IOException`: Internal server error. Returns a response with an error message and HTTP status code 500.
- `InvalidKeyException`: Bad request. Returns a response with an error message and HTTP status code 400.
- `GeneralSecurityException`: Unauthorized access. Returns a response with an error message and HTTP status code 401.

## ValidateController

The `ValidateController` handles JWT validation requests.

### API Endpoints

- `POST /api/v1/validateJwt`: Validate a JWT (JSON Web Token) by providing the token and the user ID in the request body.

### Exception Handling

The following exceptions are handled:

- `IOException`: Internal server error. Returns a response with an error message and HTTP status code 500.
- `GeneralSecurityException`: Unauthorized access. Returns a response with an error message and HTTP status code 401.

## InvalidateController

The `InvalidateController` handles JWT invalidation requests.

### API Endpoints

- `POST /api/v1/inValidateJwt`: Invalidate a JWT by providing the token in the request body.

### Exception Handling

The following exception is handled:

- `GeneralSecurityException`: Unauthorized access. Returns a response with an error message and HTTP status code 401.

Please note that you'll need to configure the appropriate mappings and dependencies for the `Register`, `Login`, `ValidateJwt`, and `InvalidateJwt` classes used in these controllers.
