package fr.esgi.service.dto;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Locale;
import java.util.Objects;


/**
 * A DTO for the User entity.
 */
public class UserDTO {
	
    private Long id;

    @NotBlank
    @Pattern(regexp =  "^[_'.@A-Za-z0-9-]*$")
    @Size(min = 1, max = 50)
    private String login;

    @Size(max = 50)
    private String firstName;

    @Size(max = 50)
    private String lastName;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private String countryOfResidence;

    private boolean activated = false;

    private String langKey;

    private String imageUrl;

    private Long authorityId;


    public UserDTO() {
        // Empty constructor needed for Jackson.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    // Lowercase the login before saving it in database
    public void setLogin(String login) {
        this.login = StringUtils.lowerCase(login, Locale.ENGLISH);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean getActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public boolean isActivated() {
        return activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return activated == userDTO.activated &&
                Objects.equals(id, userDTO.id) &&
                Objects.equals(login, userDTO.login) &&
                Objects.equals(firstName, userDTO.firstName) &&
                Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(email, userDTO.email) &&
                Objects.equals(countryOfResidence, userDTO.countryOfResidence) &&
                Objects.equals(langKey, userDTO.langKey) &&
                Objects.equals(imageUrl, userDTO.imageUrl) &&
                Objects.equals(authorityId, userDTO.authorityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, firstName, lastName, email, countryOfResidence, activated, langKey, imageUrl, authorityId);
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserDTO [");
		if (id != null) {
			builder.append("id=");
			builder.append(id);
			builder.append(", ");
		}
		if (login != null) {
			builder.append("login=");
			builder.append(login);
			builder.append(", ");
		}
		if (firstName != null) {
			builder.append("firstName=");
			builder.append(firstName);
			builder.append(", ");
		}
		if (lastName != null) {
			builder.append("lastName=");
			builder.append(lastName);
			builder.append(", ");
		}
		if (email != null) {
			builder.append("email=");
			builder.append(email);
			builder.append(", ");
		}
		if (countryOfResidence != null) {
			builder.append("countryOfResidence=");
			builder.append(countryOfResidence);
			builder.append(", ");
		}
		builder.append("activated=");
		builder.append(activated);
		builder.append(", ");
		if (langKey != null) {
			builder.append("langKey=");
			builder.append(langKey);
			builder.append(", ");
		}
		if (imageUrl != null) {
			builder.append("imageUrl=");
			builder.append(imageUrl);
			builder.append(", ");
		}
		if (authorityId != null) {
			builder.append("authorityId=");
			builder.append(authorityId);
		}
		builder.append("]");
		return builder.toString();
	}
}
