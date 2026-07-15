package in.rupeshkumarpro.foodiesapi.service;

import in.rupeshkumarpro.foodiesapi.io.CartRequest;
import in.rupeshkumarpro.foodiesapi.io.CartResponse;

public interface CartService {

    CartResponse addToCart(CartRequest request);

    CartResponse getCart();

    void clearCart();

    CartResponse removeFromCart(CartRequest cartRequest);
}
