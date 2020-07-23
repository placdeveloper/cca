package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.constantes.TemplatesRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelResumoLancamentosDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelResumoLancamentosServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelResumoLancamentosServicoRemote;

/**
 * @author Kleber Alves
 */
@Stateless
@Local(FechRelResumoLancamentosServicoLocal.class)
@Remote(FechRelResumoLancamentosServicoRemote.class)
public class FechRelResumoLancamentosServicoEJB extends RelatorioLancamentoServicoEJB<RelResumoLancamentosDTO>
		implements FechRelResumoLancamentosServicoLocal, FechRelResumoLancamentosServicoRemote {

	@Override
	protected String obtemCodigoRelatorio() {

		return CodigoRelatorio.RELATORIO_RESUMO_LANCAMENTOS;
	}

	@Override
	protected String obtemTemplateRelatorio() {

		return TemplatesRelatorio.RELATORIO_RESUMO_LANCAMENTOS;
	}

	@Override
	protected List<RelResumoLancamentosDTO> obtemLancamentos(Integer instituicaoID, Date dataProduto) throws BancoobException {

		return relatorioLancamentosDAO.obtemResumoLancamentos(dataProduto, dataProduto, instituicaoID);
	}

}