package com.theoreoluwa.property.propertymanagement.repository;

import com.theoreoluwa.property.propertymanagement.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long>{

}
