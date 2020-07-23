package br.com.sicoob.cca.relatorios.negocio.servicos.ejb.v2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.movimentacao.negocio.delegates.ContaCapitalMovimentacaoFabricaDelegates;
import br.com.sicoob.cca.movimentacao.negocio.delegates.DebitoIndeterminadoDelegate;
import br.com.sicoob.cca.movimentacao.negocio.dto.ConsultaDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelDebitoIndeterminadoRenDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelDebitoIndeterminadoServicoLocal;
import br.com.sicoob.relatorio.api.dto.ParametroDTO;
import br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO;
import br.com.sicoob.relatorio.api.dto.RetornoProcessamentoRelatorioDTO;
import br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico;

/**
 * Relatorio DebitoIndeterminado
 */
@Stateless
@Remote(IProcessamentoRelatorioServico.class)
public class RelV2DebitoIndeterminadoServicoEJB implements IProcessamentoRelatorioServico {

	private DebitoIndeterminadoDelegate debIndDelegate = ContaCapitalMovimentacaoFabricaDelegates.getInstance().criarDebitoIndeterminadoDelegate();
	
	@EJB
	private RelDebitoIndeterminadoServicoLocal servico;
	
	/**
	 * @see br.com.sicoob.relatorio.api.interfaces.IProcessamentoRelatorioServico#gerarRelatorio(br.com.sicoob.relatorio.api.dto.ParametroDTO, br.com.sicoob.relatorio.api.dto.RelatorioDadosDTO)
	 */
	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) 
	public RetornoProcessamentoRelatorioDTO gerarRelatorio(ParametroDTO dto, RelatorioDadosDTO rDto) throws BancoobException {
		
		String filtro = dto.getDados().get("filtro").toString();
		
		ConsultaDebitoIndeterminadoRenDTO consDto = new ConsultaDebitoIndeterminadoRenDTO();
		consDto.setIdTipoPessoa(dto.getDados().get("idTipoPessoa") == null ? null : Integer.valueOf(dto.getDados().get("idTipoPessoa").toString()));
		consDto.setIdFormaDebito(dto.getDados().get("idFormaDebito") == null ? null : Integer.valueOf(dto.getDados().get("idFormaDebito").toString()));
		consDto.setValor(dto.getDados().get("valor") == null ? null : BigDecimal.valueOf(Double.parseDouble(dto.getDados().get("valor").toString())));
		consDto.setDiaDebito(dto.getDados().get("diaDebito") == null ? null : Integer.valueOf(dto.getDados().get("diaDebito").toString()));
		consDto.setNumContaCapital(dto.getDados().get("numContaCapital") == null ? null : Integer.valueOf(dto.getDados().get("numContaCapital").toString()));
		consDto.setNome(dto.getDados().get("nome") == null ? null : dto.getDados().get("nome").toString());
		consDto.setCpfCnpj(dto.getDados().get("cpfCnpj") == null ? null : dto.getDados().get("cpfCnpj").toString());
		if (rDto.getUsuarioBancoobDTO().getIdInstituicao() != null) {
			consDto.setIdInstituicao(Integer.valueOf(rDto.getUsuarioBancoobDTO().getIdInstituicao()));
		}
		List<ConsultaDebitoIndeterminadoRenDTO> lstDTO = debIndDelegate.pesquisar(consDto);
		
		servico.configurarParametrosComuns(rDto);
		RelatorioContaCapitalV2<RelDebitoIndeterminadoRenDTO> rel = 
				(RelatorioContaCapitalV2<RelDebitoIndeterminadoRenDTO>) servico.gerarRelatorioDebitoIndeterminado(
						converterParaRelDebitoIndeterminadoDTO(lstDTO), filtro);
		
		return rel.gerar(rDto);
	}
	
	private List<RelDebitoIndeterminadoRenDTO> converterParaRelDebitoIndeterminadoDTO(List<ConsultaDebitoIndeterminadoRenDTO> lstEntrada) throws BancoobException {
		List<RelDebitoIndeterminadoRenDTO> lstSaida = new ArrayList<RelDebitoIndeterminadoRenDTO>();
		RelDebitoIndeterminadoRenDTO saida = null;
		for(ConsultaDebitoIndeterminadoRenDTO entrada : lstEntrada) {
			saida = new RelDebitoIndeterminadoRenDTO();
			saida.setCpfCnpj(entrada.getCpfCnpj());
			saida.setDataPeriodoDeb(entrada.getDataPeriodoDeb());
			saida.setFormaDebito(entrada.getFormaDebito());
			saida.setTipoPessoa(entrada.getTipoPessoa());
			saida.setNome(entrada.getNome());
			saida.setNumContaCapital(entrada.getNumContaCapital());
			saida.setValor(entrada.getValor());
			lstSaida.add(saida);
		}
		return lstSaida;
	}
	
}
