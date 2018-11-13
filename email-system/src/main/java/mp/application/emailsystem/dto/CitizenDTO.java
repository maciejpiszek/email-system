package mp.application.emailsystem.dto;

import java.util.List;
import mp.application.emailsystem.model.AuditModel;
import mp.application.emailsystem.model.EmailAddress;

public class CitizenDTO extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1265621297514171111L;

	private Long id;

	private String firstname;

	private String surname;

	List<EmailAddress> emailAddresses;

	public CitizenDTO() {

	}

	public CitizenDTO(Long id, String firstname, String surname) {

		this.id = id;
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

}
