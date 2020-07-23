package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.entidades.negocio.entidades.Parcelamento;
import br.com.sicoob.cca.movimentacao.negocio.dto.BloqueioContaCapitalDTO;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.BloqueioContaCapitalServicoLocal;
import br.com.sicoob.cca.movimentacao.negocio.servicos.interfaces.ParcelamentoContaCapitalServicoLocal;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.constantes.CodigoRelatorio;
import br.com.sicoob.cca.relatorios.negocio.dto.RelCCODTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelImpedimentosDesligamentoDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelParcelamentoDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelImpedimentosDesligamentoServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelImpedimentosDesligamentoServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ParticipanteContaCorrenteIntegracaoRetDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.ContaCorrenteIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.EmprestimoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.EmprestimoIntegracaoLegadoServicoLocal;

/**
 * EJB com os servicos do relatorio de impedimentos.
 */
@Stateless
@Local(RelImpedimentosDesligamentoServicoLocal.class)
@Remote(RelImpedimentosDesligamentoServicoRemote.class) 
public class RelImpedimentosDesligamentoServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelImpedimentosDesligamentoServicoLocal, RelImpedimentosDesligamentoServicoRemote {
	
	@EJB
	private CapesIntegracaoServicoLocal capesInt;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;
	
	@EJB
	private ParcelamentoContaCapitalServicoLocal parServico;
	
	@EJB
	private EmprestimoIntegracaoLegadoServicoLocal emprestimoServico;
	
	@EJB
	private ContaCorrenteIntegracaoServicoLocal ccoServico;
	
	@EJB
	private BloqueioContaCapitalServicoLocal bloqueioContaCapitalServico;

	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	/**
	 * @see br.com.sicoob.cca.relatorios.negocio.servicos.RelImpedimentosDesligamentoServico#gerarRelatorioImpedimentosDesligamento(br.com.sicoob.cca.relatorios.negocio.dto.RelImpedimentosDesligamentoDTO)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarRelatorioImpedimentosDesligamento(RelImpedimentosDesligamentoDTO dtoEntrada) throws BancoobException {
		RelImpedimentosDesligamentoDTO dtoRelatorio = buscarDadosRelatorio(dtoEntrada);
		List<RelImpedimentosDesligamentoDTO> lista = new ArrayList<RelImpedimentosDesligamentoDTO>(0);
		lista.add(dtoRelatorio);
		
		String nomeRelatorio = "CCA_Relatorio_ImpedimentosDesligamento.jasper";
		
		Map<String, Object> parametros = getParametrosComuns();
		
		InstituicaoIntegracaoDTO dtoInstituicaoIntegracao = instituicaoIntegracaoServico.obterInstituicaoIntegracao(dtoEntrada.getIdInstituicao());
		Integer numCliente = capesInt.obterPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao()).getIdPessoaLegado();
		
		if (!dtoEntrada.isEsconderEmprestimos()) {
			parametros.put("listaEmprestimos", criarLstEmprestimos(numCliente, dtoEntrada.getIdInstituicao(), dtoEntrada.getIdPessoa()));
		}
		parametros.put("listaCCO", criarLstCco(dtoEntrada.getIdInstituicao(), dtoEntrada.getIdPessoa()));
		parametros.put("listaParcelamentos", getParcelamentosEmAberto(dtoEntrada.getIdContaCapital()));
		parametros.put("listaBloqueios", criarLstBloqueios(dtoEntrada));
		
		parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracao.getNumero() + " - " + dtoInstituicaoIntegracao.getDescInstituicao());
		parametros.put("COD_RELATORIO", CodigoRelatorio.COD_IMPEDIMENTOS_DESLIGAMENTO);
		
		return new RelatorioContaCapitalV2<RelImpedimentosDesligamentoDTO>(lista, nomeRelatorio, parametros);
	}
	
	private List<BloqueioContaCapitalDTO> criarLstBloqueios(RelImpedimentosDesligamentoDTO dtoEntrada) throws BancoobException {
		List<BloqueioContaCapitalDTO> bloqueios = new ArrayList<BloqueioContaCapitalDTO>();
		ContaCapital cca = contaCapitalServico.obter(dtoEntrada.getIdContaCapital());
		if (cca.getValorBloq().compareTo(BigDecimal.ZERO) > 0) {
			BloqueioContaCapitalDTO bloqueio = new BloqueioContaCapitalDTO();
			bloqueio.setCodTipoBloqueio(0);
			bloqueio.setNomeTipoBloqueio("ProcapCred / CotasPartes");
			bloqueio.setValorBloqueado(cca.getValorBloq());
			bloqueios.add(bloqueio);
		}
		BloqueioContaCapitalDTO filtro = new BloqueioContaCapitalDTO();
		filtro.setIdInstituicao(dtoEntrada.getIdInstituicao());
		filtro.setNumContaCapital(cca.getNumContaCapital());
		List<BloqueioContaCapitalDTO> outrosBloqueios = bloqueioContaCapitalServico.consultarBloqueios(filtro);
		if (outrosBloqueios != null && !outrosBloqueios.isEmpty()) {
			for (BloqueioContaCapitalDTO bloq : outrosBloqueios) {
				if (Boolean.TRUE.equals(bloq.getAtivo())) {
					bloqueios.add(bloq);
				}
			}
		}
		return bloqueios;
	}

	private List<EmprestimoIntegracaoDTO> criarLstEmprestimos(Integer numCliente, Integer idInstituicao, Integer idPessoa) throws BancoobException {
		List<EmprestimoIntegracaoDTO> lstEmprestimos = new ArrayList<EmprestimoIntegracaoDTO>();
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdInstituicao(idInstituicao);
		ccoDTO.setIdPessoa(idPessoa);
		List<ContaCorrenteIntegracaoRetDTO> lstCco = ccoServico.consultarContaCorrenteAtivaPorNumeroCliente(ccoDTO);
		for (ContaCorrenteIntegracaoRetDTO cco : lstCco) {
			List<EmprestimoIntegracaoDTO> emprestimos = emprestimoServico.consultarEmprestimos(numCliente, cco.getNumeroContaCorrente());
			if (emprestimos != null) {
				lstEmprestimos.addAll(emprestimos);
			}
		}
		return lstEmprestimos;
	}

	private List<RelCCODTO> criarLstCco(Integer idInstituicao, Integer idPessoa) throws BancoobException {
		ContaCorrenteIntegracaoDTO ccoDTO = new ContaCorrenteIntegracaoDTO();
		ccoDTO.setIdInstituicao(idInstituicao);
		ccoDTO.setIdPessoa(idPessoa);
		List<ContaCorrenteIntegracaoRetDTO> lstCco = ccoServico.consultarContaCorrenteImpeditivaDesligamento(ccoDTO);
		
		/**
		 * Conta Corrente
		 */
		List<RelCCODTO> lstRetorno = new ArrayList<RelCCODTO>(0);
		
		RelCCODTO relCcoDTO = null;
		if(lstCco != null && !lstCco.isEmpty()) {
			for (ContaCorrenteIntegracaoRetDTO cco : lstCco) {
				for (ParticipanteContaCorrenteIntegracaoRetDTO par : cco.getLstParticipanteContaCorrenteIntegracaoRetDTO()) {				
					ccoDTO.setNumContaCorrente(cco.getNumeroContaCorrente());					
					relCcoDTO = new RelCCODTO();
					relCcoDTO.setNumeroContaCorrente(cco.getNumeroContaCorrente());
					relCcoDTO.setTitularidade(par.getDescricaoResponsabilidade());
					lstRetorno.add(relCcoDTO);
				}
			}
		}
		
		return lstRetorno;
	}
	
	private List<RelParcelamentoDTO> getParcelamentosEmAberto(Integer idContaCapital) throws BancoobException {
		
		List<Parcelamento> lst = parServico.pesquisarParcelasEmAberto(idContaCapital);
		
		Map<Short, RelParcelamentoDTO> map = new HashMap<Short, RelParcelamentoDTO>();
		
		RelParcelamentoDTO dto = null;
		
        /**
         * Parcelamento
         */
		for(Parcelamento p : lst) {
			
			if(map.containsKey(p.getNumParcelamento())) {
				int parcelasAbertas = map.get(p.getNumParcelamento()).getParcelasAbertas(); 
				BigDecimal soma = map.get(p.getNumParcelamento()).getValorTotal();
				
				map.get(p.getNumParcelamento()).setParcelasAbertas(parcelasAbertas + 1);
				if(p.getValor() != null){
					map.get(p.getNumParcelamento()).setValorTotal(soma.add(p.getValor()));
				}
			} else {
				
				dto = new RelParcelamentoDTO();
				if(p.getNumParcelamento() != null){
					dto.setNumParcelamento(p.getNumParcelamento().intValue());
				}
				if(p.getTipoParcelamento() != null){
					dto.setTipoParcelamento(p.getTipoParcelamento().getDescricao());
				}
				dto.setValorTotal(p.getValor());
				dto.setParcelasAbertas(1);
				
				map.put(p.getNumParcelamento(), dto);
			}
		}
		
		List<RelParcelamentoDTO> lstOrdenada = new ArrayList<RelParcelamentoDTO>();
		lstOrdenada.addAll(map.values());
		Collections.sort(lstOrdenada, new Comparator<RelParcelamentoDTO>() {
			public int compare(RelParcelamentoDTO o1, RelParcelamentoDTO o2) { return o1.getNumParcelamento().compareTo(o2.getNumParcelamento());}
		}); 
		
		return lstOrdenada;
	}

	private RelImpedimentosDesligamentoDTO buscarDadosRelatorio(RelImpedimentosDesligamentoDTO dtoEntrada) throws BancoobException {
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(dtoEntrada.getIdPessoa(), dtoEntrada.getIdInstituicao());
		ContaCapital contaCapital = contaCapitalServico.obter(dtoEntrada.getIdContaCapital());

		RelImpedimentosDesligamentoDTO dto = new RelImpedimentosDesligamentoDTO();
		
		/**
		 * Dados do Associado
		 */
		dto.setNomeCompleto(pessoaIntegracaoDTO.getNomeCompleto());
		dto.setCpfCnpj(pessoaIntegracaoDTO.getCpfCnpj());
		dto.setNumContaCapital(contaCapital.getNumContaCapital());
		dto.setDataAbertura(contaCapital.getDataMatricula());
		
		/**
		 * Capital Bloqueado
		 */
		dto.setValorBloqueado(contaCapital.getValorBloq());
		
		return dto;
	}
	
}
