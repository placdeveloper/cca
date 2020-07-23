/*
 * 
 */
package br.com.sicoob.sisbr.cca.cadastro.servicos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.bancoob.excecao.BancoobException;
import br.com.bancoob.sisbrweb.dto.RequisicaoReqDTO;
import br.com.bancoob.sisbrweb.dto.RetornoDTO;
import br.com.bancoob.sisbrweb.fachada.BancoobFachada;
import br.com.bancoob.sisbrweb.seguranca.RemoteService;


/**
 * Fachada CCARenServico 
 */
@RemoteService
public class CCARenServico extends BancoobFachada {

	/**
	 * obterDefinicoes
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO obterDefinicoes(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retDTO = new RetornoDTO();
		
		try {
			
			Method[] metodos = recuperarMetodos(Class.forName(dto.getDados().get("clazz").toString()));
			
			for (Method method : metodos) {
				retDTO.getDados().put(method.toString(), recuperarListaParametros(method));
			}
			
		} catch (ClassNotFoundException e) {
			retDTO.getDados().put("retorno", e.getMessage());  
		}

		return retDTO;
	}
	
	/**
	 * listarMetodos
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO listarMetodos(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retDTO = new RetornoDTO();
		
		try {
			
			Method[] metodos = recuperarMetodos(Class.forName(dto.getDados().get("clazz").toString()));
			
			for (Method method : metodos) {
				retDTO.getDados().put(method.toString(), recuperarListaParametros(method));
			}
			
		} catch (ClassNotFoundException e) {
			retDTO.getDados().put("retorno", e.getMessage());  
		}
		
		return retDTO;
	}
	
	/**
	 * chamarServico
	 * @param dto
	 * @return
	 * @throws BancoobException
	 */
	public RetornoDTO chamarServico(RequisicaoReqDTO dto) throws BancoobException {
		RetornoDTO retDTO = new RetornoDTO();
		
		 try {

			Class obj = Class.forName(dto.getDados().get("clazz").toString(), true, Thread.currentThread().getContextClassLoader());
			
			Method[] metodos = recuperarMetodos(obj);
			
			for(Method method : metodos) {
				
				if(method.toString().equals(dto.getDados().get("metodo").toString())) {
					
					String[] arrParam = dto.getDados().get("parametros").toString().split(",");
					
					Object[] oos = new Object[arrParam.length];
					for(int i = 0; i < arrParam.length; i++) {
						
						if(method.getParameterTypes()[i].getName().equals(Integer.class.getName())) {
							oos[i] = Integer.valueOf(arrParam[i]);
							
						} else if(method.getParameterTypes()[i].getName().equals(Long.class.getName())) {
							oos[i] = Long.valueOf(arrParam[i]);
							
						} else if(method.getParameterTypes()[i].getName().equals(BigDecimal.class.getName())) {
							oos[i] = new BigDecimal(arrParam[i]);
							
						} else if(method.getParameterTypes()[i].getName().equals(Date.class.getName())) {
							
							oos[i] = new SimpleDateFormat("dd/MM/yyyy").parse(arrParam[i]);
						}
					}
					
					method.setAccessible(true);
					
					retDTO.getDados().put("retorno", method.invoke(obj.newInstance(), oos).toString()); 
				}
			}
			
		} catch (ClassNotFoundException e) {
			retDTO.getDados().put("retorno", e.getMessage()); 
		} catch (IllegalArgumentException e) {
			retDTO.getDados().put("retorno", e.getMessage());  
		} catch (IllegalAccessException e) {
			retDTO.getDados().put("retorno", e.getMessage());  
		} catch (InvocationTargetException e) {
			retDTO.getDados().put("retorno", e.getTargetException().getMessage());  
		} catch (InstantiationException e) {
			retDTO.getDados().put("retorno", e.getMessage());  
		} catch (SecurityException e) {
			retDTO.getDados().put("retorno", e.getMessage());  
		} catch (ParseException e) {
			retDTO.getDados().put("retorno", e.getMessage());  
		}  
		
		return retDTO;
	}
	
	private Method[] recuperarMetodos(Class clazz) {
	    List<Method> result = new ArrayList<Method>();
	    	
        for(int i = 0; i < clazz.getDeclaredMethods().length; i++) {
        	
        	Method method = clazz.getDeclaredMethods()[i];
        	
            if(Modifier.isPublic(method.getModifiers()) && method.getExceptionTypes().length > 0
            		&& method.getExceptionTypes()[0].toString().equals("class br.com.bancoob.excecao.BancoobException")) {
            	
            	result.add(method);
            }
        }
        
	    return result.toArray(new Method[result.size()]);
	}
	
	private List<String> recuperarListaParametros(Method method) throws ClassNotFoundException {
		
		List<String> parametros = new ArrayList<String>(0);
		
		for(int i = 0; i < method.getParameterTypes().length; i++) {
			
			if(method.getParameterTypes()[i].toString().contains("br.com.sicoob")) {
				Field[] fields = Class.forName(method.getParameterTypes()[i].getName()).getDeclaredFields();
				
				StringBuffer strDTO = new StringBuffer(); 
				for(int f = 0; f < fields.length; f++) {
					if(f == 0) {
						strDTO.append(method.getParameterTypes()[i].getName()).append("{").append(fields[f].getName());
					} else if(f == fields.length-1) {
						strDTO.append(fields[f].getName()).append("}");
					} else {
						strDTO.append(fields[f].getName());
					}
				}
				parametros.add(strDTO.toString());
				
			} else {
				parametros.add(method.getParameterTypes()[i].getName());
			}
		}
		
		return parametros; 
	}
}