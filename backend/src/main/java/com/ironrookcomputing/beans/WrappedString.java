package com.ironrookcomputing.beans;

public class WrappedString {
	
	public WrappedString() {
		super();
	}
	
	public WrappedString(String string) {
		super();
		
		setString(string);
	}
	
	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return "WrappedString [string=" + string + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((string == null) ? 0 : string.hashCode());
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
		WrappedString other = (WrappedString) obj;
		if (string == null) {
			if (other.string != null)
				return false;
		} else if (!string.equals(other.string))
			return false;
		return true;
	}

	private String string = "";
}
