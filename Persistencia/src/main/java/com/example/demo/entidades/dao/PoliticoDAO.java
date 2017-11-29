package com.example.demo.entidades.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Politico;

public interface PoliticoDAO extends JpaRepository<Politico, Long> {

}
