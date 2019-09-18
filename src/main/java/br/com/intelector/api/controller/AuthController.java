package br.com.intelector.api.controller;

import br.com.intelector.api.controller.response.Response;
import br.com.intelector.api.controller.response.UserAuthDTO;
import br.com.intelector.api.controller.utils.ResourceApi;
import br.com.intelector.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ResourceApi.RESOURCE_API_SERVICE_AUTH)
@CrossOrigin(origins="*", methods = {RequestMethod.GET})
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity<Response<UserAuthDTO>> auth(){
        Response<UserAuthDTO> response = new Response<UserAuthDTO>();

        try {
            response.setData(authService.userAuthenticated());
        } catch (Exception e) {
            response.getErrors().add(e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }
}
