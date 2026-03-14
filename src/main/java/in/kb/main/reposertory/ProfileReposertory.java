package in.kb.main.reposertory;

import in.kb.main.entitys.ProfileDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileReposertory extends JpaRepository<ProfileDocument, Integer> {

  Optional<ProfileDocument>findByEmail(String email);

  ProfileDocument findByClerkId(String clerkId);

  boolean existsByClerkId(String clerId);

}
