package br.com.camnuvem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.camnuvem.model.Camera;
import br.com.camnuvem.model.Error;
import br.com.camnuvem.model.Produto;
import br.com.camnuvem.model.Usuario;
import br.com.camnuvem.repository.CameraRepository;
import br.com.camnuvem.repository.UsuarioRepository;

@Controller("CameraController")
@RequestMapping(value = "/camera")
// Camera CRUD
public class CameraController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CameraRepository cameraRepository;


    // Get all cameras
    @GetMapping(value="/", produces="application/json")
    public ResponseEntity getAll(){

        try{
            List<Camera> cameras = (List<Camera>)cameraRepository.findAll();
            return new ResponseEntity<>(cameras, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(new Error("Fail at find all cameras"), HttpStatus.INSUFFICIENT_STORAGE);
        }
    }

     // Get camera by id
     @GetMapping(value="/{id}", produces="application/json")
     public ResponseEntity<Camera> getAll(@PathVariable("id") Long id) {
 
         try{
             Optional<Camera> camera_get = cameraRepository.findById(id);
             return new ResponseEntity<Camera>(camera_get.get(),HttpStatus.OK);
         }catch(Exception e){
             return new ResponseEntity(new Error("Fail at find all cameras"), HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }     

    // Retornando objetos
    @PostMapping(value="/insert", produces="application/json")
    public ResponseEntity insert(@RequestBody Camera camera){

		for (int pos = 0; pos < camera.getUsuarios().size(); pos ++) {
            List<Camera> camera_aux = camera.getUsuarios().get(pos).getCameras();
            camera_aux.add(camera);
            camera.getUsuarios().get(pos).setCameras(camera_aux);
		}

        try{
            Camera newCamera = (Camera) cameraRepository.save(camera);
            return new ResponseEntity<Camera>(newCamera, HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Error at insert new camera: "+e);
            return new ResponseEntity<>(new Error("Some error occurred when inserting the new camera. "), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value="/update", produces="application/json")
    public ResponseEntity update(@RequestBody Camera camera){

		for (int pos = 0; pos < camera.getUsuarios().size(); pos ++) {
            List<Camera> camera_aux = camera.getUsuarios().get(pos).getCameras();
            camera_aux.add(camera);
            camera.getUsuarios().get(pos).setCameras(camera_aux);
		}

        try{
            Camera newCamera = (Camera) cameraRepository.save(camera);
            return new ResponseEntity<Camera>(newCamera, HttpStatus.OK);
        }catch(Exception e){
            System.out.println("Error at insert new camera: "+e);
            return new ResponseEntity<>(new Error("Some error occurred when inserting the new camera. "), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value="/{id}", produces = "application/json")
    public ResponseEntity apagar(@PathVariable("id") Long id){
        
        try{
            cameraRepository.deleteById(id);
            return new ResponseEntity<>("", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(new Error("Error at deleting a camera: "+e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}
