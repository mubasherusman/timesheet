package com.mubasher.timesheet.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(readOnly=false,isolation = Isolation.READ_UNCOMMITTED)
public interface GenericSimpleJpaRepository <T, ID extends Serializable> extends JpaRepository<T, ID>
{
    public T handleCustom(String id);
}
