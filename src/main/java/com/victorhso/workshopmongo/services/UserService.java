package com.victorhso.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import com.victorhso.workshopmongo.domain.User;
import com.victorhso.workshopmongo.dto.UserDTO;
import com.victorhso.workshopmongo.repository.UserRepository;
import com.victorhso.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User obj) {
		User newobj = findById(obj.getId());
		updateData(newobj, obj);
		return repo.save(newobj);
	}
	
	private void updateData(User newobj, User obj) {
		newobj.setName(obj.getName());
		newobj.setEmail(obj.getEmail());
	}

	public User fromDto(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
