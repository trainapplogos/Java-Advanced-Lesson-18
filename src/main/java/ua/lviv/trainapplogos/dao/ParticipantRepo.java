package ua.lviv.trainapplogos.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import ua.lviv.trainapplogos.domain.Level;
import ua.lviv.trainapplogos.domain.Participant;

@Repository
public class ParticipantRepo {
	private List<Participant> participants = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		Participant part1 = new Participant();
		part1.setName("The Runner");
		part1.setEmail("a@a.a");
		part1.setLevel(Level.L3);
		part1.setPrimarySkill("Runner");
		part1.setId(1);
		
		Participant part2 = new Participant();
		part2.setName("Ice Diver");
		part2.setEmail("b@b.b");
		part2.setLevel(Level.L5);
		part2.setPrimarySkill("Freediver");
		part2.setId(2);
		
		Participant part3 = new Participant();
		part3.setName("The Last Survivor");
		part3.setEmail("c@c.c");
		part3.setLevel(Level.L4);
		part3.setPrimarySkill("Survivalist");
		part3.setId(3);
		
		participants.addAll(Arrays.asList(part1, part2, part3));	
	}
	
	public List<Participant> findAllParticipants() {
		return participants;
	} 
	
	public Participant findOne(Integer id) {
		return participants.stream().filter(part -> part.getId() == id).findFirst().orElse(null); 
	}
	
	public void save(Participant participant) {
		Participant partToUpdate = null;
		
		//update Participant
		if (participant.getId() != null) {
			partToUpdate = findOne(participant.getId());
			
			partToUpdate.setName(participant.getName());
			partToUpdate.setEmail(participant.getEmail());
			partToUpdate.setLevel(participant.getLevel());
			partToUpdate.setPrimarySkill(participant.getPrimarySkill());
			
			int index = participants.indexOf(partToUpdate);
			
			participants.set(index, partToUpdate);
			
		} else { //save
			Participant theLastParticipant = participants.get(participants.size() - 1);
			int theLastId = theLastParticipant.getId();
			participant.setId(theLastId + 1);
			participants.add(participant);
		}
	}
	
	public void delete(Integer id) {
		Iterator<Participant> iter = participants.iterator();
		
		while (iter.hasNext()) {
			if (iter.next().getId() == id) {
				iter.remove();
			}
		}
	}
	
}
