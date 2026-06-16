package com.app.Ordermcsrv.service;

import java.math.BigDecimal;
import java.util.List;
// import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.Ordermcsrv.dto.CartItemRequest;
import com.app.Ordermcsrv.model.CartItem;
// import com.app.Ordermcsrv.model.Product;
// import com.app.Ordermcsrv.model.Student;
import com.app.Ordermcsrv.repository.CartItemRepository;
// import com.app.Ordermcsrv.repository.ProductRepository;
// import com.app.Ordermcsrv.repository.StudentRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class CartService {
    // private final ProductRepository productRepository;
    // private final StudentRepository studentRepository;
    private final CartItemRepository cartItemRepository;

    public boolean addToCart(Integer studentId,CartItemRequest cartItemRequest){
        // Optional<Product> productOpt = productRepository.findById(cartItemRequest.getProduct().getId());
        // if(productOpt.isEmpty()){
        //     return false;
        // }

        // Product product = productOpt.get();
        // if(product.getStockQuantity() < cartItemRequest.getQuantity()){
        //     return false;
        // }
        // Optional<Student> studentOpt = studentRepository.findById(studentId);
        // if(studentOpt.isEmpty()){
        //     return false;
        // }
        // Student student = studentOpt.get();
        CartItem existingCartItem = cartItemRepository.findByStudentAndProduct(studentId,cartItemRequest.getProductId());

        if(existingCartItem == null){
            // create a new cartItem
            CartItem newCartItem = new CartItem();
            newCartItem.setStudentId(studentId);
            newCartItem.setProductId(cartItemRequest.getProductId());
            newCartItem.setQuantity(cartItemRequest.getQuantity());
            newCartItem.setPrice(BigDecimal.valueOf(1000));
            cartItemRepository.save(newCartItem);
            
        }
        else{
            // Update the cartItem
            existingCartItem.setQuantity(existingCartItem.getQuantity()+cartItemRequest.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        }
        return true;



    }
    @Transactional
    public boolean deleteItemFromCart(Integer studentId,Integer productId){
        // Optional<Product> productopt = productRepository.findById(productId);
        // Optional<Student> studentopt = studentRepository.findById(studentId);

        CartItem cartItem = cartItemRepository.findByStudentAndProduct(studentId, productId);

        if(cartItem!=null){
            cartItemRepository.delete(cartItem);
            return true;
        }
        return false;
    }
    public List<CartItem> getCart(Integer studentId){
        return cartItemRepository.findByStudent(studentId);
    
    }

    @Transactional

    public void clearCart(Integer studentId){
        // Optional<Student> studentOpt = studentRepository.findById(studentId);
        // if(studentOpt.isEmpty()) return;
        // Student student = studentOpt.get();
        cartItemRepository.deleteByStudentId(studentId);
    }

    
}
