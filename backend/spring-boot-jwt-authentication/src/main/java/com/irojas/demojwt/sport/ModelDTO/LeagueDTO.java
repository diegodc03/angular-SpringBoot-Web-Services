package com.irojas.demojwt.sport.ModelDTO;




public class LeagueDTO {

	
	private Long id;
    private String name;
    private String leagueType;
    private boolean requestToJoinLeague; // Nuevo campo
    
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
		return requestToJoinLeague;
	}
	public void setRequireRequest(boolean requireRequest) {
		this.requestToJoinLeague = requireRequest;
	}
	public String getLeagueType() {
		return leagueType;
	}
	public void setLeagueType(String leagueType) {
		this.leagueType = leagueType;
	}
	
	
	public LeagueDTO(Long id, String name, String leagueType, boolean requireRequest) {
		super();
		this.id = id;
		this.name = name;
		this.leagueType = leagueType;
		this.requestToJoinLeague = requireRequest;
	}

	
	public LeagueDTO(String name, String leagueType, boolean requireRequest) {
		super();
		this.name = name;
		this.leagueType = leagueType;
		this.requestToJoinLeague = requireRequest;
	}
	
	
	
	public LeagueDTO(Long id, String name, String leagueType) {
		super();
		this.id = id;
		this.name = name;
		this.leagueType = leagueType;
	}
	
	public LeagueDTO() {
		super();
	}
	
	  
    
}
