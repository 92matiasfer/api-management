package uy.com.stronghold.apimanagement.outputs;

public class UnitBoxMonthLiquidationResponse {

    private Long idBoxSettlementMonth;
    private String name;
    private Long idUnitBoxSettlementMonth;
    private float amountSettlementMonth;
    private float previousBalance;
    private float currentBalance;
    
    public Long getIdBoxSettlementMonth() {
        return idBoxSettlementMonth;
    }
    public void setIdBoxSettlementMonth(Long idBoxSettlementMonth) {
        this.idBoxSettlementMonth = idBoxSettlementMonth;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getIdUnitBoxSettlementMonth() {
        return idUnitBoxSettlementMonth;
    }
    public void setIdUnitBoxSettlementMonth(Long idUnitBoxSettlementMonth) {
        this.idUnitBoxSettlementMonth = idUnitBoxSettlementMonth;
    }
    public float getAmountSettlementMonth() {
        return amountSettlementMonth;
    }
    public void setAmountSettlementMonth(float amountSettlementMonth) {
        this.amountSettlementMonth = amountSettlementMonth;
    }
    public float getPreviousBalance() {
        return previousBalance;
    }
    public void setPreviousBalance(float previousBalance) {
        this.previousBalance = previousBalance;
    }
    public float getCurrentBalance() {
        return currentBalance;
    }
    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    
    public UnitBoxMonthLiquidationResponse() {
        super();
    }
    public UnitBoxMonthLiquidationResponse(Long idBoxSettlementMonth, String name, Long idUnitBoxSettlementMonth,
            float amountSettlementMonth, float previousBalance, float currentBalance) {
        this.idBoxSettlementMonth = idBoxSettlementMonth;
        this.name = name;
        this.idUnitBoxSettlementMonth = idUnitBoxSettlementMonth;
        this.amountSettlementMonth = amountSettlementMonth;
        this.previousBalance = previousBalance;
        this.currentBalance = currentBalance;
    }

   

}
