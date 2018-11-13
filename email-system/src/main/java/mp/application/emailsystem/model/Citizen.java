package mp.application.emailsystem.model;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "citizens")
public class Citizen extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8119623885018197161L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100)
	private String firstname;

	@NotNull
	@Size(max = 100)
	private String surname;

	@OneToMany(mappedBy = "citizen", targetEntity = EmailAddress.class)
	List<EmailAddress> emailAddresses;

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

	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	@Override
	public String toString() {
		return "Citizen " + id.toString() + " = (Name= " + firstname + ", Surname= " + surname + ")";
	}

}