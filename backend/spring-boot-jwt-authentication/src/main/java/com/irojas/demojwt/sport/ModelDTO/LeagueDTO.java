package com.irojas.demojwt.sport.ModelDTO;




public class LeagueDTO {

	
	private Long id;
    private String name;
	
    
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
	
	
	
	public LeagueDTO(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	
	public LeagueDTO(String name) {
		super();
		this.name = name;
	}
	
	
	public LeagueDTO() {
		super();
	}
	
	
	
    
    
    
    
}
