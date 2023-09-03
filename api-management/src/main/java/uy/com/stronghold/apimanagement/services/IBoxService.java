package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Box;

@Service
public interface IBoxService {

	Box getBox(int id) throws ValidationException;;

	List<Box> getBoxes(String name, int idBuilding) throws ValidationException;;

	void saveBox(Box box) throws ValidationException;

	void deleteBox(Box box) throws ValidationException;

	void updateBox(Box box) throws ValidationException;

}
