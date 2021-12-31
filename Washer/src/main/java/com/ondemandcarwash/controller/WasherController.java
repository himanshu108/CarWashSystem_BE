package com.ondemandcarwash.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ondemandcarwash.exception.ApiRequestException;
import com.ondemandcarwash.model.Washer;
import com.ondemandcarwash.model.WasherAuthResponse;
import com.ondemandcarwash.repository.WasherRepository;
import com.ondemandcarwash.service.WasherService;

@RestController
@RequestMapping("/washer")
public class WasherController {
	
	@Autowired
	private WasherService washerService;
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//Creating/Adding Washer
	/*
	 * @PostMapping("/addwasher") public Washer saveWasher(@RequestBody Washer
	 * washer) { return washerService.addWasher(washer); }
	 */
	
	@PostMapping("/addwasher")
	private ResponseEntity<?> saveWasher(@RequestBody Washer washer){
		
		String email = washer.getwEmail();
		String password = washer.getwPassword();
		Washer washer1 = new Washer();
		washer1.setwEmail(email);
		washer1.setwPassword(password);
		try {
			washerRepository.save(washer);
			
		} catch (Exception e) {
			return ResponseEntity.ok(new WasherAuthResponse("Error creating washer "+ email));
		}
		return ResponseEntity.ok(new WasherAuthResponse("Successfully created washer "+ email));
		
		
	}
	//authenticating washer
	@PostMapping("/auth")
	private ResponseEntity<?> authWasher(@RequestBody Washer washer){
		String email = washer.getwEmail();
		String password = washer.getwPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			
		} catch (Exception e) {
			
			return ResponseEntity.ok(new WasherAuthResponse("Error during  washer Authentication"+ email));
		}
		return ResponseEntity.ok(new WasherAuthResponse("Successfully Authenticated washer"+ email));
		
	}
	
	
	//Reading all washer
	@GetMapping("/allwashers")
	public List<Washer> findAllWashers(){
		return washerService.getWashers();
		
	}
	
	//Reading Washer by ID
	@GetMapping("/allwashers/{id}")
	public Optional<Washer> getWasherById(@PathVariable int id) throws ApiRequestException
	{
		return Optional.of(washerRepository.findById(id)
				.orElseThrow(()  -> new ApiRequestException("WASHER NOT FOUND WITH THIS ID ::") ) );
		
}
	//Updating Washer Data by Id
		@PutMapping("/update/{id}")
		public ResponseEntity<Object> updateWasher(@PathVariable int id, @RequestBody Washer washer)
		{
			boolean isWasherExist=washerRepository.existsById(id);
			if(isWasherExist) {
				washerRepository.save(washer);
				return new ResponseEntity<Object>("Washer updated successfully with id " +id, HttpStatus.OK);
			}
			else 
			{
				throw new ApiRequestException("CAN NOT UPDATE AS WASHER NOT FOUND WITH THIS ID ::");
			}
			
		}
		
		// Deleting Washer Data by Id 
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Object> deleteWasher (@PathVariable int id)
		{
			boolean isWasherExist=washerRepository.existsById(id);
			if(isWasherExist) {
				washerService.deleteById(id);
				return new ResponseEntity<Object>("Washer deleted with id"+id,HttpStatus.OK);
			}
			else
			{
				throw new ApiRequestException("CAN NOT DELETE AS WASHER NOT FOUND WITH THIS ID ::");
			}

		}
}
