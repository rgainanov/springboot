package ru.geekbrains.springboot.springboot.repositories;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.springboot.springboot.models.Role;

@Repository
@Profile("dao")
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
