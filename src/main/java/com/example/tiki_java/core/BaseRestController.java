package com.example.tiki_java.core;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Generic Parameters
public interface BaseRestController<T, C, U> {

    /**
     * Retrieves a list of all entities in DTO form.
     *
     * @return A response containing a list of DTO objects (T) representing the entities.
     */
    @GetMapping
    ApiResponse<List<T>> getAll();

    /**
     * Retrieves a single entity based on its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return A response containing the DTO object (T) for the entity if found, or an appropriate error response if not found.
     */
    @GetMapping("/{id}")
    ApiResponse<T> getById(@PathVariable String id);

    /**
     * Creates a new entity based on the provided data.
     *
     * @param createRequest The data required to create the new entity.
     * @return A response containing the DTO object (T) representing the newly created entity.
     */
    @PostMapping
    ApiResponse<T> create(@RequestBody @Valid C createRequest);

    /**
     * Updates an existing entity with the provided data.
     *
     * @param id            The ID of the entity to update.
     * @param updateRequest The data to update the entity with.
     * @return A response containing the DTO object (T) representing the updated entity.
     */
    @PutMapping("/{id}")
    ApiResponse<T> update(@PathVariable String id, @RequestBody @Valid U updateRequest);


    /**
     * Deletes the entity corresponding to the provided ID.
     *
     * @param id The ID of the entity to delete.
     * @return A response indicating the result of the deletion operation.
     */
    @DeleteMapping("/{id}")
    ApiResponse<Void> delete(@PathVariable String id);


}
