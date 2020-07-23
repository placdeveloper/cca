package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import java.math.BigDecimal;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelTransferenciaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelContaCapitalServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Relatorio ReciboTransferenciaCapital
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2ReciboTransferenciaCapitalServicoEJB implements IProcessamentoRelatorioServico {

	@EJB
	private RelContaCapitalServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		RelTransferenciaCapitalDTO dtoRelatorio = new RelTransferenciaCapitalDTO();
		
		dtoRelatorio.setIdInstituicaoDebito(Integer.valueOf(dto.getDados().get("idInstituicaoDebito").toString()));
		dtoRelatorio.setNumContaCapitalDebito(Integer.valueOf(dto.getDados().get("numContaCapitalDebito").toString()));
		dtoRelatorio.setIdPessoaDebito(Integer.valueOf(dto.getDados().get("idPessoaDebito").toString()));			
		dtoRelatorio.setIdContaCapitalDebito(Integer.valueOf(dto.getDados().get("idContaCapitalDebito").toString()));			
		
		dtoRelatorio.setIdInstituicaoCredito(Integer.valueOf(dto.getDados().get("idInstituicaoCredito").toString()));
		dtoRelatorio.setNumContaCapitalCredito(Integer.valueOf(dto.getDados().get("numContaCapitalCredito").toString()));
		dtoRelatorio.setIdPessoaCredito(Integer.valueOf(dto.getDados().get("idPessoaCredito").toString()));			
		dtoRelatorio.setIdContaCapitalCredito(Integer.valueOf(dto.getDados().get("idContaCapitalCredito").toString()));			

		dtoRelatorio.setVlrTransferir(new BigDecimal(dto.getDados().get("vlrTransferir").toString()));
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelTransferenciaCapitalDTO> rel = 
				(RelatorioContaCapitalV2<RelTransferenciaCapitalDTO>) servico.gerarReciboTransferenciaCapital(dtoRelatorio);
		
		return rel.gerar(rDto);
	}

}
