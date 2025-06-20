class Uva extends Produto {
    public Uva(int qtde) {
        super(qtde);
    }

    @Override
    public double getPreco() {
        return 0.99;
    }

    @Override
    public String toString() {
        return "Uva";
    }
}