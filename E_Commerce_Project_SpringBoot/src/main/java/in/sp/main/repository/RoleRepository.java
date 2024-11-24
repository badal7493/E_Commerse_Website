package in.sp.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
