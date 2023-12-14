package com.example.CustomerSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CustomerSystem.entity.MstCustomer;

@Repository
public interface CustomerRepository extends JpaRepository<MstCustomer, Integer>, JpaSpecificationExecutor<MstCustomer>{
    @Modifying
    @Query("UPDATE MstCustomer SET deleteYmd = CURRENT_TIMESTAMP WHERE customerId IN :ids")
    void softDeleteByIds(@Param("ids") List<Integer> ids);
    
    MstCustomer findByCustomerId(int customerId);
}
