package ar.unnoba.poo2020.project.service;

import java.util.List;

import ar.unnoba.poo2020.project.model.User;


public interface IUserService {

	public List < User > getUsers();
    
    public User create(User user);
    
    public User find(Long id);
	
}
