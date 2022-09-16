package equipe4.atlanticshippingmasters.model;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    private String username;

    private String password;

    @Transient
    private String passwordConfirm;

    @Enumerated(EnumType.STRING)
    private Role role;
    
    @Override
    public String toString() {
        String result = String.format(
                "Category[id=%d, name='%s']%n",
                idUser, username);
        
        return result;
    }



	
    
}