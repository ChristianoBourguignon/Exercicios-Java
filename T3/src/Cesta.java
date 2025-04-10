class Cesta {
    private Produto[] produtos;
    private int posicao;
    private int qtndTotal;
    private static final int MAX_PRODUTOS = 12;

    public Cesta() {
        produtos = new Produto[MAX_PRODUTOS];
        posicao = 0;
        qtndTotal = 0;
    }

    public void adicionarItem(Produto item) {
        int qtndDisponivel = MAX_PRODUTOS - qtndTotal;

        if (item.getQtde() > qtndDisponivel) {
            item.setQtde(qtndDisponivel);
        }
        if (item.getQtde() > 0) {
            produtos[posicao] = item;
            posicao++;
            qtndTotal += item.getQtde();
            System.out.println(item.toString() + " foi " +
                    "adicionada " + item.getQtde() +
                    " unidades");
        } else {
            System.out.println("A cesta está cheia.");
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < posicao; i++) {
            total += produtos[i].calcularPrecoTotal();
        }
        return total;
    }

    public void listarCesta() {
        if (posicao == 0) {
            System.out.println("A cesta está vazia.");
        } else {
            System.out.println(
                    "======================");
            for (int i = 0; i < posicao; i++) {
                Produto produto = produtos[i];

                System.out.println("Produto: " + produto.toString() +
                        " Quantidade: " + produto.getQtde() + " Preço: " + produto.calcularPrecoTotal());
            }
            System.out.println(
                    "======================");
            System.out.println("Total de itens: " + qtndTotal);
            System.out.printf("Valor total: R$%.2f%n",
                    calcularTotal());
            System.out.println(
                    "======================");
        }
    }
    public void limparCesta() {
        for (int i = 0; i < posicao; i++) {
            produtos[i] = null;
        }
        posicao = 0;
        qtndTotal = 0;
        System.out.println("A cesta foi esvaziada.");
    }
}