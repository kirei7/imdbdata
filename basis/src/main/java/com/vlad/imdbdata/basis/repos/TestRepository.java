package com.vlad.imdbdata.basis.repos;

import com.vlad.imdbdata.basis.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by vlad on 10.03.17.
 */
public interface TestRepository extends JpaRepository<TestEntity, String> {
}
