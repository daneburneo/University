package br.com.logos.repositories;

import br.com.logos.models.Discipline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplineRepository extends CrudRepository<Discipline, Integer> {
}





