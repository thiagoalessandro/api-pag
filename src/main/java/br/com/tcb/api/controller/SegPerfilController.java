package br.com.tcb.api.controller;

import br.com.tcb.api.controller.utils.AbstractController;
import br.com.tcb.api.controller.utils.ResourceApi;
import br.com.tcb.api.model.SegOperacao;
import br.com.tcb.api.service.SegPerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*", methods = {RequestMethod.POST, RequestMethod.DELETE, RequestMethod.GET, RequestMethod.PUT})
@RequestMapping(ResourceApi.RESOURCE_API_SERVICE_PERFIL)
public class SegPerfilController extends AbstractController<SegPerfilService, SegOperacao> {

    @Autowired
    private SegPerfilService segPerfilService;

    @Override
    public String getFunctionController() {
        return ResourceApi.SERVICE_PERFIL;
    }

    @Override
    public SegPerfilService getService() {
        return segPerfilService;
    }

}

