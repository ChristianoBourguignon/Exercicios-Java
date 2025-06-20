class Pera extends Produto {
    public Pera(int qtde) {
        super(qtde);
    }

    @Override
    public double getPreco() {
        return 5.00;
    }

    @Override
    public String toString() {
        return "Pera";
    }
}