package com.ironrookcomputing.hibernate.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Suffix getSuffix() {
		return suffix;
	}

	public void setSuffix(Suffix suffix) {
		this.suffix = suffix;
	}

	public ProfileType getProfileType() {
		return profileType;
	}

	public void setProfileType(ProfileType profileType) {
		this.profileType = profileType;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", title=" + title
				+ ", suffix=" + suffix + ", profileType=" + profileType + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((profileType == null) ? 0 : profileType.hashCode());
		result = prime * result + ((suffix == null) ? 0 : suffix.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (profileType == null) {
			if (other.profileType != null)
				return false;
		} else if (!profileType.equals(other.profileType))
			return false;
		if (suffix == null) {
			if (other.suffix != null)
				return false;
		} else if (!suffix.equals(other.suffix))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Id
	@Column(name="id", unique = true)
	private int id = 0;
	
	@Column(name="firstname")
	private String firstName = null;
	
	@Column(name="lastname")
	private String lastName = null;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="TITLE_id", referencedColumnName="id")
	private Title title;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="SUFFIX_id", referencedColumnName="id")
	private Suffix suffix;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="PROFILE_TYPE_id", referencedColumnName="id")
	private ProfileType profileType;
}
