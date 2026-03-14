package in.kb.main.services;

import in.kb.main.dto.ProfileDto;
import in.kb.main.entitys.ProfileDocument;
import in.kb.main.reposertory.ProfileReposertory;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProfileServices {


    private final ProfileReposertory profileReposertory;


    public ProfileDto createProfile(ProfileDto profileDto) {
        if(profileReposertory.existsByClerkId(profileDto.getClerkId())){
            updatePrifie(profileDto);

        }

      ProfileDocument profile = ProfileDocument.builder()
               .clerkId(profileDto.getClerkId())
               .email(profileDto.getEmail())
               .firstName(profileDto.getFirstName())
               .lastName(profileDto.getLastName())
               .photoUrl(profileDto.getPhotoUrl())
               .credits(5)
               .createdAt(Instant.now())
               .build();
      try{
          profile = profileReposertory.save(profile);
      } catch (Exception e) {
          throw new RuntimeException(e.getMessage());
      }




        return ProfileDto.builder()
                .id(profile.getId())
                .clerkId(profile.getClerkId())
                .email(profile.getEmail())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .photoUrl(profile.getPhotoUrl())
                .credits(profile.getCredits())
                .createdAt(profile.getCreatedAt())
                .build();


    }

    public ProfileDto updatePrifie(ProfileDto profileDto){
        ProfileDocument existingProfile = profileReposertory.findByClerkId(profileDto.getClerkId());
        if (existingProfile != null){
            if(profileDto.getEmail() != null && !profileDto.getEmail().isEmpty()){
                existingProfile.setEmail(profileDto.getEmail());
            }
            if(profileDto.getFirstName() != null && !profileDto.getFirstName().isEmpty() ){
                existingProfile.setFirstName(profileDto.getFirstName());
            }
            if(profileDto.getLastName() != null && !profileDto.getLastName().isEmpty() ){
                existingProfile.setLastName(profileDto.getLastName());
            }
            if(profileDto.getPhotoUrl() != null && !profileDto.getPhotoUrl().isEmpty() ){
                existingProfile.setPhotoUrl(profileDto.getPhotoUrl());
            }

             existingProfile = profileReposertory.save(existingProfile);

            return ProfileDto.builder()
                    .id(existingProfile.getId())
                    .email(existingProfile.getEmail())
                    .clerkId(existingProfile.getClerkId())
                    .firstName(existingProfile.getFirstName())
                    .lastName(existingProfile.getLastName())
                    .credits(existingProfile.getCredits())
                    .photoUrl(existingProfile.getPhotoUrl())
                    .build();

        }

        return  null;



    }

    public boolean existsByClerkId(String clerkId){
        return profileReposertory.existsByClerkId(clerkId);
    }

    public void deleteProfile(String clerId){
       ProfileDocument existsProfile = profileReposertory.findByClerkId(clerId);
       if (existsProfile != null){
           profileReposertory.delete(existsProfile);
       }

    }


}
