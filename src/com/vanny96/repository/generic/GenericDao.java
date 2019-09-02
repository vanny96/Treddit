package com.vanny96.repository.generic;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class GenericDao<T extends Serializable> extends AbstractRepository<T> implements RepositoryInterface<T>{

}
