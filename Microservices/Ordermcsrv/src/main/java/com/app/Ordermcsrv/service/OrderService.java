package com.app.Ordermcsrv.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.Ordermcsrv.dto.OrderItemDTO;
import com.app.Ordermcsrv.dto.OrderResponse;
import com.app.Ordermcsrv.model.CartItem;
import com.app.Ordermcsrv.model.Order;
import com.app.Ordermcsrv.model.OrderItem;
import com.app.Ordermcsrv.model.OrderStatus;
// import com.app.Ordermcsrv.model.Student;
// import com.app.Ordermcsrv.repository.StudentRepository;
// import com.app.Ordermcsrv.repository.CartItemRepository;
import com.app.Ordermcsrv.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CartService cartService;
    // private final StudentRepository studentRepository;
    private final OrderRepository orderRepository;
    // private final CartItemRepository cardItemRepository;

    public Optional<OrderResponse> createOrder(Integer studentid){

        //Validate the Cart
        List<CartItem> cartItems = cartService.getCart(studentid);

        if(cartItems.isEmpty()) return Optional.empty();




        //Validate the User
        // Optional<Student> studentOptional = studentRepository.findById(studentid);
        // if(studentOptional.isEmpty()) return Optional.empty();
        // Student student = studentOptional.get();
        //Calculate totalPrice
        BigDecimal totalPrice = cartItems.stream()
        .map(CartItem::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);  // .reduce(if,else) -> if not found gives zero, found is adding the price
        //reduce - into one single value
        //BigDecimal.Zero if cartItems is empty returns 0
        //::add combines values.


        //Create Order

        Order order = new Order();
        order.setStudentId(studentid);
        order.setStatus(OrderStatus.CONFIRMED);
        order.setTotalAmount(totalPrice);
        List<OrderItem> orderItems = cartItems.stream()
        .map(item -> new OrderItem(
            null,
            item.getProductId(),
            item.getQuantity(),
            item.getPrice(),
            order
        ))
        .toList();
        order.setItems(orderItems);
        Order savOrder = orderRepository.save(order);

        //Clear the cart

        cartService.clearCart(studentid);
        return Optional.of(mapToOrderResponse(savOrder));
    
    }

    private OrderResponse mapToOrderResponse(Order savedOrder){
        return new OrderResponse(
            savedOrder.getId(),
            savedOrder.getTotalAmount(),
            savedOrder.getStatus(),
            savedOrder.getItems().stream().map(orderItem -> new OrderItemDTO(
                orderItem.getId(),
                orderItem.getProductId(),
                orderItem.getQuantity(),
                orderItem.getPrice(),
                orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))
            )).toList(),savedOrder.getCreatedAt()
        );
    }
    
}
