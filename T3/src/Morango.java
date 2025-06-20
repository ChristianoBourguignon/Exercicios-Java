class Morango extends Produto {
    public Morango(int qtde) {
        super(qtde);
    }

    @Override
    public double getPreco() {
        return 1.2;
    }

    @Override
    public String toString() {
        return "Morango";
    }
}