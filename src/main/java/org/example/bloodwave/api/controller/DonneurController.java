package org.example.bloodwave.api.controller;


import lombok.AllArgsConstructor;
import org.example.bloodwave.application.dto.response.DonneurDtoResponse;
import org.example.bloodwave.application.service.DonneurService;
import org.example.bloodwave.domain.enumeration.GroupeSanguin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donneur")
@AllArgsConstructor
public class DonneurController {

    public final DonneurService donneurService;


    @PutMapping("/{id}")
    public ResponseEntity<DonneurDtoResponse> updateGroupeSanguinById(@PathVariable("id") Long id, @RequestBody GroupeSanguin groupeSanguin)
    {
       DonneurDtoResponse donneurDtoResponse =  this.donneurService.updateGroupSanguinById(id,groupeSanguin);

       return ResponseEntity.ok().body(donneurDtoResponse);
    }




}
