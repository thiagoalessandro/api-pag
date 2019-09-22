package br.com.tcb.api.controller.utils;

import br.com.tcb.api.controller.enums.ValidateTypeEnum;
import br.com.tcb.api.controller.response.Response;
import br.com.tcb.api.controller.response.ValidateAttributeDTO;
import br.com.tcb.api.exceptions.NoDataContentException;
import br.com.tcb.api.model.AbstractEntity;
import br.com.tcb.api.security.utils.HasAuthorityUtil;
import br.com.tcb.api.service.GenericService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractController<S extends GenericService, T extends AbstractEntity> {

	@GetMapping("/validate-attributes")
	public ResponseEntity<Response<List<ValidateAttributeDTO>>> validateAttributes() {
		Response<List<ValidateAttributeDTO>> response = new Response();
		try {
			response.setData(findValidationAttributesByEntity());
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
			
		return ResponseEntity.ok(response);
	}

	private List<ValidateAttributeDTO> findValidationAttributesByEntity() {
		List<ValidateAttributeDTO> listValidationAttributesDTO = new ArrayList();
		for (Field field : this.getGenericTypeClass().getDeclaredFields()) {
			if (field.isAnnotationPresent(NotEmpty.class)) {
				listValidationAttributesDTO.add(new ValidateAttributeDTO(getFunctionController(), field.getName(), ValidateTypeEnum.REQUIRED));
			}
		}
		return listValidationAttributesDTO;
	}

	private Class<AbstractEntity> getGenericTypeClass() {
		try {
			String className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0].getTypeName();
			Class<?> clazz = Class.forName(className);
			return (Class<AbstractEntity>) clazz;
		} catch (Exception e) {
			throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
		}
	}

	public abstract String getFunctionController();

	public String genAuthGrant(String operation){
		return getFunctionController().toUpperCase().concat("_").concat(operation);
	}

	@GetMapping("/{id}")
	@PreAuthorize(HasAuthorityUtil.CONSULTAR)
	public ResponseEntity<Response<T>> findById(@PathVariable("id") Long id){
		Response<T> response = new Response<T>();
		try {
			Optional<T> entity = getService().findById(id);
			response.setData(entity.orElseThrow(() -> new NoDataContentException(id)));
			return ResponseEntity.ok(response);
		} catch (NoDataContentException e) {
			e.printStackTrace();
			response.getErrors().add(e.getMessage());
			return ResponseEntity.ok().body(response);
		}
	}

	@DeleteMapping("/{id}")
	@PreAuthorize(HasAuthorityUtil.EXCLUIR)
	public ResponseEntity<Response<String>> delete(@PathVariable("id") Long id){
		Response<String> response = new Response();
		try {
			Optional<T> entity = getService().findById(id);
			entity.orElseThrow(() -> new NoDataContentException(id));
			getService().deleteLogical(entity.get());
			return ResponseEntity.ok(response);
		}catch (NoDataContentException e) {
			e.printStackTrace();
			response.getErrors().add(e.getMessage());
			return ResponseEntity.ok().body(response);
		}
	}

	@GetMapping
	@PreAuthorize(HasAuthorityUtil.CONSULTAR)
	public ResponseEntity<Response<Page<T>>> findByFilter(@RequestParam Map<String,String> params) {
		Response<Page<T>> response = new Response();
		try {
			Page<T> data = getService().findByFilter(params);
			response.setData(data);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/ativo")
	//@PreAuthorize(HasAuthorityUtil.CONSULTAR)
	public ResponseEntity<Response<Iterable<T>>> findByAtivo() {
		Response<Iterable<T>> response = new Response();
		try {
			Iterable<T> data = getService().findByAtivo();
			response.setData(data);
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	@PostMapping
	@PreAuthorize(HasAuthorityUtil.CADASTRAR)
	private ResponseEntity<Response<T>> save(@Valid @RequestBody T body){

		Response<T> response = new Response();
		T persisted;
		try {
			persisted = (T) getService().save(body);
			response.setData(persisted);
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			e.printStackTrace();
			response.getErrors().add(e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	public abstract S getService();

}
