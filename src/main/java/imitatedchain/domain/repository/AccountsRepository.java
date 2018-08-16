package imitatedchain.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imitatedchain.domain.model.Accounts;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long>{

}
