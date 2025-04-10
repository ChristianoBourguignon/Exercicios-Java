class Maca extends Produto {
    public Maca(int qtde) {
        super(qtde);
    }

    @Override
    public double getPreco() {
        return 2.00;
    }

    @Override
    public String toString() {
        return "Maçã";
    }
}