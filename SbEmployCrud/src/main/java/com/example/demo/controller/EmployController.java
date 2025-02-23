package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employ;
import com.example.demo.service.EmployService;

@RestController
@RequestMapping(value="/employ")
@CrossOrigin(origins = "http://localhost:3000")
public class EmployController {

	@Autowired
	private EmployService employService;
	

	@GetMapping(value="/show/{pageNo}/{pageSize}/{sortBy}/{sortDirection}")
	public List<Employ> findAll(@PathVariable int pageNo, 
				@PathVariable int pageSize, @PathVariable String sortBy, 
				@PathVariable String sortDirection
				) {
		Page<Employ> result = employService.findAll(pageNo, pageSize, sortBy, sortDirection);
		return result.getContent();
	}

	
	@GetMapping(value="/showEmploy")
	public List<Employ> showEmploy() {
		return employService.showEmploy();
	}
	
	@GetMapping(value="/searchEmploy/{id}")
	public ResponseEntity<Employ> searchEmploy(@PathVariable int id) {
		try {
			Employ employ = employService.searchEmploy(id);
			return new ResponseEntity<Employ>(employ,HttpStatus.OK);
			} catch(NoSuchElementException e) {
				return new ResponseEntity<Employ>(HttpStatus.NOT_FOUND);
			}
	}
	
	@PostMapping(value="/addEmploy")
	public void addEmploy(@RequestBody Employ employ) {
		employService.addEmploy(employ);
	}
	
	@DeleteMapping(value="/deleteEmploy/{id}")
	public void deleteEmploy(@PathVariable int id) {
		employService.deleteEmploy(id);
	}
	
	@PutMapping(value="/updateEmploy")
	public void updateEmploy(@RequestBody Employ employ) {
		employService.updateEmploy(employ);
	}
}
