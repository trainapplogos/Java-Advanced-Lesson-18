package ua.lviv.trainapplogos.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ua.lviv.trainapplogos.domain.Level;
import ua.lviv.trainapplogos.domain.Participant;
import ua.lviv.trainapplogos.service.ParticipantService;

@Controller
public class ParticipantController {
	
	@Autowired
	private ParticipantService participantService;
	
	@GetMapping("/")
	public String init(HttpServletRequest req) {
		req.setAttribute("participants", participantService.findAllParticipants());
		req.setAttribute("mode", "PARTICIPANT_VIEW");
		return "index";
	}
	
	@GetMapping("/new")
	public String newParticipant(HttpServletRequest req) {
		req.setAttribute("mode", "PARTICIPANT_CREATE");
		return "index";
	}
	
//	@RequestMapping(path = {"/save"}, method = RequestMethod.POST)
	@PostMapping("/save")
	public String save(@ModelAttribute Participant participant, HttpServletRequest req) {
		participantService.save(participant);
		req.setAttribute("participants", participantService.findAllParticipants());
		req.setAttribute("mode", "PARTICIPANT_VIEW");
		return "index";
	}
	
	@GetMapping("/update")
	public String update(@RequestParam Integer id, HttpServletRequest req) {
			req.setAttribute("participant", participantService.findOne(id));
			req.setAttribute("mode", "PARTICIPANT_EDIT");
			return "index";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam Integer id, HttpServletRequest req) {
		participantService.delete(id);
		req.setAttribute("participants", participantService.findAllParticipants());
		req.setAttribute("mode", "PARTICIPANT_VIEW");
		return "index";
	}
	
}
