package in.kb.main.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table
public class ProfileDocument {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(unique = true)
    private String clerkId;

    @Column(unique = true)
    private String email;
    private String firstName;
    private String lastName;
    private Integer credits;
    private String photoUrl;

    @CreatedDate
    private Instant createdAt;


}
