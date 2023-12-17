package com.example.CustomerSystem.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.example.CustomerSystem.entity.MstCustomer;
import com.example.CustomerSystem.form.SearchRequest;

@Component
public class CustomerSpecifications {

    public static Specification<MstCustomer> searchCustomers(SearchRequest searchRequest) {
        String name = searchRequest.getName();
        String sex = searchRequest.getSex();
        String birthdayFrom = searchRequest.getBirthdayFrom();
        String birthdayTo = searchRequest.getBirthdayTo();
    	
    	return new Specification<MstCustomer>() {

			private static final long serialVersionUID = 1L;

			@Override
            public Predicate toPredicate(Root<MstCustomer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);

                // Base condition: DELETE_YMD IS NULL
                Predicate predicate = criteriaBuilder.isNull(root.get("deleteYmd"));

                // Additional conditions based on parameters
                if (name != null && !name.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("customerName"), "%" + name + "%"));
                }

                if (sex != null && !sex.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("sex"), sex));
                }

                if (birthdayFrom != null && !birthdayFrom.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.greaterThanOrEqualTo(root.get("birthDay"), birthdayFrom));
                }

                if (birthdayTo != null && !birthdayTo.isEmpty()) {
                    predicate = criteriaBuilder.and(predicate, criteriaBuilder.lessThanOrEqualTo(root.get("birthDay"), birthdayTo));
                }

                return predicate;
            }
        };
    }
}
