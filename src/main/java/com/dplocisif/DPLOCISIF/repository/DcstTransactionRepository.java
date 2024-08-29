package com.dplocisif.DPLOCISIF.repository;


import com.dplocisif.DPLOCISIF.model.DcstTransaction;
import com.dplocisif.DPLOCISIF.model.DsctDepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DcstTransactionRepository extends JpaRepository<DcstTransaction, Long> {

    @Query("SELECT t FROM DcstTransaction t WHERE " +
            "(t.transactionCode = :transactionCode OR t.transDescription = :transDescription)")
    List<DcstTransaction> findAllTransactionCodeAndTransactionDescription(@Param("transactionCode")Long transactionCode, @Param("transDescription") String transDescription);


    @Query("SELECT t FROM DcstTransaction t WHERE " +
        "(:transactionCode IS NULL OR FUNCTION('TO_CHAR', t.transactionCode) LIKE CONCAT('%', :transactionCode, '%')) AND " +
        "(:transDescription IS NULL OR :transDescription = '' OR t.transDescription LIKE CONCAT('%', :transDescription, '%'))")
    List<DcstTransaction> findByTransaction(
        @Param("transactionCode") Long transactionCode,
        @Param("transDescription") String transDescription);

    Optional<DcstTransaction> findByTransactionCode(Long transactionCode);
}
