package br.com.intelector.api.controller;

import br.com.intelector.api.controller.utils.AbstractController;
import br.com.intelector.api.controller.utils.ResourceApi;
import br.com.intelector.api.model.SegUsuario;
import br.com.intelector.api.service.SegUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(value = ResourceApi.RESOURCE_API_SERVICE_USUARIO)
public class SegUsuarioController extends AbstractController<SegUsuarioService, SegUsuario> {
	
	@Autowired 
	private SegUsuarioService segUsuarioService;

	@Override
	public String getFunctionController() {
		return ResourceApi.SERVICE_USUARIO;
	}

	@Override
	public SegUsuarioService getService() {
		return segUsuarioService;
	}

}
