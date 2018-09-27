package br.com.gabrielspassos.poc.model;

public class Result {

    private Integer custumersNumber;
    private Integer sellersNumber;
    private Long idMostExpensiveSale;
    private String worstSalesmanName;

    public Result() {
    }

    public Result(Integer custumersNumber, Integer sellersNumber, Long idMostExpensiveSale, String worstSalesmanName) {
        this.custumersNumber = custumersNumber;
        this.sellersNumber = sellersNumber;
        this.idMostExpensiveSale = idMostExpensiveSale;
        this.worstSalesmanName = worstSalesmanName;
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

    public String getWorstSalesmanName() {
        return worstSalesmanName;
    }

    public void setWorstSalesmanName(String worstSalesmanName) {
        this.worstSalesmanName = worstSalesmanName;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\tcustumersNumber=" + custumersNumber + "," +
                "\n\tsellersNumber=" + sellersNumber + "," +
                "\n\tidMostExpensiveSale=" + idMostExpensiveSale + "," +
                "\n\tworstSalesmanName='" + worstSalesmanName + '\'' +
                "\n}";
    }
}
