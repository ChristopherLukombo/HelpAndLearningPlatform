package fr.esgi.service.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.Locale;
import java.util.Objects;


/**
 * A user.
 */
public class UserDTO {
    private Long id;

    private String login;

    private String firstName;

    private String lastName;

    private String email;

    private String countryOfResidence;

    private boolean activated = false;

    private String langKey;

    private String imageUrl;

    private Long authorityId;


    public UserDTO() {
        // Empty constructor for Jackson.
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
}
