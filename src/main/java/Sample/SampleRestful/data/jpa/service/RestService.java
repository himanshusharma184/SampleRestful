package Sample.SampleRestful.data.jpa.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;

@RestController
public class RestService {

	@GetMapping("/checkout")
	@ResponseBody
	public String getStringV() {
		return "this is cool";
	}

	@RequestMapping(value = "/url/{val}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseEntity<String> getval(@PathVariable String val) {
		return new ResponseEntity<String>(val, HttpStatus.OK);

	}

	@RequestMapping(value = "/test/{val}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public @ResponseBody ResponseEntity<String> setVal(
			@PathVariable String val, @RequestBody String jsonData) {

		try {
			com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
			JsonObject jsonObject = (JsonObject) jsonParser.parse(jsonData);
			System.out.println(jsonObject.get("id"));

			return new ResponseEntity<String>("{id: 'cool'}", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<String>("{id: 'cool'}",
				HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/api/upload")
	public ResponseEntity<?> uploadFile(
			@RequestParam("file") MultipartFile uploadfile) {

		if (uploadfile.isEmpty()) {
			return new ResponseEntity<Object>("please select a file!",
					HttpStatus.OK);
		}

		else
			System.out.println("working ");

		return new ResponseEntity<Object>("Successfully uploaded - "
				+ uploadfile.getOriginalFilename(), new HttpHeaders(),
				HttpStatus.OK);

	}

}
