abstract class Produto {
    protected int qtde;

    public Produto(int qtde) {
        this.qtde = qtde;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public abstract double getPreco();

    public double calcularPrecoTotal() {
        return getPreco() * qtde;
    }
}