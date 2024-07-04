package uy.com.stronghold.apimanagement.resources;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uy.com.stronghold.apimanagement.enums.UnitType;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.Box;
import uy.com.stronghold.apimanagement.models.BoxSettlementMonth;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.models.ItemMenu;
import uy.com.stronghold.apimanagement.models.Person;
import uy.com.stronghold.apimanagement.models.SettlementMonth;
import uy.com.stronghold.apimanagement.models.Supplier;
import uy.com.stronghold.apimanagement.models.SupplierTransaction;
import uy.com.stronghold.apimanagement.models.Transaction;
import uy.com.stronghold.apimanagement.models.Unit;
import uy.com.stronghold.apimanagement.models.UnitTransaction;
import uy.com.stronghold.apimanagement.repositories.QueriesRepository;
import uy.com.stronghold.apimanagement.services.IBoxService;
import uy.com.stronghold.apimanagement.services.IBuildingService;
import uy.com.stronghold.apimanagement.services.IItemMenuService;
import uy.com.stronghold.apimanagement.services.IPersonService;
import uy.com.stronghold.apimanagement.services.ISettlementMonthService;
import uy.com.stronghold.apimanagement.services.ISupplierService;
import uy.com.stronghold.apimanagement.services.ITransactionService;
import uy.com.stronghold.apimanagement.services.IUnitService;

@Service
public class ApiManagementImp implements IApiManagementImp {
	
	@Autowired
	private QueriesRepository repository;
	@Autowired
	private IBuildingService buildingService;
	@Autowired
	private IUnitService unitService;
	@Autowired
	private ITransactionService transactionService;
	@Autowired
	private IBoxService boxService;
	@Autowired
	private ISupplierService supplierService;
	@Autowired
	private IPersonService personService;
	@Autowired
	private ISettlementMonthService settlementMonthService;
	@Autowired
	private IItemMenuService itemMenuService;
	
	
	public List<ItemMenu> getItemsMenu() throws ValidationException {
		return itemMenuService.getItemsMenu();
	}

	public Building getBuilding(int id) throws ValidationException {
		return buildingService.getBuilding(id);
	}
	
	public List<Building> getBuildings(String name) throws ValidationException {
		return buildingService.getBuildings(name);
	}
	
	public List<Building> getInitialBuildings() {
		return repository.getBuildings();
	}

	public void saveBuilding(Building building) throws ValidationException {
		buildingService.saveBuilding(building);
	}
	
	public void updateBuilding(Building building) throws ValidationException {
		buildingService.updateBuilding(building);
	}

	public void deleteBuilding(Building building) throws ValidationException {
		buildingService.deleteBuilding(building);
	}

	
	public Unit getUnit(int id) throws ValidationException {
		return unitService.getUnit(id);
	}

	public List<Unit> getUnits(int idBuilding, UnitType unitType,String number) throws ValidationException {
		return unitService.getUnits(idBuilding, unitType, number);
	}

	public void saveUnit(Unit unit) throws ValidationException {
		unitService.saveUnit(unit);
	}
	
	public void deleteUnit(Unit unit) {
		unitService.deleteUnit(unit);
	}

	public Optional<Transaction> getTransaction(int id) throws ValidationException {
		return transactionService.getTransaction(id);
	}

	public List<Transaction> getTransactions(int idBuilding, Date dateFrom, Date dateTo,
			int settlementMonth, String type) throws ValidationException {
		return transactionService.getTransactions(idBuilding, dateFrom, dateTo,
				settlementMonth, type);
	}

	public void SaveUnitTransaction(UnitTransaction transaction) throws ValidationException {
		transactionService.saveUnitTransaction(transaction);
	}

	public void saveSupplierTransaction(SupplierTransaction transaction) throws ValidationException {
		transactionService.saveSupplierTransaction(transaction);
	}

	public void updateUnitTransaction(UnitTransaction transaction) throws ValidationException {
		transactionService.updateUnitTransaction(transaction);
	}

	public void updateSupplierTransaction(SupplierTransaction transaction) throws ValidationException {
		transactionService.updateSupplierTransaction(transaction);
	}

	public void deleteById(int id) {
		transactionService.deleteById(id);
	}

	public Box getBox(int id) throws ValidationException {
		return boxService.getBox(id);
	}

	public List<Box> getBoxes(String name, int idBuilding) throws ValidationException {
		return boxService.getBoxes(name, idBuilding);
	}

	public void saveBox(Box box) throws ValidationException {
		boxService.saveBox(box);
	}

	public void deleteBox(Box box) throws ValidationException {
		boxService.deleteBox(box);
	}

	public Supplier getSupplier(int id) throws ValidationException {
		return supplierService.getSupplier(id);
	}

	public List<Supplier> getSuppliers(String name) throws ValidationException {
		return supplierService.getSuppliers(name);
	}

	public void saveSupplier(Supplier supplier) throws ValidationException {
		supplierService.saveSupplier(supplier);
	}

	public void updateSupplier(Supplier supplier) throws ValidationException {
		supplierService.updateSupplier(supplier);
	}
	public void deleteSupplier(Supplier supplier) throws ValidationException {
		supplierService.deleteSupplier(supplier);
	}

	public Person getPerson(int id) throws ValidationException {
		return personService.getPerson(id);
	}

	public List<Person> getPersons(String name) throws ValidationException {
		return personService.getPersons(name);
	}

	public void savePerson(Person person) throws ValidationException {
		personService.savePerson(person);
	}

	public void updatePerson(Person person) throws ValidationException {
		personService.updatePerson(person);
	}

	public void deletePerson(Person person) throws ValidationException {
		personService.deletePerson(person);
	}

	public SettlementMonth getSettlementMonth(int id) throws ValidationException {
		return settlementMonthService.getSettlementMonth(id);
	}

	public List<SettlementMonth> getSettlementMonths(int year, int month, int idBuilding) throws ValidationException {
		return settlementMonthService.getSettlementMonths(year, month, idBuilding);
	}

	public void saveSettlementMonths(SettlementMonth settlementMonth) throws ValidationException {
		settlementMonthService.saveSettlementMonths(settlementMonth);
	}

	public void updateSettlementMonths(SettlementMonth settlementMonth) throws ValidationException {
		settlementMonthService.updateSettlementMonths(settlementMonth);
	}

	public void deleteSettlementMonths(SettlementMonth settlementMonth) throws ValidationException {
		settlementMonthService.deleteSettlementMonths(settlementMonth);
	}

    public List<BoxSettlementMonth> getBoxesSettlementMonths(int settlementMonth, int box, int unit) throws ValidationException {
        return repository.getBoxesSettlementMonths(settlementMonth, box, unit);
    }

}
