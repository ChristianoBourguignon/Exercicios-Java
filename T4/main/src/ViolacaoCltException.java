public class ViolacaoCltException extends IllegalArgumentException {
    private final int codigoArtigo;

    public ViolacaoCltException(String mensagem, int codigoArtigo) {
        super(mensagem);
        this.codigoArtigo = codigoArtigo;
    }

    public int getCodigoArtigo() {
        return codigoArtigo;
    }

    @Override
    public String toString() {
        return super.getMessage() + " (Artigo da CLT: " + codigoArtigo + ")";
    }
}
