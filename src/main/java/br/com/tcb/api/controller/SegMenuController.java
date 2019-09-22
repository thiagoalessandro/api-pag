package br.com.tcb.api.controller;

import br.com.tcb.api.controller.response.Response;
import br.com.tcb.api.controller.utils.ResourceApi;
import br.com.tcb.api.model.SegMenu;
import br.com.tcb.api.service.SegMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ResourceApi.RESOURCE_API_SERVICE_MENU)
@CrossOrigin(origins="*")
public class SegMenuController {
	
	@Autowired 
	private SegMenuService segMenuService;

	@GetMapping
	public ResponseEntity<Response<Iterable<SegMenu>>> findByAll() throws InterruptedException {
		Response<Iterable<SegMenu>> response = new Response<Iterable<SegMenu>>();

		try {
			Iterable<SegMenu> data = segMenuService.findByAtivo();
			response.setData(data);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		//Thread.sleep(6000);
		return ResponseEntity.ok(response);
	}

}
