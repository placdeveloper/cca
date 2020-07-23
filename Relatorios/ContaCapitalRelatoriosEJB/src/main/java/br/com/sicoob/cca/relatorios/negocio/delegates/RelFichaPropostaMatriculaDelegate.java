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
		 * Método para garantir que o delegate seja criado apenas pela fabrica de delegates
		 */
		RelFichaPropostaMatriculaDelegate() {
		}
		
		/**
		 * Método static para criar instancia de RelFichaPropostaMatriculaDelegate
		 * @return RelFichaPropostaMatriculaDelegate
		 */
		public static RelFichaPropostaMatriculaDelegate getInstance() {
			return new RelFichaPropostaMatriculaDelegate();
		}
		
		
		/**
		 * Método para criar uma instancia do localizador de servico do relatório de proposta de matricula
		 */
		@Override
		protected RelFichaPropostaMatriculaServico localizarServico() {
			return (RelFichaPropostaMatriculaServico) ContaCapitalRelatoriosServiceLocator.getInstance().localizarRelFichaPropostaMatriculaServico();
		}
		
		/**
		 * Método responsável para gerar relatório de histórico de proposta de matricula
		 * @param RelFichaPropostaMatriculaDTO
		 * @return
		 * @throws BancoobException
		 */
		public Object gerarRelatorioFichaPropostaMatricula(RelFichaPropostaMatriculaDTO dto) throws BancoobException {
			return getServico().gerarRelatorioFichaPropostaMatricula(dto);
		}
	}
