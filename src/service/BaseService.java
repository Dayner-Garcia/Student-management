package service;

import domain.OperationResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class BaseService<T> {
    protected final List<T> dataStore = new ArrayList<>();

    public OperationResult<T> create(T entity) {
        dataStore.add(entity);
        return OperationResult.success("Elemento agregado correctamente.", entity);
    }

    public OperationResult<List<T>> findAll() {
        return OperationResult.success("Listado recuperado.", new ArrayList<>(dataStore));
    }

    public OperationResult<T> findOne(Predicate<T> predicate) {
        Optional<T> result = dataStore.stream().filter(predicate).findFirst();
        return result.map(r -> OperationResult.success("Elemento encontrado.", r)).orElseGet(() -> OperationResult.failure("No encontrado."));
    }

    public OperationResult<T> delete(Predicate<T> predicate) {
        Optional<T> result = dataStore.stream().filter(predicate).findFirst();
        if (result.isPresent()) {
            dataStore.remove(result.get());
            return OperationResult.success("Elemento eliminado.", result.get());
        } else {
            return OperationResult.failure("No se pudo eliminar. No encontrado.");
        }
    }

    public OperationResult<T> update(Predicate<T> predicate, T newData) {
        for (int i = 0; i < dataStore.size(); i++) {
            if (predicate.test(dataStore.get(i))) {
                dataStore.set(i, newData);
                return OperationResult.success("Elemento actualizado.", newData);
            }
        }
        return OperationResult.failure("No se pudo actualizar. No encontrado.");
    }

    public boolean exists(Predicate<T> predicate) {
        return dataStore.stream().anyMatch(predicate);
    }
}