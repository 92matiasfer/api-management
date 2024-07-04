package uy.com.stronghold.apimanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import uy.com.stronghold.apimanagement.enums.Errores;
import uy.com.stronghold.apimanagement.exceptions.ValidationException;
import uy.com.stronghold.apimanagement.models.BoxSettlementMonth;
import uy.com.stronghold.apimanagement.models.Building;
import uy.com.stronghold.apimanagement.models.UnitBoxSettlementMonth;

@Repository
public class QueriesRepository {

	@PersistenceContext
    private EntityManager entityManager;
	
	public List<Building> getBuildings() {
        return entityManager.createQuery(
                "SELECT new uy.com.stronghold.apimanagement.models.Building(b.id, b.name) "
                + "FROM building b "
                + "ORDER BY b.name ASC",
                Building.class).getResultList();
    }


    public List<BoxSettlementMonth> getBoxesSettlementMonths(int settlementMonth, int box, int unit) throws ValidationException{

        try {
            String jpql = "SELECT bxm.id AS id_box_settlement_month, b.name, ubsm.id AS id_unit_box_settlement_month, ubsm.amount_settlement_month, ubsm.previous_balance, ubsm.current_balance\r\n" + 
                                "FROM box_settlement_month bxm\r\n" + 
                                "INNER JOIN box b ON bxm.id_box = b.id\r\n" + 
                                "INNER JOIN unit_box_settlement_month ubsm ON bxm.id = ubsm.id_box_settlement_month"; 
            if(settlementMonth > 0) jpql += " AND bxm.id_settlement_month = :settlementMonth";
            if(box > 0) jpql += " AND bxm.id_box = :box";
            if(unit > 0) jpql += " AND ubsm.id_unit = :unit";

            Query query = entityManager.createNativeQuery(jpql);
            if(settlementMonth > 0) query.setParameter("settlementMonth", settlementMonth);
            if(box > 0) query.setParameter("box", box);
            if(unit > 0)  query.setParameter("unit", unit);
    
            List<BoxSettlementMonth> results = getDTOBoxesSettlementMonths(query.getResultList());
            return results;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidationException(Errores.BOX_NOT_FOUND);
        }

    }


    private List<BoxSettlementMonth> getDTOBoxesSettlementMonths(List<Object[]> resultList) throws ValidationException {
       
        List<BoxSettlementMonth> results = new ArrayList<>();
        try {
            if(!resultList.isEmpty()) {
                for(Object[] result : resultList) {
                    
                    BoxSettlementMonth bsm = new BoxSettlementMonth();
                    bsm.setId((int)result[0]);
                    
                    UnitBoxSettlementMonth ubsm = new UnitBoxSettlementMonth();
                    ubsm.setBoxName((String)result[1]);
                    ubsm.setId((int)result[2]);
                    ubsm.setAmountSettlementMonth((float)result[3]); 
                    ubsm.setPreviousBalance((float)result[4]);
                    ubsm.setCurrentBalance((float)result[5]);
                    bsm.setUnitsboxesSettlementMonths(new ArrayList<UnitBoxSettlementMonth>() {{add(ubsm);}});
                    results.add(bsm);
                }
            }
            return results;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ValidationException(Errores.ERROR_GET_BOX_SETTLEMENT_MONTH);
        }
    }


	
	
}
