package br.com.camnuvem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.camnuvem.model.Camera;

@Repository
public interface CameraRepository extends CrudRepository<Camera, Long>{

}
