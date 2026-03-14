package in.kb.main.controller;

import in.kb.main.dto.ProfileDto;
import in.kb.main.entitys.ProfileDocument;
import in.kb.main.services.ProfileServices;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProfileController {


    private final ProfileServices profileServices;


    @PostMapping("/register")
    public ResponseEntity<?> registerProfile (@RequestBody ProfileDto profileDto){
        HttpStatus status = profileServices.existsByClerkId(profileDto.getClerkId())? HttpStatus.OK : HttpStatus.CREATED;

        ProfileDto profile =  profileServices.createProfile(profileDto);
        return ResponseEntity.status(status).body(profile);

    }




}
