package br.com.ultraworks.erp.api.configuracao.controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificado;
import br.com.ultraworks.erp.api.configuracao.domain.certificado.EmpresaCertificadoDTO;
import br.com.ultraworks.erp.api.configuracao.mapper.EmpresaCertificadoMapper;
import br.com.ultraworks.erp.api.configuracao.service.EmpresaCertificadoService;
import br.com.ultraworks.erp.core.generics.GenericController;

@RestController
@RequestMapping("/configuracao/certificado")
public class EmpresaCertificadoController extends GenericController<EmpresaCertificado, Long, EmpresaCertificadoDTO> {

	public EmpresaCertificadoController(EmpresaCertificadoService service, EmpresaCertificadoMapper mapper) {
		super(service, mapper);
	}

	@PostMapping(value = "/upload", consumes = "multipart/form-data")
	public ResponseEntity<String> uploadCertificado(@RequestParam("file") MultipartFile file,
			@RequestParam String dadosRequest) {

		if (file.isEmpty()) {
			return ResponseEntity.badRequest().body("Por favor, selecione um arquivo.");
		}

		try {
			EmpresaCertificadoDTO dto = createGson().fromJson(dadosRequest, EmpresaCertificadoDTO.class);
			
			EmpresaCertificado entity = mapper.toEntity(dto);
			entity.setArquivoPfx(file.getBytes());
			service.save(entity);

			return ResponseEntity.ok("Certificado enviado com sucesso.");
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o certificado.");
		}

	}
	
	public Gson createGson() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
            @Override
            public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                // Extrai a data da string removendo a parte do tempo
                String dataString = json.getAsString().split("T")[0];
                return LocalDate.parse(dataString, DateTimeFormatter.ISO_LOCAL_DATE);
            }
        });
	        return gsonBuilder.create();
	}
}