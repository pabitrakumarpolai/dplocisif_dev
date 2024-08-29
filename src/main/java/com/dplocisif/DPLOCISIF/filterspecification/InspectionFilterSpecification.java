package com.dplocisif.DPLOCISIF.filterspecification;

import com.dplocisif.DPLOCISIF.startupdto.InspectionViewDTO;
import com.dplocisif.DPLOCISIF.startupdto.ItemInspectionFilterDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class InspectionFilterSpecification implements Specification<InspectionViewDTO> {

    private ItemInspectionFilterDTO filter;

    public InspectionFilterSpecification(ItemInspectionFilterDTO filter) {
        this.filter = filter;
    }

    @Override
    public Predicate toPredicate(Root<InspectionViewDTO> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        if (filter.getDepoCode() != null) {
            predicates.add(builder.like(builder.upper(root.get("depoCode")), "%" + filter.getDepoCode().toUpperCase() + "%"));
        }

        if (filter.getDepoDescription() != null) {
            predicates.add(builder.like(builder.upper(root.get("depoDescription")), "%" + filter.getDepoDescription().toUpperCase() + "%"));
        }

        if (filter.getInspectionNo() != null) {
            predicates.add(builder.like(builder.upper(root.get("inspectionNo")), "%" + filter.getInspectionNo().toUpperCase() + "%"));
        }

        if (filter.getInspectionDate() != null) {
            predicates.add(builder.equal(root.get("inspectionDate"), filter.getInspectionDate()));
        }

        if (filter.getChalanNo() != null) {
            predicates.add(builder.like(builder.upper(root.get("challanNo")), "%" + filter.getChalanNo().toUpperCase() + "%"));
        }

        if (filter.getItemCode() != null) {
            predicates.add(builder.like(builder.upper(root.get("itemCode")), "%" + filter.getItemCode().toUpperCase() + "%"));
        }

        if (filter.getItemDescription() != null) {
            predicates.add(builder.like(builder.upper(root.get("itemDescription")), "%" + filter.getItemDescription().toUpperCase() + "%"));
        }

        if (filter.getChallanQtyInNumber() != null) {
            predicates.add(builder.equal(root.get("challanQtyInNumber"), filter.getChallanQtyInNumber()));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
