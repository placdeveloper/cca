package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.constantes.TemplatesRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelLancamentosSinteticoDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelLancamentosSinteticoServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.FechRelLancamentosSinteticoServicoRemote;

/**
 * @author Kleber Alves
 */
@Stateless
@Local(FechRelLancamentosSinteticoServicoLocal.class)
@Remote(FechRelLancamentosSinteticoServicoRemote.class)
public class FechRelLancamentosSinteticoServicoEJB extends RelatorioLancamentoServicoEJB<RelLancamentosSinteticoDTO>
		implements FechRelLancamentosSinteticoServicoLocal, FechRelLancamentosSinteticoServicoRemote {

	@Override
	protected String obtemCodigoRelatorio() {

		return CodigoRelatorio.RELATORIO_LANCAMENTOS_NAO_CONTABILIZADOS_SINTETICO;
	}

	@Override
	protected String obtemTemplateRelatorio() {

		return TemplatesRelatorio.RELATORIO_LANCAMENTOS_NAO_CONTABILIZADOS_SINTETICO;
	}

	protected List<RelLancamentosSinteticoDTO> obtemLancamentos(Integer instituicaoID, Date dataProduto) throws BancoobException {

		return relatorioLancamentosDAO.obtemLancamentosNaoContabilizadosSintetico(dataProduto, dataProduto, instituicaoID);
	}
}