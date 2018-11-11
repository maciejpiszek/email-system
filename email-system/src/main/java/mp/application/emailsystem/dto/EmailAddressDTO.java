package mp.application.emailsystem.dto;

import mp.application.emailsystem.model.Citizen;

public class EmailAddressDTO {

	
	private Long id;

    private String emailAddress;

    private Citizen citizen;
    
    public EmailAddressDTO() {
    	  	
    }
    
    public EmailAddressDTO(Long id, String emailAddress, Citizen citizen) {
	  	this.id = id;
	  	this.emailAddress = emailAddress;
	  	this.citizen = citizen;
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
}
