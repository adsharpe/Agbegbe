package com.ironrookcomputing.dao;

import java.util.Set;

import com.ironrookcomputing.hibernate.beans.ProfileType;

public interface ProfileTypeDAO {
	public ProfileType addProfileType(ProfileType profileType);
	public ProfileType getProfileType(ProfileType profileType);
	public Set<ProfileType> getProfileTypes();
	public ProfileType updateProfileType(ProfileType profileType);
	public void deleteProfileType(ProfileType profileType);
}
