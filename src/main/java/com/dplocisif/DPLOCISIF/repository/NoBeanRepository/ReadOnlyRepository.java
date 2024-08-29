package com.dplocisif.DPLOCISIF.repository.NoBeanRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID> extends JpaRepository<T, ID> {
    List<T> findAll();
    @Override
    Optional<T> findById(ID id);
}
