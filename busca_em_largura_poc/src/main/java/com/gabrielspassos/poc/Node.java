package com.gabrielspassos.poc;

public class Node {

    private Integer valor;
    private Boolean checked;
    private Boolean connection;

    public Node(Integer valor, Boolean connection) {
        this.valor = valor;
        this.connection = connection;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getConnection() {
        return connection;
    }

    public void setConnection(Boolean connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        if(getConnection()){
            return "Node: " + valor + " -- ";
        }
        return "Node: " + valor +"    ";
    }
}
