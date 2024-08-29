package com.dplocisif.DPLOCISIF.repository;

import com.dplocisif.DPLOCISIF.model.ItemMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<ItemMaster, String> {
    @Query(value = "SELECT * FROM T_DCST_M_ITEM WHERE depo_code = ?1 AND item_description = ?2", nativeQuery = true)
    List<ItemMaster> findByDepoCodeAndItemDescription(String depoCode, String itemDescription);

    @Query(value = "SELECT * FROM T_DCST_M_ITEM WHERE depo_code = ?1 AND item_code = ?2", nativeQuery = true)
    ItemMaster findByDepoCodeAndItemCode(String depoCode, String itemCode);

    @Query(value = "SELECT * FROM T_DCST_M_ITEM WHERE item_description = ?1", nativeQuery = true)
    Optional<ItemMaster> findByItemDescription(String itemDescription);

    @Query(nativeQuery = true, value = "SELECT DCST_STORE_PG.gen_item_code(:depoCode, :groupCode) FROM dual")
    String callGenItemCode(@Param("depoCode") String depoCode, @Param("groupCode") String groupCode);

//    @Query(value = "SELECT * FROM T_DCST_M_ITEM WHERE depo_code = ?1 AND item_code = ?2 AND item_description = ?3", nativeQuery = true)
//    ItemMaster findByDepoCodeAndItemCodeAndItemDescription(String depoCode, String itemCode, String itemDescription);
//
//    @Query(value = "SELECT * FROM T_DCST_M_ITEM WHERE item_code = ?1 AND item_description = ?2", nativeQuery = true)
//    ItemMaster findByItemCodeAndItemDescription(String itemCode, String itemDescription);
//
//    @Query(value = "SELECT * FROM T_DCST_M_ITEM WHERE item_code = ?1", nativeQuery = true)
//    ItemMaster findByItemCode(String itemCode);
//
//    @Query(value = "SELECT * FROM T_DCST_M_ITEM WHERE depo_code = ?1", nativeQuery = true)
//    List<ItemMaster> findByDepoCode(String depoCode);
//
//    @Query(value = "SELECT * FROM T_DCST_M_ITEM WHERE item_description = ?1", nativeQuery = true)
//    List<ItemMaster> findByItemDescription(String itemDescription);

    @Query("SELECT i FROM ItemMaster i WHERE " +
            "(:depoCode IS NULL OR i.depoCode LIKE CONCAT('%', :depoCode, '%')) AND " +
            "(:itemCode IS NULL OR i.itemCode LIKE CONCAT('%', :itemCode, '%')) AND " +
            "(:itemDescription IS NULL OR i.itemDescription LIKE CONCAT('%', :itemDescription, '%'))")
    Page<ItemMaster> searchItems(@Param("depoCode") String depoCode,
                                 @Param("itemCode") String itemCode,
                                 @Param("itemDescription") String itemDescription,
                                 Pageable pageable);


    @Query(nativeQuery = true, value = "select ITEM_CODE from T_DCST_M_ITEM")
    List<Long> findAllItemCode();
}
