package io.github.Psyllo.reviewLibraryapi.Repository;

import io.github.Psyllo.reviewLibraryapi.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AutorRepository extends JpaRepository<Autor, UUID> {

}
