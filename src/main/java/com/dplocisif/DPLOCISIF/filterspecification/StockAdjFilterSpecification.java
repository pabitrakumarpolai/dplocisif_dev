package com.dplocisif.DPLOCISIF.filterspecification;

import com.dplocisif.DPLOCISIF.startupdto.StockAdjFilterDTO;
import com.dplocisif.DPLOCISIF.startupdto.StockAdjViewDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StockAdjFilterSpecification implements Specification<StockAdjViewDTO> {

    private StockAdjFilterDTO filter;

    public StockAdjFilterSpecification(StockAdjFilterDTO filter) {
        this.filter = filter;
    }


    @Override
    public Predicate toPredicate(Root<StockAdjViewDTO> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        if (filter.getDepoCode() != null) {
            predicates.add(builder.like(builder.upper(root.get("depoCode")), "%" + filter.getDepoCode().toUpperCase() + "%"));
        }
        if (filter.getDepoDesription() != null) {
            predicates.add(builder.like(builder.upper(root.get("depoDescription")), "%" + filter.getDepoDesription().toUpperCase() + "%"));
        }
        if (filter.getItemCode() != null) {
            predicates.add(builder.like(builder.upper(root.get("itemCode")), "%" + filter.getItemCode().toUpperCase() + "%"));
        }
        if (filter.getItemDescription() != null) {
            predicates.add(builder.like(builder.upper(root.get("itemDescription")), "%" + filter.getItemDescription().toUpperCase() + "%"));
        }
        if (filter.getDebitCreditFlag()!=null){
            predicates.add(builder.like(builder.upper(root.get("debitCreditFlag")),"%"+filter.getDebitCreditFlag().toUpperCase()+"%"));
        }
        if (filter.getAdjustedQty() != null){
            predicates.add(builder.equal(root.get("adjustedQty"),filter.getAdjustedQty()));
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }

}
