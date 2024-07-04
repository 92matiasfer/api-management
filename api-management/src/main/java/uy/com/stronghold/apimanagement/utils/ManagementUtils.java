package uy.com.stronghold.apimanagement.utils;

import uy.com.stronghold.apimanagement.enums.UnitType;
import uy.com.stronghold.apimanagement.models.Unit;

public class ManagementUtils {

    public static String getNameSettlementMonth(int year, int month){

        String[] months = {
            "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        };
        return  months[month - 1] + " " + year;
    }


    public static String getNumberUnitFormated(String number, UnitType type){
        if(type.equals(UnitType.owner))return number + " P"; 
        else if(type.equals(UnitType.renter))return number + " I";
        else return number + " A";
    }
}
