package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employ;
import com.example.demo.repo.EmployRepository;

@Service
public class EmployService {

	@Autowired
	private EmployRepository employRepository;
	
	public Page<Employ> findAll(int pageNo, int pageSize, String sortBy, String sortDirection) {
		Sort sort = Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
		Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
		return employRepository.findAll(pageable);
	}

	
	public List<Employ> showEmploy() {
		return employRepository.findAll();
	}
	
	public Employ searchEmploy(int empno) {
		return employRepository.findById(empno).get();
	}
	
	public void addEmploy(Employ employ) {
		employRepository.save(employ);
	}
	
	public void updateEmploy(Employ employ) {
		employRepository.save(employ);
	}
	
	public void deleteEmploy(int empno) {
		employRepository.deleteById(empno);
	}
}

