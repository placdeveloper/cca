/*
 * 
 */
package br.com.sicoob.sisbr.cca.integracao.negocio.servicos.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.lang.StringUtils;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.sicoob.capes.api.negocio.delegates.AnotacaoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.BemPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.CAPESApiFabricaDelegates;
import br.com.sicoob.capes.api.negocio.delegates.ClienteDelegate;
import br.com.sicoob.capes.api.negocio.delegates.EnderecoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.FonteRendaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaFisicaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.PessoaJuridicaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.ReferenciaPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.RelacionamentoPessoaDelegate;
import br.com.sicoob.capes.api.negocio.delegates.TelefonePessoaDelegate;
import br.com.sicoob.capes.api.negocio.vo.AnotacaoPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.BemPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.ClienteVO;
import br.com.sicoob.capes.api.negocio.vo.EnderecoPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.FonteRendaPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaFisicaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaJuridicaVO;
import br.com.sicoob.capes.api.negocio.vo.PessoaVO;
import br.com.sicoob.capes.api.negocio.vo.ReferenciaPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.RelacionamentoPessoaVO;
import br.com.sicoob.capes.api.negocio.vo.TelefonePessoaVO;
import br.com.sicoob.capes.comum.negocio.enums.TipoEnderecoEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoRelacionamentoPessoaEnum;
import br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.CapesIntegracaoDelegate;
import br.com.sicoob.sisbr.cca.integracao.negocio.delegates.ContaCapitalIntegracaoFabricaDelegates;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.AnotacaoPessoaDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.BemPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.EnderecoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.FonteRendaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaFisicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaJuridicaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.ReferenciaPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.RelacionamentoPessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.TelefonePessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.enumintegracao.TipoReferenciaEnum;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoCapesException;
import br.com.sicoob.sisbr.cca.integracao.negocio.excecao.IntegracaoCapesNegocioException;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoRemote;
import br.com.sicoob.sisbr.localidade.api.filtro.LocApiFabricaFiltro;
import br.com.sicoob.sisbr.localidade.api.filtro.LocApiFiltroTipoLogradouro;
import br.com.sicoob.sisbr.localidade.api.negocio.delegates.LocApiFabricaDelegates;
import br.com.sicoob.sisbr.localidade.api.negocio.delegates.LocApiTipoLogradouroDelegate;

/**
 * EJB contendo servicos relacionados a CapesIntegracao.
 */
@Stateless
@Local (CapesIntegracaoServicoLocal.class)
@Remote(CapesIntegracaoServicoRemote.class)
public class CapesIntegracaoServicoEJB extends ContaCapitalIntegracaoServicoEJB implements CapesIntegracaoServicoLocal, CapesIntegracaoServicoRemote {
	
	private PessoaDelegate pessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaDelegate();
	private PessoaFisicaDelegate pessoaFisicaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaFisicaDelegate();
	private TelefonePessoaDelegate telefonePessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarTelefonePessoaDelegate();
	private FonteRendaPessoaDelegate fonteRendaPessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarFonteRendaPessoaDelegate();
	private BemPessoaDelegate bemPessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarBemPessoaDelegate();
	private ReferenciaPessoaDelegate referenciaPessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarReferenciaPessoaDelegate();
	private PessoaJuridicaDelegate pessoaJuridicaDelegate = CAPESApiFabricaDelegates.getInstance().criarPessoaJuridicaDelegate();
	private RelacionamentoPessoaDelegate relacionamentoPessoaDelegate =  CAPESApiFabricaDelegates.getInstance().criarRelacionamentoPessoaDelegate();
	private ClienteDelegate clienteDelegate = CAPESApiFabricaDelegates.getInstance().criarClienteDelegate();
	private CapesIntegracaoDelegate capesIntegracaoDelegate = ContaCapitalIntegracaoFabricaDelegates.getInstance().criarCapesIntegracaoDelegate();
	private AnotacaoPessoaDelegate anotacaoPessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarAnotacaoPessoaDelegate();
	private EnderecoPessoaDelegate enderecoPessoaDelegate = CAPESApiFabricaDelegates.getInstance().criarEnderecoPessoaDelegate();
	private LocApiTipoLogradouroDelegate logradouroDelegate = LocApiFabricaDelegates.getInstancia().criarLocApiTipoLogradouroDelegate();

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterPorCpfCnpjInstituicao(String, Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public PessoaIntegracaoDTO obterPorCpfCnpjInstituicao(String cpfCnpj,Integer idInstituicao) throws BancoobException {
				
		PessoaIntegracaoDTO pessoaIntegracaoDTO = null;
		
		try{
			PessoaVO pessoaVO = pessoaDelegate.obterPorCpfCnpjInstituicao(cpfCnpj, idInstituicao);
			
			if (pessoaVO != null) {
				pessoaIntegracaoDTO = new PessoaIntegracaoDTO();
				pessoaIntegracaoDTO.setIdPessoa(pessoaVO.getIdPessoa());
				pessoaIntegracaoDTO.setIdPessoaLegado(pessoaVO.getIdPessoaLegado());
				pessoaIntegracaoDTO.setNomePessoa(pessoaVO.getNomeCompleto());
				pessoaIntegracaoDTO.setCpfCnpj(pessoaVO.getCpfCnpj());
				pessoaIntegracaoDTO.setCodTipoPessoa(pessoaVO.getCodTipoPessoa());
				pessoaIntegracaoDTO.setIdInstituicao(pessoaVO.getIdInstituicao());
				pessoaIntegracaoDTO.setNomeApelido(pessoaVO.getNomeApelido());
				pessoaIntegracaoDTO.setNomeCompleto(pessoaVO.getNomeCompleto());
				pessoaIntegracaoDTO.setCodCNAE(pessoaVO.getCodigoCnaeFiscal());
			}
			
			return pessoaIntegracaoDTO;
			
		}catch (BancoobRuntimeException e) {
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}catch(BancoobException e){
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}
				
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterIdPessoaPorIdPessoaLegado(Integer, Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public Integer obterIdPessoaPorIdPessoaLegado(Integer idPessoaLegado,Integer idInstituicao) throws BancoobException {
		this.getLogger().info("CCA.obterIdPessoaPorIdPessoaLegado");
		Integer idPessoa = null;
		
		try{
			PessoaVO pessoaVO = pessoaDelegate.obterPorPessoaLegadoInstituicao(idPessoaLegado, idInstituicao);
			
			if (pessoaVO != null) {
				idPessoa = pessoaVO.getIdPessoa();
			}
			
			return idPessoa;
			
		}catch (BancoobRuntimeException e) {
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}catch(BancoobException e){
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}		
				
	}	
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterPessoaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public PessoaIntegracaoDTO obterPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException {
		this.getLogger().info("CCA.obterPessoaInstituicao");
		PessoaIntegracaoDTO pessoaIntegracaoDTO = new PessoaIntegracaoDTO();
		
		try{
			PessoaVO pessoaVO = pessoaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
			
			if (pessoaVO != null) {
				pessoaIntegracaoDTO.setIdPessoa(pessoaVO.getIdPessoa());
				pessoaIntegracaoDTO.setIdPessoaLegado(pessoaVO.getIdPessoaLegado());
				pessoaIntegracaoDTO.setNomePessoa(pessoaVO.getNomeCompleto());
				pessoaIntegracaoDTO.setCpfCnpj(pessoaVO.getCpfCnpj());
				pessoaIntegracaoDTO.setCodTipoPessoa(pessoaVO.getCodTipoPessoa());
				pessoaIntegracaoDTO.setIdInstituicao(pessoaVO.getIdInstituicao());
				pessoaIntegracaoDTO.setNomeApelido(pessoaVO.getNomeApelido());
				pessoaIntegracaoDTO.setNomeCompleto(pessoaVO.getNomeCompleto());
				pessoaIntegracaoDTO.setCodCNAE(pessoaVO.getCodigoCnaeFiscal());
				pessoaIntegracaoDTO.setIdUnidadeInst(pessoaVO.getIdUnidadeInst());
			}
			
			return pessoaIntegracaoDTO;
			
		}catch (BancoobRuntimeException e) {
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}catch(BancoobException e){
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterPessoaFisicaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public PessoaFisicaIntegracaoDTO obterPessoaFisicaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException{
		PessoaFisicaIntegracaoDTO pessoaFisicaIntegracaoDTO = new PessoaFisicaIntegracaoDTO();
		
		try{
			PessoaFisicaVO pessoaFisicaVO = pessoaFisicaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
			if(pessoaFisicaVO != null){
				pessoaFisicaIntegracaoDTO.setNascimento(pessoaFisicaVO.getDataNascimento());
				pessoaFisicaIntegracaoDTO.setNomePai(pessoaFisicaVO.getNomePai());
				pessoaFisicaIntegracaoDTO.setNomeMae(pessoaFisicaVO.getNomeMae());
				pessoaFisicaIntegracaoDTO.setDescDocumento(pessoaFisicaVO.getDescricaoTipoDocumento());
				pessoaFisicaIntegracaoDTO.setNumDocumento(pessoaFisicaVO.getNumeroDocumento());
				pessoaFisicaIntegracaoDTO.setOrgaoDocumento(pessoaFisicaVO.getOrgaoExpedidorDocumento());
				pessoaFisicaIntegracaoDTO.setUfDocumento(pessoaFisicaVO.getUfOrgaoExpedidorDocumento());
				pessoaFisicaIntegracaoDTO.setEmissaoDocumento(pessoaFisicaVO.getDataEmissaoDocumento());
				pessoaFisicaIntegracaoDTO.setDescSexo(pessoaFisicaVO.getTipoSexo());
				pessoaFisicaIntegracaoDTO.setDescProfissao(pessoaFisicaVO.getDescricaoOcupacaoProfissional());
				pessoaFisicaIntegracaoDTO.setEstadoCivil(pessoaFisicaVO.getDescricaoEstadoCivil());
				pessoaFisicaIntegracaoDTO.setNaturalidade(pessoaFisicaVO.getDescNaturalidade());
				pessoaFisicaIntegracaoDTO.setIdNaturalidade(pessoaFisicaVO.getIdNaturalidade());
				pessoaFisicaIntegracaoDTO.setNacionalidade(pessoaFisicaVO.getDescricaoNacionalidade());
			}
			
			return pessoaFisicaIntegracaoDTO;
			
		}catch (BancoobRuntimeException e) {
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}catch(BancoobException e){
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterEnderecoPessoaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public EnderecoPessoaIntegracaoDTO obterEnderecoPessoaInstituicao(Integer idPessoa,Integer idInstituicao) throws BancoobException{
		
		EnderecoPessoaIntegracaoDTO enderecoPessoaIntegracaoDTO = new EnderecoPessoaIntegracaoDTO();
		
		try{
			buscarEnderecoResidencialComercialOutros(enderecoPessoaIntegracaoDTO, idPessoa, idInstituicao);
			
			return enderecoPessoaIntegracaoDTO;
			
		}catch (BancoobRuntimeException e) {
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}catch(BancoobException e){
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}
	}
	
	/**
	 * Método responsável de atribuir os valores do endereco correspondencia (residencial) da pessoa ao dto
	 * @param dto
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public EnderecoPessoaIntegracaoDTO buscarEnderecoCorrespondencia(EnderecoPessoaIntegracaoDTO dto, Integer idPessoa, Integer idInstituicao) throws BancoobException{
		
		try{
			EnderecoPessoaVO enderecoPessoaVO = enderecoPessoaDelegate.obterEnderecoCorrespondenciaPorPessoaInstituicao(idPessoa, idInstituicao);
			LocApiFiltroTipoLogradouro filtroLog = new LocApiFabricaFiltro().createLocApiFiltroTipoLogradouro();
			
			if(enderecoPessoaVO != null){
				dto.setIdLocResidencial(enderecoPessoaVO.getIdLocalidade());
				dto.setComplementoEnderecoResidencial(enderecoPessoaVO.getComplemento());
				dto.setBairroEnderecoResidencial(enderecoPessoaVO.getBairro());
				dto.setNumEnderecoResidencial(enderecoPessoaVO.getNumero());
				dto.setCepEnderecoResidencial(enderecoPessoaVO.getCep());
				
				if(enderecoPessoaVO.getIdTipoLogradouro() != null) {
					filtroLog.setId(enderecoPessoaVO.getIdTipoLogradouro().intValue());
					String logradouro = logradouroDelegate.pesquisarTiposLogradouro(filtroLog).get(0).getDescricao();
					dto.setDescEnderecoResidencial(logradouro + " " + enderecoPessoaVO.getDescricao());
					
				} else {
					dto.setDescEnderecoResidencial(enderecoPessoaVO.getDescricao());
				}
			}
			
			return dto;
		
		} catch(BancoobException e){
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}
	}
	
	/**
	 * Método responsável de atribuir os valores do endereco comercial e outros da pessoa ao dto
	 * @param dto
	 * @param idPessoa
	 * @param idInstituicao
	 * @return
	 * @throws BancoobException
	 */
	public EnderecoPessoaIntegracaoDTO buscarEnderecoResidencialComercialOutros(EnderecoPessoaIntegracaoDTO dto, Integer idPessoa, Integer idInstituicao) throws BancoobException{

		try{
			List<EnderecoPessoaVO> lstEnderecoVO = enderecoPessoaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
			LocApiFiltroTipoLogradouro filtroLog = new LocApiFabricaFiltro().createLocApiFiltroTipoLogradouro();
			
			if(!lstEnderecoVO.isEmpty()){
				for (EnderecoPessoaVO endVO : lstEnderecoVO) {
					if(endVO.getCodigoTipoEndereco().equals(TipoEnderecoEnum.RESIDENCIAL.getCodigo())) {
						dto.setIdLocResidencial(endVO.getIdLocalidade());
						dto.setComplementoEnderecoResidencial(endVO.getComplemento());
						dto.setBairroEnderecoResidencial(endVO.getBairro());
						dto.setNumEnderecoResidencial(endVO.getNumero());
						dto.setCepEnderecoResidencial(endVO.getCep());
						
						if(endVO.getIdTipoLogradouro() != null) {
							filtroLog.setId(endVO.getIdTipoLogradouro().intValue());
							String logradouro = logradouroDelegate.pesquisarTiposLogradouro(filtroLog).get(0).getDescricao();
							dto.setDescEnderecoResidencial(logradouro + " " + endVO.getDescricao());
							
						} else {
							dto.setDescEnderecoResidencial(endVO.getDescricao());
						}
					}
					if(endVO.getCodigoTipoEndereco().equals(TipoEnderecoEnum.COMERCIAL.getCodigo())) {
						dto.setIdLocComercial(endVO.getIdLocalidade());
						dto.setComplementoEnderecoComercial(endVO.getComplemento());
						dto.setBairroEnderecoComercial(endVO.getBairro());
						dto.setNumEnderecoComercial(endVO.getNumero());
						dto.setCepEnderecoComercial(endVO.getCep());
						
						if(endVO.getIdTipoLogradouro() != null) {
							filtroLog.setId(endVO.getIdTipoLogradouro().intValue());
							String logradouro = logradouroDelegate.pesquisarTiposLogradouro(filtroLog).get(0).getDescricao();
							dto.setDescEnderecoComercial(logradouro + " " + endVO.getDescricao());
						} else {
							dto.setDescEnderecoComercial(endVO.getDescricao());
						}
					}
					
					if(endVO.getCodigoTipoEndereco().equals(TipoEnderecoEnum.OUTROS.getCodigo())) {
						dto.setIdLocOutros(endVO.getIdLocalidade());
						dto.setComplementoEnderecoOutros(endVO.getComplemento());
						dto.setBairroEnderecoOutros(endVO.getBairro());
						dto.setNumEnderecoOutros(endVO.getNumero());
						dto.setCepEnderecoOutros(endVO.getCep());
						
						if(endVO.getIdTipoLogradouro() != null) {
							filtroLog.setId(endVO.getIdTipoLogradouro().intValue());
							String logradouro = logradouroDelegate.pesquisarTiposLogradouro(filtroLog).get(0).getDescricao();
							dto.setDescEnderecoOutros(logradouro + " " + endVO.getDescricao());
						} else {
							dto.setDescEnderecoOutros(endVO.getDescricao());
						}
					}
				}
			}
			return dto;
			
		}catch(BancoobException e){
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}
	}
	
	/**
	 * @see br.com.sicoob.capes.comum.negocio.enums.TipoTelefoneEnum
	 */
	public TelefonePessoaIntegracaoDTO obterTelefonePessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException{
		
		TelefonePessoaIntegracaoDTO telDTO = new TelefonePessoaIntegracaoDTO();
		List<TelefonePessoaVO> lstTelVO = telefonePessoaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
		
		if(!lstTelVO.isEmpty()){
			for (TelefonePessoaVO telVO : lstTelVO) {
				if(telVO.getCodigoTipoTelefone() != null
				&& telVO.getDdd() != null 
				&& telVO.getTelefone() != null){
					
					//Em caso de 2 telefones do mesmo tipo, pega apenas o primeiro
					if(telVO.getCodigoTipoTelefone().equals(TipoTelefoneEnum.RESIDENCIAL.getCodigo())
					&& telDTO.getTelefoneEnderecoResidencial() == null
					&& telDTO.getRamalEnderecoResidencial() == null) {
						telDTO.setRamalEnderecoResidencial(telVO.getRamal());
						telDTO.setTelefoneEnderecoResidencial("("+telVO.getDdd()+")" +" "+ telVO.getTelefone());
					}
						
					//Em caso de 2 telefones do mesmo tipo, pega apenas o primeiro
					if(telVO.getCodigoTipoTelefone().equals(TipoTelefoneEnum.COMERCIAL.getCodigo())
					&& telDTO.getTelefoneEnderecoComercial() == null
					&& telDTO.getRamalEnderecoComercial() == null) {
						telDTO.setTelefoneEnderecoComercial("("+telVO.getDdd()+")" +" "+telVO.getTelefone());
						telDTO.setRamalEnderecoComercial(telVO.getRamal());
					}
				}
			}
			TelefonePessoaVO telVO = lstTelVO.get(0);
			telDTO.setOutroTelefone("("+telVO.getDdd()+")" +" "+telVO.getTelefone());
		}
		return telDTO;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterFonteRendaPessoaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public FonteRendaPessoaIntegracaoDTO obterFonteRendaPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException{
		List<FonteRendaPessoaVO> fonteRendaVO = fonteRendaPessoaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
		FonteRendaPessoaIntegracaoDTO fonteRendaPessoaIntegracaoDTO = new FonteRendaPessoaIntegracaoDTO();
		BigDecimal vlrTotalRenda = BigDecimal.ZERO;
		if(fonteRendaVO != null && !fonteRendaVO.isEmpty()){
			for (FonteRendaPessoaVO fonteRendaPessoaVO : fonteRendaVO) {
				if(fonteRendaPessoaVO.getValorReceitaBrutaMensal() != null){
					vlrTotalRenda = vlrTotalRenda.add(fonteRendaPessoaVO.getValorReceitaBrutaMensal());									
				}
			}
		}
		fonteRendaPessoaIntegracaoDTO.setRendaMensal(vlrTotalRenda);			
		return fonteRendaPessoaIntegracaoDTO;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterBemPessoaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public BemPessoaIntegracaoDTO obterBemPessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException{
		
		List<BemPessoaVO> bemPessoaVO = bemPessoaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
		BemPessoaIntegracaoDTO dto = new BemPessoaIntegracaoDTO();
		
		if(!bemPessoaVO.isEmpty()){
			BigDecimal valorTotalBens = BigDecimal.valueOf(0);
			BigDecimal fazerPorcentagem = BigDecimal.valueOf(ContaCapitalConstantes.NUMERO_CEM);
			for (BemPessoaVO vo : bemPessoaVO) {
				BigDecimal valorAtualMercado = vo.getValorAtualMercado();
				BigDecimal percentual = vo.getPercentual();
				if(valorAtualMercado != null && percentual != null){
					valorTotalBens = valorTotalBens.add(valorAtualMercado.multiply(percentual.divide(fazerPorcentagem)));
				}
			}
			dto.setValorTotalBens(valorTotalBens);
		}
		return dto;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterReferenciaPessoaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public ReferenciaPessoaIntegracaoDTO obterReferenciaPessoaInstituicao (Integer idPessoa, Integer idInstituicao) throws BancoobException{
		
		ReferenciaPessoaIntegracaoDTO refDTO = new ReferenciaPessoaIntegracaoDTO();
		List<ReferenciaPessoaVO> lstRefVO = referenciaPessoaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
		if(!lstRefVO.isEmpty()){
			for (ReferenciaPessoaVO ref : lstRefVO) {
				//primeira referencia
				if(refDTO.getTipoReferenciaUm()==null) {
					refDTO.setTipoReferenciaUm(ref.getDescricaoTipoReferencia());
					refDTO.setDescReferenciaUm(ref.getObservacao() != null ? ref.getObservacao() : ref.getNomePessoaReferencia());
					//verifica se a referencia tem o telefone cadastrado na tabela de referencia
					if(ref.getDdd() != null && ref.getTelefone() != null) {
						refDTO.setNumReferenciaUm("("+ref.getDdd()+")"+" "+ref.getTelefone());
					} else if (ref.getIdPessoaReferencia() != null) {
						//Caso nao encontre 'telefone referencia', utiliza o numero de telefone cadastrado na tabela de telefone
						TelefonePessoaIntegracaoDTO telDTO = obterTelefonePessoaInstituicao(ref.getIdPessoaReferencia(), idInstituicao);
						if(ref.getCodigoTipoReferencia() != null && ref.getCodigoTipoReferencia().equals(TipoReferenciaEnum.PARTICULAR.getCodigo())){
							refDTO.setNumReferenciaUm(telDTO.getTelefoneEnderecoResidencial());
							refDTO.setTipoReferenciaUm(TipoReferenciaEnum.find(ref.getCodigoTipoReferencia()).getDescricao());
						}
						if(ref.getCodigoTipoReferencia() != null && ref.getCodigoTipoReferencia().equals(TipoReferenciaEnum.COMERCIAL.getCodigo())) {
							refDTO.setNumReferenciaUm(telDTO.getTelefoneEnderecoComercial());
							refDTO.setTipoReferenciaUm(TipoReferenciaEnum.find(ref.getCodigoTipoReferencia()).getDescricao());
						}
						if (refDTO.getNumReferenciaUm() == null) {
							refDTO.setNumReferenciaUm(telDTO.getOutroTelefone());
						}
					}
					continue;
				}
				//segunda referencia
				if(refDTO.getTipoReferenciaDois()==null){
					refDTO.setTipoReferenciaDois(ref.getDescricaoTipoReferencia());
					refDTO.setDescReferenciaDois(ref.getObservacao() != null ? ref.getObservacao() : ref.getNomePessoaReferencia());
					//verifica se a referencia tem o telefone cadastrado na tabela de referencia
					if(ref.getDdd() != null && ref.getTelefone() != null) {
						refDTO.setNumReferenciaDois("("+ref.getDdd()+")"+" "+ref.getTelefone());
					} else if (ref.getIdPessoaReferencia() != null) {
						//Caso nao encontre 'telefone referencia', utiliza o numero de telefone cadastrado na tabela de telefone
						TelefonePessoaIntegracaoDTO telDTO = obterTelefonePessoaInstituicao(ref.getIdPessoaReferencia(), idInstituicao);
						if(ref.getCodigoTipoReferencia() != null && ref.getCodigoTipoReferencia().equals(TipoReferenciaEnum.PARTICULAR.getCodigo())){
							refDTO.setNumReferenciaDois(telDTO.getTelefoneEnderecoResidencial());
							refDTO.setTipoReferenciaDois(TipoReferenciaEnum.find(ref.getCodigoTipoReferencia()).getDescricao());
						}
						if(ref.getCodigoTipoReferencia() != null && ref.getCodigoTipoReferencia().equals(TipoReferenciaEnum.COMERCIAL.getCodigo())) {
							refDTO.setNumReferenciaDois(telDTO.getTelefoneEnderecoComercial());
							refDTO.setTipoReferenciaDois(TipoReferenciaEnum.find(ref.getCodigoTipoReferencia()).getDescricao());
						}
						if (refDTO.getNumReferenciaDois() == null) {
							refDTO.setNumReferenciaDois(telDTO.getOutroTelefone());
						}
					}
				}
			}
		}
		return refDTO;
	}

	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterPessoaJuridicaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public PessoaJuridicaIntegracaoDTO obterPessoaJuridicaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException{
		
		PessoaJuridicaIntegracaoDTO pessoaJuridicaIntegracaoDTO = new PessoaJuridicaIntegracaoDTO();
		PessoaJuridicaVO pessoaJuridicaVO = pessoaJuridicaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
		if(pessoaJuridicaVO != null){
			pessoaJuridicaIntegracaoDTO.setRazaoSocialEmpresa(pessoaJuridicaVO.getNomeCompleto());
			pessoaJuridicaIntegracaoDTO.setCnpjEmpresa(pessoaJuridicaVO.getCpfCnpj());
			pessoaJuridicaIntegracaoDTO.setInscricaoEstadual(pessoaJuridicaVO.getInscricaoEstadual());
			pessoaJuridicaIntegracaoDTO.setCapitalSocial(pessoaJuridicaVO.getValorCapitalSocial());
		}
		return pessoaJuridicaIntegracaoDTO;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterConjugePessoaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public List<RelacionamentoPessoaIntegracaoDTO> obterConjugePessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException{
		
		List<RelacionamentoPessoaIntegracaoDTO> listaRetorno = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		List<RelacionamentoPessoaVO> relConjPessoaVOLst = relacionamentoPessoaDelegate.obterConjugesPorPessoaInstituicao(idPessoa, idInstituicao);
		RelacionamentoPessoaIntegracaoDTO dto;
		
		for (RelacionamentoPessoaVO vo : relConjPessoaVOLst) {
			dto = new RelacionamentoPessoaIntegracaoDTO();
			dto.setNomeConjuge(obterPessoaInstituicao(vo.getIdPessoaRelacionada(), vo.getIdInstituicao()).getNomePessoa());
			dto.setCpfConjuge(obterPessoaInstituicao(vo.getIdPessoaRelacionada(),vo.getIdInstituicao()).getCpfCnpj());
			listaRetorno.add(dto);
		}
		return listaRetorno;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterRepresentantesLegaisPessoaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public List<RelacionamentoPessoaIntegracaoDTO> obterRepresentantesLegaisPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException{
		
		List<RelacionamentoPessoaIntegracaoDTO> listaRetorno = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		List<RelacionamentoPessoaVO> relReprPessoaVOLst = relacionamentoPessoaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
		RelacionamentoPessoaIntegracaoDTO dto;
		
		for (RelacionamentoPessoaVO vo : relReprPessoaVOLst) {
			dto = new RelacionamentoPessoaIntegracaoDTO();
			if(vo.getCodigoTipoRelacionamento() != null){
				if(vo.getCodigoTipoRelacionamento().equals(TipoRelacionamentoPessoaEnum.ADMINISTRADOR.getCodigo()) ||
					vo.getCodigoTipoRelacionamento().equals(TipoRelacionamentoPessoaEnum.PROCURADOR.getCodigo()) ||
					vo.getCodigoTipoRelacionamento().equals(TipoRelacionamentoPessoaEnum.REPRESENTATE_LEGAL.getCodigo()) ||
					vo.getCodigoTipoRelacionamento().equals(TipoRelacionamentoPessoaEnum.SOCIO_ADMINISTRADOR.getCodigo())) {
						dto.setNomeRepresentanteLegal(obterPessoaInstituicao(vo.getIdPessoaRelacionada(), vo.getIdInstituicao()).getNomePessoa());
						dto.setTipoRepresentanteLegal(vo.getDescricaoTipoRelacionamento());
						dto.setCpfRepresentanteLegal(obterPessoaInstituicao(vo.getIdPessoaRelacionada(), vo.getIdInstituicao()).getCpfCnpj());
					}
					listaRetorno.add(dto);
			}
		}
		return listaRetorno;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterResponsavelLegalPessoaInstituicao(java.lang.Integer, java.lang.Integer)
	 */
	public List<RelacionamentoPessoaIntegracaoDTO> obterResponsavelLegalPessoaInstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException{
		
		List<RelacionamentoPessoaIntegracaoDTO> listaRetorno = new ArrayList<RelacionamentoPessoaIntegracaoDTO>();
		List<RelacionamentoPessoaVO> relReprPessoaVOLst = relacionamentoPessoaDelegate.obterPorPessoaInstituicaoTipo(idPessoa, idInstituicao, TipoRelacionamentoPessoaEnum.RESPONSAVEL_LEGAL.getCodigo());
		RelacionamentoPessoaIntegracaoDTO dto;
		
		for (RelacionamentoPessoaVO vo : relReprPessoaVOLst) {
			dto = new RelacionamentoPessoaIntegracaoDTO();
			dto.setNomeResponsavelLegal(obterPessoaInstituicao(vo.getIdPessoaRelacionada(), vo.getIdInstituicao()).getNomePessoa());
			dto.setCpfResponsavelLegal(obterPessoaInstituicao(vo.getIdPessoaRelacionada(), vo.getIdInstituicao()).getCpfCnpj());
			listaRetorno.add(dto);
		}
		return listaRetorno;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterPessoaJuridicaFormaConstituicao(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public PessoaIntegracaoDTO obterPessoaJuridicaFormaConstituicao(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		
		PessoaIntegracaoDTO pessoaIntegracaoDTO = new PessoaIntegracaoDTO();
		try {
			
			PessoaJuridicaVO pessoaJuridicaVO = pessoaJuridicaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao);
			if(pessoaJuridicaVO != null) {
				pessoaIntegracaoDTO.setCodigoTipoFormaConstituicao(pessoaJuridicaVO.getCodigoTipoFormaConstituicao());
				pessoaIntegracaoDTO.setNaturezaJuridica(pessoaJuridicaVO.getDescricaoTipoFormaConstituicao());
				pessoaIntegracaoDTO.setNumRegistroOrgaoCompetente(pessoaJuridicaVO.getNumeroRegistroJuntaComercial());
				pessoaIntegracaoDTO.setDataRegistroOrgaoCompetente(pessoaJuridicaVO.getDataRegistroJuntaComercial());
				pessoaIntegracaoDTO.setDataConstituicao(pessoaJuridicaVO.getDataConstituicao());
			}
			
			ClienteVO clienteVO = clienteDelegate.obterPorIdPessoaInstituicao(idPessoa, idInstituicao);
			if(clienteVO != null) {
				pessoaIntegracaoDTO.setCodigoAtividadeEconomica(clienteVO.getCodigoAtividadeEconomica());
				pessoaIntegracaoDTO.setCodigoCnaeFiscal(clienteVO.getCodigoCnaeFiscal());	
			}
			
			return pessoaIntegracaoDTO;
		
		} catch (BancoobRuntimeException e) {
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		} catch(BancoobException e){
			throw new IntegracaoCapesNegocioException("MSG_001",e);
		}
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#isPessoaJuridica(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public Boolean isPessoaJuridica(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		Short codTipoPessoa = capesIntegracaoDelegate.obterPessoaInstituicao(idPessoa, idInstituicao).getCodTipoPessoa();
		return TipoPessoaEnum.PESSOA_JURIDICA.getCodigo().equals(codTipoPessoa);	
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#isClienteCadastrado(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public Boolean isClienteCadastrado(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		ClienteVO cli = clienteDelegate.obterPorIdPessoaInstituicao(idPessoa, idInstituicao);
		if(cli != null && cli.getIdPessoa() != null && StringUtils.isNotBlank(cli.getCpfCnpj())) {
			return true;
		}
		return false;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterAnotacoesBaixadas(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public List<AnotacaoPessoaDTO> obterAnotacoesBaixadas(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterAnotacoes(idPessoa, idInstituicao, true);
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterAnotacoesVigentes(java.lang.Integer, java.lang.Integer)
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)		
	public List<AnotacaoPessoaDTO> obterAnotacoesVigentes(Integer idPessoa, Integer idInstituicao) throws BancoobException {
		return obterAnotacoes(idPessoa, idInstituicao, false);
	}
	
	/**
	 * Recupera anotacoes vigentes ou baixadas
	 * @param idPessoa
	 * @param idInstituicao
	 * @param baixadas
	 * @return
	 * @throws BancoobException
	 */
	private List<AnotacaoPessoaDTO> obterAnotacoes(Integer idPessoa, Integer idInstituicao, boolean baixadas) throws BancoobException {
		List<AnotacaoPessoaDTO> lstRetorno = new ArrayList<AnotacaoPessoaDTO>(0);
		
		try {
			List<AnotacaoPessoaVO> lstAnotacaoVO = anotacaoPessoaDelegate.obterPorPessoaInstituicao(idPessoa, idInstituicao, baixadas);

			AnotacaoPessoaDTO anotacaoPessoaDTO = null;
			if(lstAnotacaoVO != null && !lstAnotacaoVO.isEmpty()) {
				
				for(AnotacaoPessoaVO vo : lstAnotacaoVO) {
					anotacaoPessoaDTO = new AnotacaoPessoaDTO();
					anotacaoPessoaDTO.setDataHoraAnotacao(vo.getDataHoraAnotacao());
					anotacaoPessoaDTO.setDataHoraBaixa(vo.getDataHoraBaixa());
					anotacaoPessoaDTO.setDataHoraOcorrencia(vo.getDataHoraOcorrencia());
					anotacaoPessoaDTO.setDescricaoCategoriaAnotacao(vo.getDescricaoCategoriaAnotacao());
					anotacaoPessoaDTO.setDescricaoOrigemInfo(vo.getDescricaoOrigemInfo());
					anotacaoPessoaDTO.setDescricaoTipoAnotacao(vo.getDescricaoTipoAnotacao());
					anotacaoPessoaDTO.setDescricaoTipoBaixa(vo.getDescricaoTipoBaixa());
					anotacaoPessoaDTO.setDescricaoTipoConsultaOrigem(vo.getDescricaoTipoConsultaOrigem());
					anotacaoPessoaDTO.setFlexibilidade(vo.getFlexibilidade());
					anotacaoPessoaDTO.setIdAnotacao(vo.getIdAnotacao());
					anotacaoPessoaDTO.setObservacao(vo.getObservacao());
					anotacaoPessoaDTO.setQuantidade(vo.getQuantidade());
					anotacaoPessoaDTO.setValor(vo.getValor());
					anotacaoPessoaDTO.setCodigoTipoAnotacao(vo.getCodigoTipoAnotacao());
					
					lstRetorno.add(anotacaoPessoaDTO);
				}
			}
			
		} catch (BancoobRuntimeException e) {
			getLogger().erro(e, e.getMessage());
			throw new IntegracaoCapesException(e);
		}
		
		return lstRetorno;
	}
	
	/**
	 * @see br.com.sicoob.sisbr.cca.integracao.negocio.servicos.CapesIntegracaoServico#obterPessoaPorCpfCnpj(java.lang.String)
	 */
	public List<PessoaIntegracaoDTO> obterPessoaPorCpfCnpj(String cpfCnpj) throws BancoobException {
		List<PessoaIntegracaoDTO> lstRetorno = null;
		
		try {
			
			List<PessoaVO> pessoas = pessoaDelegate.obterPorCpfCnpj(cpfCnpj);
			
			PessoaIntegracaoDTO dto = null;
			if(pessoas != null && !pessoas.isEmpty()) {
				
				lstRetorno = new ArrayList<PessoaIntegracaoDTO>(0);
				
				for(PessoaVO pessoaVO : pessoas) {
					dto = new PessoaIntegracaoDTO();
					dto.setIdInstituicao(pessoaVO.getIdInstituicao());
					dto.setIdPessoa(pessoaVO.getIdPessoa());
					
					lstRetorno.add(dto);
				}
			}
			
		} catch (BancoobRuntimeException e) {
			throw new IntegracaoCapesException(e);
		} catch(BancoobException e){
			throw new IntegracaoCapesException(e);
		}
		
		return lstRetorno;
	}
	
	/**
	 * Setters and Getters
	 * @param pessoaDelegate
	 */
	public void setPessoaDelegate(PessoaDelegate pessoaDelegate) {
		this.pessoaDelegate = pessoaDelegate;
	}
	
	public void setPessoaFisicaDelegate(PessoaFisicaDelegate pessoaFisicaDelegate) {
		this.pessoaFisicaDelegate = pessoaFisicaDelegate;
	}
	
	public void setTelefonePessoaDelegate(TelefonePessoaDelegate telefonePessoaDelegate) {
		this.telefonePessoaDelegate = telefonePessoaDelegate;
	}
	
	public void setFonteRendaPessoaDelegate(FonteRendaPessoaDelegate fonteRendaPessoaDelegate) {
		this.fonteRendaPessoaDelegate = fonteRendaPessoaDelegate;
	}
	
	public void setBemPessoaDelegate(BemPessoaDelegate bemPessoaDelegate) {
		this.bemPessoaDelegate = bemPessoaDelegate;
	}
	
	public void setReferenciaPessoaDelegate(ReferenciaPessoaDelegate referenciaPessoaDelegate) {
		this.referenciaPessoaDelegate = referenciaPessoaDelegate;
	}
	
	public void setPessoaJuridicaDelegate(PessoaJuridicaDelegate pessoaJuridicaDelegate) {
		this.pessoaJuridicaDelegate = pessoaJuridicaDelegate;
	}
	
	public void setRelacionamentoPessoaDelegate(RelacionamentoPessoaDelegate relacionamentoPessoaDelegate) {
		this.relacionamentoPessoaDelegate = relacionamentoPessoaDelegate;
	}
	
	public void setClienteDelegate(ClienteDelegate clienteDelegate) {
		this.clienteDelegate = clienteDelegate;
	}
	
	public void setCapesIntegracaoDelegate(CapesIntegracaoDelegate capesIntegracaoDelegate) {
		this.capesIntegracaoDelegate = capesIntegracaoDelegate;
	}
	
	public void setAnotacaoPessoaDelegate(AnotacaoPessoaDelegate anotacaoPessoaDelegate) {
		this.anotacaoPessoaDelegate = anotacaoPessoaDelegate;
	}
	
	public void setEnderecoPessoaDelegate(EnderecoPessoaDelegate enderecoPessoaDelegate) {
		this.enderecoPessoaDelegate = enderecoPessoaDelegate;
	}
	
	public void setLocApiTipoLogradouroDelegate(LocApiTipoLogradouroDelegate logradouroDelegate) {
		this.logradouroDelegate = logradouroDelegate;
	}
}