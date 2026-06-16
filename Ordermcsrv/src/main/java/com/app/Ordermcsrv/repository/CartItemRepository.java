package com.app.Ordermcsrv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.Ordermcsrv.model.CartItem;
// import com.app.Ordermcsrv.model.Product;
// import com.app.Ordermcsrv.model.Student;
@Repository

public interface CartItemRepository extends JpaRepository<CartItem,Integer>{
    CartItem findByStudentAndProduct(Integer student,Integer product);
    void deleteByStudentIdAndProductId(Integer studentid,Integer productid);

    List<CartItem> findByStudent(Integer student);
    void deleteByStudentId(Integer studentid);
    
}
