class Mamao extends Produto {
    public Mamao(int qtde) {
        super(qtde);
    }

    @Override
    public double getPreco() {
        return 18.99;
    }

    @Override
    public String toString() {
        return "Mamão";
    }
}