package br.com.logos.resources.service;

import br.com.logos.exceptions.DiscilpineNameNullException;
import br.com.logos.models.Discipline;
import br.com.logos.repositories.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;

    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }

    public Discipline getOneDisciplineById(int id) throws Exception {

        Optional<Discipline> validation = disciplineRepository.findById(id);

        if (validation.isEmpty() || validation.equals(null)) {
            throw new Exception("This id is invalid. Please, type another id.");

        } else {

            return validation.get();
        }

    }

    public Iterable<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }


    public Discipline updateDiscipline(int id, Discipline disciplineToBeUpdated) {
        Discipline disciplineSet = disciplineRepository.findById(id).get();
        disciplineSet.setName(disciplineToBeUpdated.getName());
        return disciplineRepository.save(disciplineSet);
    }

    public Discipline newDiscipline(Discipline discipline) {
        validateDiscipline(discipline);
        return disciplineRepository.save(discipline);
    }

    public boolean validateDiscipline(Discipline disciplineValidated) {

        if (disciplineValidated.getName().isEmpty()) {
            throw new DiscilpineNameNullException("Discipline name cannot be empty. Please, type a valid name");
        }


        return false;

    }
}
