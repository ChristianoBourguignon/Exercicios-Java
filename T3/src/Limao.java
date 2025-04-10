class Limao extends Produto {
    public Limao(int qtde) {
        super(qtde);
    }

    @Override
    public double getPreco() {
        return 1.99;
    }

    @Override
    public String toString() {
        return "Limão";
    }
}