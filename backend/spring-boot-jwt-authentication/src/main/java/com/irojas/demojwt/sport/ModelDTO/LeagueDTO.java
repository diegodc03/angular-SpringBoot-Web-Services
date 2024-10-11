package com.irojas.demojwt.sport.ModelDTO;




public class LeagueDTO {

	
	private Long id;
    private String name;
    private boolean requireRequest; // Nuevo campo
    
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isRequireRequest() {
		return requireRequest;
	}
	public void setRequireRequest(boolean requireRequest) {
		this.requireRequest = requireRequest;
	}
	
	
	public LeagueDTO(Long id, String name, boolean requireRequest) {
		super();
		this.id = id;
		this.name = name;
		this.requireRequest = requireRequest;
	}

	
	public LeagueDTO(String name, boolean requireRequest) {
		super();
		this.name = name;
		this.requireRequest = requireRequest;
	}
	
	
	
	public LeagueDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public LeagueDTO() {
		super();
	}
	
	  
    
}
