package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.constantes.TemplatesRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosAnaliticoDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelLancamentosAnaliticoServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelLancamentosAnaliticoServicoRemote;

/**
 * @author Kleber Alves
 */
@Stateless
@Local(FechRelLancamentosAnaliticoServicoLocal.class)
@Remote(FechRelLancamentosAnaliticoServicoRemote.class)
public class FechRelLancamentosAnaliticoServicoEJB extends RelatorioLancamentoServicoEJB<RelLancamentosAnaliticoDTO>
		implements FechRelLancamentosAnaliticoServicoLocal, FechRelLancamentosAnaliticoServicoRemote {

	@Override
	protected String obtemCodigoRelatorio() {

		return CodigoRelatorio.RELATORIO_LANCAMENTOS_NAO_CONTABILIZADOS_ANALITICO;
	}

	@Override
	protected String obtemTemplateRelatorio() {

		return TemplatesRelatorio.RELATORIO_LANCAMENTOS_NAO_CONTABILIZADOS_ANALITICO;
	}

	protected List<RelLancamentosAnaliticoDTO> obtemLancamentos(Integer instituicaoID, Date dataProduto) throws BancoobException {

		return relatorioLancamentosDAO.obtemLancamentosNaoContabilizadosAnalitico(dataProduto, dataProduto, instituicaoID);
	}
}