package uy.com.stronghold.apimanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Box;
import uy.com.stronghold.apimanagement.repositories.IBoxRepository;

@Service
public class BoxService implements IBoxService{

	@Autowired
	private IBoxRepository repository;
	
	@Override
	public Box getBox(int id) throws ValidationException {
		Box box = repository.getBox(id);
		if(box == null) throw new ValidationException(Errores.BOX_NOT_FOUND);
		return box;
	}

	@Override
	public List<Box> getBoxes(String name, int idBuilding) throws ValidationException {
		List<Box> boxes = repository.getBoxes(name, idBuilding);
		if(boxes == null) throw new ValidationException(Errores.BOX_NOT_FOUND);
		return boxes;
	}

	@Override
	public void saveBox(Box box) throws ValidationException {
		if(repository.save(box) == null) 
			throw new ValidationException(Errores.ERROR_SAVE_BOX);
	}
	
	@Override
	public void updateBox(Box box) throws ValidationException {
		if(repository.save(box) == null) 
			throw new ValidationException(Errores.ERROR_UPDATE_BOX);
	}

	@Override
	public void deleteBox(Box box) {
		repository.delete(box);
	}

	
}
