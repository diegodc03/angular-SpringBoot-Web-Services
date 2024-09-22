package com.irojas.demojwt.workHours.Controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.irojas.demojwt.workHours.Model.WorkingRoles;
import com.irojas.demojwt.workHours.ModelDTO.WorkingRoleDTO;

@Controller
@RequestMapping("/working-role")
public class WorkingRoleController {
	
	
	@GetMapping("/get-roles")
	public ResponseEntity<List<WorkingRoleDTO>> getRoles(){
		
		try {
			return ResponseEntity.ok(
				    Arrays.stream(WorkingRoles.values())
				          .map(role -> new WorkingRoleDTO(role.name(), role.getSalary()))
				          .collect(Collectors.toList())
				);
			
		}catch(Exception e) {
			return null;
		}
		
	}
	
	
	
}
