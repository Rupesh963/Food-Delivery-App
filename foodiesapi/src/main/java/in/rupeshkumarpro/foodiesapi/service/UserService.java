package in.rupeshkumarpro.foodiesapi.service;

import in.rupeshkumarpro.foodiesapi.io.UserRequest;
import in.rupeshkumarpro.foodiesapi.io.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRequest request);

    String findByUserId();
}
