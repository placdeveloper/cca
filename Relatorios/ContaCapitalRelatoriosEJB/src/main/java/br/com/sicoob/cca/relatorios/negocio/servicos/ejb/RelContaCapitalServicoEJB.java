package br.com.sicoob.cca.relatorios.negocio.servicos.ejb;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.apache.commons.codec.binary.Base64;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.excecao.BancoobRuntimeException;
import br.com.bancoob.negocio.contexto.InformacoesUsuario;
import br.com.bancoob.util.DataUtil;
import br.com.sicoob.cca.cadastro.negocio.servicos.interfaces.ContaCapitalServicoLocal;
import br.com.sicoob.cca.comum.constantes.ContaCapitalConstantes;
import br.com.sicoob.cca.entidades.negocio.entidades.ContaCapital;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapital;
import br.com.sicoob.cca.relatorios.infraestrutura.relatorios.RelatorioContaCapitalV2;
import br.com.sicoob.cca.relatorios.negocio.dto.RelExtratoRelatorioDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelSolDevolucaoCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.dto.RelTransferenciaCapitalDTO;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosException;
import br.com.sicoob.cca.relatorios.negocio.excecao.ContaCapitalRelatoriosNegocioException;
import br.com.sicoob.cca.relatorios.negocio.servicos.ContaCapitalRelatoriosServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelContaCapitalServicoLocal;
import br.com.sicoob.cca.relatorios.negocio.servicos.interfaces.RelContaCapitalServicoRemote;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.InstituicaoIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.dto.PessoaIntegracaoDTO;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.CapesIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.integracao.negocio.servicos.interfaces.InstituicaoIntegracaoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.DadosRelFichaMatriculaDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.dto.RelExtratoLegadoDTO;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ContaCapitalLegadoServicoLocal;
import br.com.sicoob.sisbr.cca.legado.negocio.servicos.interfaces.ProdutoLegadoServicoLocal;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;

/**
 * Responsavel por fornecer relatorios do conta capital
 * @author Marco.Nascimento
 */
@Stateless
@Local (RelContaCapitalServicoLocal.class)
@Remote(RelContaCapitalServicoRemote.class) 
public class RelContaCapitalServicoEJB extends ContaCapitalRelatoriosServicoEJB implements RelContaCapitalServicoLocal, RelContaCapitalServicoRemote {
	
	@EJB
	private ContaCapitalLegadoServicoLocal ccaLegadoServico;
	
	@EJB
	private InstituicaoIntegracaoServicoLocal instituicaoIntegracaoServico;

	@EJB
	private CapesIntegracaoServicoLocal capesInt;

	@EJB
	private ContaCapitalServicoLocal contaCapitalServico;
	
	@EJB
	private ProdutoLegadoServicoLocal prodLegadoServico;	

	/** A constante IMAGE_PREFIX. */
	private static final String IMAGE_PREFIX = "#imgtoreplacebase64#";
	
	/** A constante PREFIX. */
	private static final String PREFIX = "data:image/jpeg;base64,";
	
	/**
	 * {@link ContaCapitalRelatoriosServico#gerarFichaAdmissao(Integer)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarFichaAdmissao(Integer idPessoa) throws BancoobException {
		Integer iAplicCoopDif = 0;
		Integer tipoDoc = 0;
		
		Integer tipoCoop = null;
		if(InformacoesUsuario.getInstance() != null && InformacoesUsuario.getInstance().getIdInstituicao() != null) {
			tipoCoop = instituicaoIntegracaoServico.obterTipoCooperativa(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
		} else {
			tipoCoop = 1;
		}
				
		if(tipoCoop != null){
			if(tipoCoop.equals(2)){
				iAplicCoopDif = ContaCapitalConstantes.COD_APLIC_COOP_CRED_RURAL;
			} else if(tipoCoop.equals(1)){
				iAplicCoopDif = ContaCapitalConstantes.COD_APLIC_COOP_CRED_MUTUO;
			}
		}		
		
		List<DadosRelFichaMatriculaDTO> dados = ccaLegadoServico.listarRelFichaAdmissao(idPessoa, iAplicCoopDif);
		
		List<DadosRelFichaMatriculaDTO> filtroT = new ArrayList<DadosRelFichaMatriculaDTO>();
		for(DadosRelFichaMatriculaDTO filtro : dados){
			if(filtro.getBolEnvioCorrespondencia()){
				filtroT.add(filtro);
			}
		}
		if(filtroT.isEmpty()){
			for(DadosRelFichaMatriculaDTO filtro2 : dados){
				if(!filtro2.getBolEnvioCorrespondencia()){
					filtroT.add(filtro2);
				}
			}
		}
		String tipoRelatorio = "CCA_Relatorio_FichaAdmissao.jasper";
		Map<String, Object> parametros = getParametrosComuns();
		
		if(!filtroT.isEmpty()){
			parametros.put("REGISTROS", filtroT.size());
			parametros.put("TIPO_DOCUMENTO", tipoDoc);
			if(filtroT.get(0).getCodTipoSexo().equals(ContaCapitalConstantes.COD_SEXO_MASCULINO)) {
				parametros.put("OPT_M", "X");
			} else if(filtroT.get(0).getCodTipoSexo().equals(ContaCapitalConstantes.COD_SEXO_FEMININO)) {
				parametros.put("OPT_F", "X");
			}			
		}		
		
		getLogger().info("CCA Nome Relatorio:" + tipoRelatorio);
		getLogger().info("CCA Filtro size:" + filtroT.size());
		
		RelatorioContaCapital<DadosRelFichaMatriculaDTO> relatorio = 
				new RelatorioContaCapital<DadosRelFichaMatriculaDTO>(
						filtroT, tipoRelatorio, parametros); 
		
		getLogger().info("CCA Arquivo em RelatorioContaCapital: " + relatorio.getArquivo());
		
		return relatorio.gerarSincronamente();
	}
	
	/**
	 * {@link ContaCapitalRelatoriosServico#gerarFichaMatricula(Long)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarFichaMatricula(Long numMatricula) throws BancoobException {
		try{
			
			Integer codTipoCooperativa = null;
			if(InformacoesUsuario.getInstance() != null && InformacoesUsuario.getInstance().getIdInstituicao() != null) {
				codTipoCooperativa = instituicaoIntegracaoServico.obterTipoCooperativa(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));
			} else {
				codTipoCooperativa = 1;
			}
			
			Integer aplicCoopDif = 0;
			
			if(codTipoCooperativa != null){
				if(codTipoCooperativa.equals(2)){
					aplicCoopDif = ContaCapitalConstantes.COD_APLIC_COOP_CRED_RURAL;
				} else if(codTipoCooperativa.equals(1)){
					aplicCoopDif = ContaCapitalConstantes.COD_APLIC_COOP_CRED_MUTUO;
				}
			}				
			
			List<DadosRelFichaMatriculaDTO> dados = ccaLegadoServico.listarRelFicha(numMatricula.intValue(), aplicCoopDif);
			
			List<DadosRelFichaMatriculaDTO> filtroT = new ArrayList<DadosRelFichaMatriculaDTO>();
			for(DadosRelFichaMatriculaDTO filtro : dados){
				if(filtro.getBolEnvioCorrespondencia()){
					filtroT.add(filtro);
				}
			}
			if(filtroT.isEmpty()){
				for(DadosRelFichaMatriculaDTO filtro2 : dados){
					if(!filtro2.getBolEnvioCorrespondencia()){
						filtroT.add(filtro2);
					}
				}
			}
			
			String tipoRelatorio = "CCA_Relatorio_FichaMatric.jasper";
			Map<String, Object> parametros = getParametrosComuns();
			parametros.put("TXT_MATRICULA", numMatricula.toString());
			
			if(!filtroT.isEmpty()) {
				if(filtroT.get(0).getCodTipoSexo().equals(ContaCapitalConstantes.COD_SEXO_MASCULINO)){
					parametros.put("OPT_M", "X");
				} else if(filtroT.get(0).getCodTipoSexo().equals(ContaCapitalConstantes.COD_SEXO_FEMININO)){
					parametros.put("OPT_F", "X");
				}			
			}
			
			getLogger().info("CCA Nome Relatorio:" + tipoRelatorio);
			getLogger().info("CCA Filtro size:" + filtroT.size());
			
			RelatorioContaCapital<DadosRelFichaMatriculaDTO> relatorio = 
					new RelatorioContaCapital<DadosRelFichaMatriculaDTO>(
							filtroT, tipoRelatorio, parametros); 
			
			getLogger().info("CCA Arquivo em RelatorioContaCapital: " + relatorio.getArquivo());
			
			return relatorio.gerarSincronamente();			
			
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException("MSG_RELATORIO_ERRO",e);
		}
	}
	
	/**
	 * {@link ContaCapitalRelatoriosServico#gerarExtrato(RelExtratoLegadoDTO)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Object gerarExtrato(RelExtratoRelatorioDTO relExtratoDTO) throws BancoobException {
		try{			
			
			validarCamposRelExtrato(relExtratoDTO);
			
			List<DadosRelExtratoLegadoDTO> dados = ccaLegadoServico.listarRelExtrato(montarExtratoLegadoDTO(relExtratoDTO));
			
			getLogger().info("CCA List<DadosRelExtratoLegadoDTO> dados: " + dados);
			
			if (dados==null || dados.isEmpty()){
				return null;
			}
			
			InstituicaoIntegracaoDTO instituicaoIntegracaoDTO = instituicaoIntegracaoServico.obterInstituicaoIntegracao(Integer.valueOf(InformacoesUsuario.getInstance().getIdInstituicao()));				
			
			Map<String, Object> parametros = new HashMap<String, Object>();
			
			if(instituicaoIntegracaoDTO != null) {
				parametros.put("NUM_COOPERATIVA", instituicaoIntegracaoDTO.getNumero());			
				parametros.put("TXT_PERIODO", "Período: " + DataUtil.toStringBr(relExtratoDTO.getDataInicio()) + " a " + DataUtil.toStringBr(relExtratoDTO.getDataFim()));
				parametros.put("DESC_SIGLA_COOP",  instituicaoIntegracaoDTO.getSiglaInstituicao());
				parametros.put("MATR", false);
			}
						
			RelatorioContaCapital<DadosRelExtratoLegadoDTO> relatorio = 
					new RelatorioContaCapital<DadosRelExtratoLegadoDTO>(
							dados, "CCA_Relatorio_Extrato.jasper", parametros);
			
			getLogger().info("CCA Arquivo em RelatorioContaCapital: " + relatorio.getArquivo());
			
			return relatorio.gerarSincronamente();	
			
		} catch (ContaCapitalRelatoriosNegocioException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosNegocioException("MSG_RELATORIO_ERRO");
		} catch (BancoobException e) {
			this.getLogger().erro(e, e.getMessage());
			throw new ContaCapitalRelatoriosException("MSG_RELATORIO_ERRO",e);
		}
	}
	
	/**
	 * {@link ContaCapitalRelatoriosServico#gerarExtratoHTML(RelExtratoLegadoDTO)}
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String gerarExtratoHTML(RelExtratoRelatorioDTO relExtratoDTO) throws BancoobException {
		Object oRel = gerarExtrato(relExtratoDTO);
		if(oRel instanceof JasperPrint) {
			return exportReportToHtml(((JasperPrint) oRel), "UTF-8");
		}
		return "";
	}
	
	/**
	 * Montar extrato legado dto.
	 *
	 * @param dtoEntrada o valor de dto entrada
	 * @return RelExtratoLegadoDTO
	 * @throws BancoobException lança a exceção BancoobException
	 */
	private RelExtratoLegadoDTO montarExtratoLegadoDTO(RelExtratoRelatorioDTO dtoEntrada) throws BancoobException{
		
		RelExtratoLegadoDTO dtoSaida = new RelExtratoLegadoDTO();
		dtoSaida.setNumMatricula(dtoEntrada.getNumMatricula());
		dtoSaida.setDataFim(dtoEntrada.getDataFim());
		dtoSaida.setDataInicio(dtoEntrada.getDataInicio());
		
		return dtoSaida;
	}			
	
	/**
	 * O método Validar campos rel extrato.
	 *
	 * @param relExtratoDTO o valor de rel extrato dto
	 * @throws ContaCapitalRelatoriosNegocioException lança a exceção ContaCapitalRelatoriosNegocioException
	 */
	private void validarCamposRelExtrato(RelExtratoRelatorioDTO relExtratoDTO) throws ContaCapitalRelatoriosNegocioException{
		
		if(relExtratoDTO.getNumMatricula() == null){
			throw new ContaCapitalRelatoriosNegocioException("MSG_INCONSISTENCIA_CAMPO","matrícula");
		}
		if(relExtratoDTO.getDataInicio() == null){
			throw new ContaCapitalRelatoriosNegocioException("MSG_INCONSISTENCIA_CAMPO","período inicial");
		}
		if(relExtratoDTO.getDataFim() == null){
			throw new ContaCapitalRelatoriosNegocioException("MSG_INCONSISTENCIA_CAMPO","período final");
		}
		if(relExtratoDTO.getDataInicio().compareTo(relExtratoDTO.getDataFim()) > 0){
			throw new ContaCapitalRelatoriosNegocioException("MSG_MAIORIGUALQUE", "período final", "período inicial");
		}			
		
	}
	
	/**
     * <p>
     * Converte os relatorios em formato JasperPrint para uma String HTML.
     * O metodo é otimizado para permitir que o resultado da conversão gere as imagens do relatorio em 
      * formato de base 64, evitando outras requisições para o servidor a fim de se obter o restante dos dados.
     * 
      * 
      * @param jasperPrint
     *            contendo o relatorio gerado a partir de um .jrxml do iReport.
     * @return String contendo o HTML da pagina resultado da conversão.
     * @throws BancoobException
     *             Algo inesperado ao converter o relatorio
     */
     @SuppressWarnings("deprecation")
	private String exportReportToHtml(JasperPrint jasperPrint, String encoding) throws BancoobException {
         StringBuffer buffer = new StringBuffer();
         
         Map<String, Object> map = new HashMap<String, Object>();
         try {
              JRHtmlExporter exporter = new JRHtmlExporter();
              
              exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
              exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, buffer);
              
              exporter.setParameter(JRHtmlExporterParameter.IMAGES_MAP, map);
              exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, IMAGE_PREFIX);
              
              exporter.setParameter(JRHtmlExporterParameter.CHARACTER_ENCODING, encoding);
              exporter.setParameter(JRHtmlExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
              exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
              exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
              
              exporter.exportReport();
              buffer = converterImagensBase64(buffer, map);
              
         } catch (JRException e) {
              throw new BancoobException("Ocorreu um erro ao converter o relatorio", e);
         } catch (BancoobRuntimeException e) {
              throw new BancoobException("Ocorreu um erro ao converter o relatorio", e);
         }
         return buffer.toString();
     }

     /**
     * <p>
     * Converte as imagens contidas no buffer pelas imagens contidas no mapa,
     * apos transforma-las para a base 64.
     * 
      * <p>
     * O resultado dessa operação retorna um HTML pronto para ser utilizado pelo
     * WebKit, sem necessidade de uma segunda requisição para se obter as
     * imagens da pagina.
     * 
      * @param buffer
     *            o relatorio jasper convertido em HTML.
     * @param map
     *            as imagens utilizadas na geração do relatorio em formato HTML.
     * @return <b>StringBuffer</b> contendo o HTML, com as imagens inseridas no formato
     *         de base 64.
     */
     private StringBuffer converterImagensBase64(StringBuffer buffer, Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(entry.getValue() instanceof byte[]) {
                String img = new String(Base64.encodeBase64((byte[]) entry.getValue()));
                Pattern pattern = Pattern.compile(IMAGE_PREFIX + entry.getKey());
                Matcher matcher = pattern.matcher(buffer);                              
                buffer = new StringBuffer(matcher.replaceAll(PREFIX + img));
            }
        }
        return buffer;
     }
     
 	/**
 	 * {@link ContaCapitalRelatoriosServico#gerarSolDevolucaoCapital(relSolDevolucaoCapitalDTO)}
 	 */
 	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
 	public Object gerarSolDevolucaoCapital(RelSolDevolucaoCapitalDTO relSolDevolucaoCapitalDTO) throws BancoobException {
 		try{
 			 			 			
 			String nomeRelatorio = "CCA_Relatorio_SolDevolucaoCapital.jasper";
 			
 			Map<String, Object> parametros = getParametrosComuns();
 			List<RelSolDevolucaoCapitalDTO> lista = new ArrayList<RelSolDevolucaoCapitalDTO>(0);
 			lista.add(relSolDevolucaoCapitalDTO);
			
 			ContaCapital contaCapital = contaCapitalServico.obter(relSolDevolucaoCapitalDTO.getIdContaCapital());		
 			InstituicaoIntegracaoDTO dtoInstituicaoIntegracao = instituicaoIntegracaoServico.obterInformacoesInstituicaoSCI(relSolDevolucaoCapitalDTO.getIdInstituicao());
 			PessoaIntegracaoDTO pessoaIntegracaoDTO = capesInt.obterPessoaInstituicao(relSolDevolucaoCapitalDTO.getIdPessoa(), relSolDevolucaoCapitalDTO.getIdInstituicao());
 			
 			parametros.put("DESCNOMEPESSOA", pessoaIntegracaoDTO.getNomeCompleto());
 			parametros.put("NUMCGC_CPF", pessoaIntegracaoDTO.getCpfCnpj());
 			parametros.put("DATAMATRICULA", DataUtil.toStringBr(contaCapital.getDataMatricula()));
 			parametros.put("DESCNOMECOOP", dtoInstituicaoIntegracao.getNomeInstituicao());
 			parametros.put("NOMEMUNICIPIO",  dtoInstituicaoIntegracao.getNomeCidadeUf());
 			parametros.put("DATAASSINATURA", DataUtil.toStringBr(new Date()));
 			
 			return new RelatorioContaCapitalV2<RelSolDevolucaoCapitalDTO>(lista, nomeRelatorio, parametros);
 			
 		} catch (BancoobException e) {
 			this.getLogger().erro(e, e.getMessage());
 			throw new ContaCapitalRelatoriosException("MSG_RELATORIO_ERRO",e);
 		}
 	}     
     
 	/**
 	 * {@link ContaCapitalRelatoriosServico#gerarReciboTransferenciaCapital(relTransferenciaCapitalDTO)}
 	 */
 	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
 	public Object gerarReciboTransferenciaCapital(RelTransferenciaCapitalDTO relTransferenciaCapitalDTO) throws BancoobException {
 		try{
 			 			 			
 			List<RelTransferenciaCapitalDTO> lista = new ArrayList<RelTransferenciaCapitalDTO>(0);
 			lista.add(relTransferenciaCapitalDTO);
 			
 			return new RelatorioContaCapitalV2<RelTransferenciaCapitalDTO>(lista, "CCA_Relatorio_ReciboTransfCapital.jasper", montarParametros(relTransferenciaCapitalDTO));
 			
 		} catch (BancoobException e) {
 			this.getLogger().erro(e, e.getMessage());
 			throw new ContaCapitalRelatoriosException("MSG_RELATORIO_ERRO",e);
 		}
 	}      	
 	
 	private Map<String, Object> montarParametros(RelTransferenciaCapitalDTO relTransferenciaCapitalDTO) throws BancoobException {
			Map<String, Object> parametros = getParametrosComuns();
			
			InstituicaoIntegracaoDTO dtoInstituicaoIntegracao = instituicaoIntegracaoServico.obterInstituicaoIntegracao(relTransferenciaCapitalDTO.getIdInstituicaoDebito());
			parametros.put("DESC_SIGLA_COOP", dtoInstituicaoIntegracao.getNumero() + " - " + dtoInstituicaoIntegracao.getSiglaInstituicao());			
 			parametros.put("DESC_COOP", dtoInstituicaoIntegracao.getSiglaInstituicao()); 			 			
 			parametros.put("NUM_CONTA_CAPITAL_ORIGEM", retornarContaCapitalRelatorio(relTransferenciaCapitalDTO.getIdContaCapitalDebito()).getNumContaCapital());
 			parametros.put("NOME_ORIGEM", retornarPessoaRelatorio(relTransferenciaCapitalDTO.getIdPessoaDebito(), relTransferenciaCapitalDTO.getIdInstituicaoDebito()).getNomeCompleto());
 			parametros.put("CPF_CNPJ_ORIGEM", retornarPessoaRelatorio(relTransferenciaCapitalDTO.getIdPessoaDebito(), relTransferenciaCapitalDTO.getIdInstituicaoDebito()).getCpfCnpj() );
 			parametros.put("DATA_ABERTURA_ORIGEM", DataUtil.toStringBr(retornarContaCapitalRelatorio(relTransferenciaCapitalDTO.getIdContaCapitalDebito()).getDataMatricula()));  			
 			parametros.put("NUM_CONTA_CAPITAL_DESTINO", retornarContaCapitalRelatorio(relTransferenciaCapitalDTO.getIdContaCapitalCredito()).getNumContaCapital());
 			parametros.put("NOME_DESTINO", retornarPessoaRelatorio(relTransferenciaCapitalDTO.getIdPessoaCredito(), relTransferenciaCapitalDTO.getIdInstituicaoCredito()).getNomeCompleto());
 			parametros.put("CPF_CNPJ_DESTINO", retornarPessoaRelatorio(relTransferenciaCapitalDTO.getIdPessoaCredito(), relTransferenciaCapitalDTO.getIdInstituicaoCredito()).getCpfCnpj());
 			parametros.put("DATA_ABERTURA_DESTINO", DataUtil.toStringBr(retornarContaCapitalRelatorio(relTransferenciaCapitalDTO.getIdContaCapitalCredito()).getDataMatricula())); 			
 			parametros.put("DATA_TRANSFERENCIA", DataUtil.toStringBr(prodLegadoServico.obterDataAtualProduto(
 					ContaCapitalConstantes.PRODUTO_CONTA_CAPITAL, relTransferenciaCapitalDTO.getIdInstituicaoDebito()))); 			
 			parametros.put("VALOR_TRANSFERENCIA", relTransferenciaCapitalDTO.getVlrTransferir());
 			
			return parametros;
 		
 	}
 	
 	private ContaCapital retornarContaCapitalRelatorio(Integer idContaCapital) throws BancoobException {
 		return contaCapitalServico.obter(idContaCapital);		 		
 	}
 	
 	private PessoaIntegracaoDTO retornarPessoaRelatorio(Integer idPessoa, Integer idInstituicao) throws BancoobException {
 		return capesInt.obterPessoaInstituicao(idPessoa, idInstituicao);		 		
 	}
 	
}