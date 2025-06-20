class Banana extends Produto {
    public Banana(int qtde) {
        super(qtde);
    }

    @Override
    public double getPreco() {
        return 1.50;
    }

    @Override
    public String toString() {
        return "Banana";
    }
}