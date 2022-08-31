package com.devcortes.first_configuration.components.repository;

import com.devcortes.first_configuration.components.enity.Human;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Circle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by cortes on 25.11.18.
 */

@Repository
public interface HumanRepository extends CrudRepository<Human, String> {

	List<Human> findByLastname(String lastname);

	Page<Human> findPersonByLastname(String lastname, Pageable page);

	List<Human> findByNameAndLastname(String name, String lastname);

	List<Human> findByNameOrLastname(String firstname, String lastname);

	List<Human> findByAddressCity(String city);

	List<Human> findByAddressLocationWithin(Circle circle);
}
