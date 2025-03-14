package com.example.tiki_java.core;

import java.util.List;

public interface BaseService<E, T, C, U> {
    /**
     * Finds and returns the original entity by its ID.
     *
     * @param id The ID of the entity to find.
     * @return The original entity (E) if found, or null if not found.
     */
    E getEntityById(String id);

    /**
     * Returns a list of all entities in DTO form.
     *
     * @return A list of DTO objects (T) representing the entities.
     */
    List<T> getAll();

    /**
     * Creates a new entity based on the provided create request.
     *
     * @param createRequest The data required to create a new entity.
     * @return A DTO object (T) representing the newly created entity.
     */

    T create(C createRequest);

    /**
     * Retrieves and returns a DTO object based on the provided ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return A DTO object (T) if found, or null if not found.
     */
    T getById(String id);

    /**
     * Updates an existing entity by its ID using the provided update request data.
     *
     * @param id            The ID of the entity to update.
     * @param updateRequest The data to update the entity with.
     * @return A DTO object (T) representing the updated entity.
     */
    T update(String id, U updateRequest);

    /**
     * Deletes the entity corresponding to the provided ID.
     *
     * @param id The ID of the entity to delete.
     */
    void delete(String id);
}
