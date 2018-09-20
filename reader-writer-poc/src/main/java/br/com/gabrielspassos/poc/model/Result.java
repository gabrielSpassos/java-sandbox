package br.com.gabrielspassos.poc.model;

public class Result {

    private Integer custumersNumber;
    private Integer sellersNumber;
    private Long idMostExpensiveSale;
    private String getWorstSalesmanName;

    public Result() {
    }

    public Result(Integer custumersNumber, Integer sellersNumber, Long idMostExpensiveSale, String getWorstSalesmanName) {
        this.custumersNumber = custumersNumber;
        this.sellersNumber = sellersNumber;
        this.idMostExpensiveSale = idMostExpensiveSale;
        this.getWorstSalesmanName = getWorstSalesmanName;
    }

    public Integer getCustumersNumber() {
        return custumersNumber;
    }

    public void setCustumersNumber(Integer custumersNumber) {
        this.custumersNumber = custumersNumber;
    }

    public Integer getSellersNumber() {
        return sellersNumber;
    }

    public void setSellersNumber(Integer sellersNumber) {
        this.sellersNumber = sellersNumber;
    }

    public Long getIdMostExpensiveSale() {
        return idMostExpensiveSale;
    }

    public void setIdMostExpensiveSale(Long idMostExpensiveSale) {
        this.idMostExpensiveSale = idMostExpensiveSale;
    }

    public String getGetWorstSalesmanName() {
        return getWorstSalesmanName;
    }

    public void setGetWorstSalesmanName(String getWorstSalesmanName) {
        this.getWorstSalesmanName = getWorstSalesmanName;
    }

    @Override
    public String toString() {
        return "Result{" +
                "custumersNumber=" + custumersNumber +
                ", sellersNumber=" + sellersNumber +
                ", idMostExpensiveSale=" + idMostExpensiveSale +
                ", getWorstSalesmanName='" + getWorstSalesmanName + '\'' +
                '}';
    }
}
