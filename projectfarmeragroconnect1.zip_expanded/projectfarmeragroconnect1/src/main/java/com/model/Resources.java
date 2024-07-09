package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Resources {
	
	@Id
	private int rescourceId;
	private String resourceName;
	
	public Resources(){
		super();
	}

	public Resources(int rescourceId, String resourceName) {
		super();
		this.rescourceId = rescourceId;
		this.resourceName = resourceName;
	}

	public int getRescourceId() {
		return rescourceId;
	}

	public void setRescourceId(int rescourceId) {
		this.rescourceId = rescourceId;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
}
