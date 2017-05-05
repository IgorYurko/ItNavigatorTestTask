package org.itnavigator.test.task.web;

import org.itnavigator.test.task.domain.model.Phone;
import org.itnavigator.test.task.service.MainService;
import org.itnavigator.test.task.service.dto.UserDTO;
import org.itnavigator.test.task.web.container.UpdateContainer;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1")
public class MainController {
	
	@Resource
	private MainService service;
	
	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDTO>> userInfo() {
		List<UserDTO> users = service.getUsers();
		if (CollectionUtils.isEmpty(users)) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value = "/user/type", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> typeInfo() {
		List<String> types = Arrays.stream(Phone.Type.values())
									 .map(e -> e.toString().toLowerCase())
									 .collect(Collectors.toList());
		return ResponseEntity.ok(types);
	}
	
	@PutMapping(value = "/user")
	public ResponseEntity<List<UserDTO>> userUpdate(@RequestBody List<UpdateContainer> container) {
		return Optional.of(service.updatePhones(container))
					   .filter(e -> !CollectionUtils.isEmpty(e))
					   .map(ResponseEntity::ok)
					   .orElse(ResponseEntity.badRequest().build());
	}
}
