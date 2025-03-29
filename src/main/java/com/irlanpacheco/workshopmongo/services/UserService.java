package com.irlanpacheco.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irlanpacheco.workshopmongo.domain.User;
import com.irlanpacheco.workshopmongo.dto.UserDto;
import com.irlanpacheco.workshopmongo.repository.UserRepository;
import com.irlanpacheco.workshopmongo.services.exception.ObjectNotFoundException;

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
	
	public User update(User entity) {
		User newObj = repo.findById(entity.getId()).get();
		updateData(newObj, entity);
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User entity) {
		newObj.setName(entity.getName());
		newObj.setEmail(entity.getEmail());
	}

	public User fromDTO(UserDto objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
