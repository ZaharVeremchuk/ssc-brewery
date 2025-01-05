package guru.sfg.brewery.domain.security;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String role;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_authority",
            joinColumns= {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name="AUTHORITY_ID", referencedColumnName = "ID")})
    private Set<User> users;
}
