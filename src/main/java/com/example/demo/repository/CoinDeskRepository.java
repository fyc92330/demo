package com.example.demo.repository;

import com.example.demo.entity.CoinDesk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinDeskRepository extends JpaRepository<CoinDesk, String> {
}
