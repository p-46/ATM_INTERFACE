package com.demo.atm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.atm.entity.ATMUser;

public interface ATMUserRepository extends JpaRepository<ATMUser, String> {
}
