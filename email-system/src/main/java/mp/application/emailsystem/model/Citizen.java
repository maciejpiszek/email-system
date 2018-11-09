package mp.application.emailsystem.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "citizens")
public class Citizen extends AuditModel {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 8119623885018197161L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
    private Long id;

	@Column(name = "firstname", unique = false, nullable = false, length = 100)
    private String firstname;

	@Column(name = "surname", unique = false, nullable = false, length = 100)
    private String surname;

	@OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="citizens_id")
    private Set <EmailAddress> emailaddresses;
	
	public Citizen() {
    }

    public Citizen(String firstname, String surname) {
    	this.firstname = firstname;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public Set<EmailAddress> getEmails() {
		return emailaddresses;   	    	
    }

    public void setEmails(Set<EmailAddress> emailAddresses) {
    	this.emailaddresses = emailAddresses;
    }
    
    @Override
    public String toString() {
        return "Citizen " + id.toString() + " = (Name= " + firstname + ", Surname= " + surname + ")";
    }
	
	
    
}