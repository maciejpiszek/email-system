package mp.application.emailsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
//import javax.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "emailaddresses")
public class EmailAddress extends AuditModel {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3340594805791584382L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;

	@Column(name = "emailAddress", unique = true, nullable = false, length = 254)
    private String emailAddress;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citizens_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Citizen citizen;

    public EmailAddress() {
    }

    public EmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    public Citizen getCitizen() {
    	return citizen;
    }
    
    public void setCitizen(Citizen citizen) {
    	this.citizen = citizen;
    }

    @Override
    public String toString() {
        return "Email " + id.toString()  + " = " + emailAddress;
    }
}