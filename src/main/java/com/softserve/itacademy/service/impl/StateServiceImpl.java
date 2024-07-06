package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.StateService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    private StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State create(State state) {
            return stateRepository.save(state);
    }

    @Override
    public State readById(long id) {
        Optional<State> optional = stateRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("State with id " + id + " not found");
    }

    @Override
    public State update(State state) {
            State oldState = readById(state.getId());
                return stateRepository.save(state);
    }

    @Override
    public void delete(long id) {
        State state = readById(id);
        if (state != null) {
            stateRepository.delete(state);
        } else {
            throw new EntityNotFoundException("State with id " + id + " not found");
        }
    }

    @Override
    public State getByName(String name) {
        Optional<State> optional = Optional.ofNullable(stateRepository.getByName(name));
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new EntityNotFoundException("State with name '" + name + "' not found");
    }

    @Override
    public List<State> getAll() {
        List<State> states = stateRepository.getAll();
        return states.isEmpty() ? new ArrayList<>() : states;
    }
}
