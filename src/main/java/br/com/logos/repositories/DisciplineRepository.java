package br.com.logos.repositories;

import br.com.logos.models.Discipline;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineRepository extends CrudRepository<Discipline, Integer> {
}





