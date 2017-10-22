package com.example.pcf;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TestService extends JpaRepository<Test, Long> {

}