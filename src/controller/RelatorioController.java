package controller;

import dao.RelatorioDAO;

public class RelatorioController {
    private RelatorioDAO relatorioDAO;

    public RelatorioController() {
        relatorioDAO = new RelatorioDAO();
    }

    public String gerarRelatorioProdutos() {
        return relatorioDAO.obterRelatorioProdutos();
    }

    public String gerarRelatorioMovimentacoes() {
        return relatorioDAO.obterRelatorioMovimentacoes();
    }

    public String gerarRelatorioBaixoEstoque() {
        return relatorioDAO.obterRelatorioBaixoEstoque();
    }

    public String gerarRelatorioLucro() {
        return relatorioDAO.obterRelatorioLucro();
    }
}
