/*
 * 
 */
package br.com.sicoob.cca.relatorios.negocio.delegates;

import br.com.bancoob.excecao.BancoobException;
import br.com.sicoob.cca.relatorios.negocio.dto.RelFichaPropostaMatriculaDTO;
import br.com.sicoob.cca.relatorios.negocio.servicos.RelFichaPropostaMatriculaServico;
import br.com.sicoob.cca.relatorios.negocio.servicos.locator.ContaCapitalRelatoriosServiceLocator;

/**
 * A Classe RelFichaPropostaMatriculaDelegate.
 */
public class RelFichaPropostaMatriculaDelegate extends ContaCapitalRelatoriosDelegate<RelFichaPropostaMatriculaServico> {

	
		/**
		 * M�todo para garantir que o delegate seja criado apenas pela fabrica de delegates
		 */
		RelFichaPropostaMatriculaDelegate() {
		}
		
		/**
		 * M�todo static para criar instancia de RelFichaPropostaMatriculaDelegate
		 * @return RelFichaPropostaMatriculaDelegate
		 */
		public static RelFichaPropostaMatriculaDelegate getInstance() {
			return new RelFichaPropostaMatriculaDelegate();
		}
		
		
		/**
		 * M�todo para criar uma instancia do localizador de servico do relat�rio de proposta de matricula
		 */
		@Override
		protected RelFichaPropostaMatriculaServico localizarServico() {
			return (RelFichaPropostaMatriculaServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelFichaPropostaMatriculaServico();
		}
		
		/**
		 * M�todo respons�vel para gerar relat�rio de hist�rico de proposta de matricula
		 * @param RelFichaPropostaMatriculaDTO
		 * @return
		 * @throws BancoobException
		 */
		public Object gerarRelatorioFichaPropostaMatricula(RelFichaPropostaMatriculaDTO dto) throws BancoobException {
			return getServico().gerarRelatorioFichaPropostaMatricula(dto);
		}
	}
